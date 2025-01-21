package ink.ptms.uw.common.worldguard.events.manager

import com.sk89q.worldguard.protection.regions.ProtectedRegion
import ink.ptms.uw.common.worldguard.WorldGuard
import ink.ptms.uw.common.worldguard.events.RegionEnterEvent
import ink.ptms.uw.common.worldguard.events.RegionEvent
import ink.ptms.uw.common.worldguard.events.RegionLeaveEvent
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.player.*
import org.bukkit.event.vehicle.VehicleMoveEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArraySet

/**
 * ink.ptms.uw.common.worldguard.events.manager
 *
 * @author Gei
 * @since 2025/01/21
 **/
@Suppress("SameParameterValue")
internal object WorldGuardRegionManager {

    private val playerRegions = ConcurrentHashMap<Player, CopyOnWriteArraySet<ProtectedRegion>>()

    @SubscribeEvent
    fun onPlayerKick(e: PlayerKickEvent) {
        playerRegions.remove(e.player)?.forEach { region ->
            RegionLeaveEvent(region, e.player, RegionEvent.MovementWay.DISCONNECT).call()
        }
    }

    @SubscribeEvent
    fun onPlayerQuit(e: PlayerQuitEvent) {
        playerRegions.remove(e.player)?.forEach {
            RegionLeaveEvent(it, e.player, RegionEvent.MovementWay.DISCONNECT).call()
        }
    }

    @SubscribeEvent(ignoreCancelled = true)
    fun onPlayerMove(e: PlayerMoveEvent) {
        if (e.to == null) return
        e.isCancelled = updateRegions(e.player, RegionEvent.MovementWay.MOVE, e.to!!)
    }

    @SubscribeEvent
    fun onPlayerChangeWorld(e: PlayerChangedWorldEvent) {
        clearRegions(e.player, RegionEvent.MovementWay.WORLD_CHANGE)
        updateRegions(e.player, RegionEvent.MovementWay.MOVE, e.player.location)
    }

    @SubscribeEvent
    fun onPlayerTeleport(e: PlayerTeleportEvent) {
        if (e.to == null) return
        val teleportCause = e.cause
        var movementWay = RegionEvent.MovementWay.TELEPORT
        if(teleportCause == PlayerTeleportEvent.TeleportCause.END_PORTAL || teleportCause == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            clearRegions(e.player, RegionEvent.MovementWay.WORLD_CHANGE)
            movementWay = RegionEvent.MovementWay.WORLD_CHANGE
        }

        updateRegions(e.player, movementWay, e.to!!)
    }

    @SubscribeEvent(EventPriority.HIGHEST)
    fun onPlayerLogin(e: PlayerLoginEvent) {
        updateRegions(e.player, RegionEvent.MovementWay.SPAWN, e.player.location)
    }

    @SubscribeEvent
    fun onPlayerRespawn(e: PlayerRespawnEvent) {
        updateRegions(e.player, RegionEvent.MovementWay.SPAWN, e.respawnLocation)
    }

    @SubscribeEvent
    fun onVehicleMove(e: VehicleMoveEvent) {
        e.vehicle.passengers
            .filterIsInstance<Player>()
            .forEach {
                updateRegions(it, RegionEvent.MovementWay.RIDE, it.location)
            }
    }

    private fun clearRegions(player: Player, movementWay: RegionEvent.MovementWay) {
        if (!playerRegions.containsKey(player)) {
            return
        }
        playerRegions[player]!!.forEach {
            RegionLeaveEvent(it, player, movementWay).call()
        }
        playerRegions[player] = CopyOnWriteArraySet()
    }


    private fun updateRegions(player: Player, movement: RegionEvent.MovementWay, to: Location): Boolean {
        if (to.world == null) return false

        val regions: CopyOnWriteArraySet<ProtectedRegion> =
            if(playerRegions.containsKey(player)) {
                CopyOnWriteArraySet(playerRegions[player]!!)
            } else {
                CopyOnWriteArraySet()
            }

        val oldRegions = CopyOnWriteArraySet(regions)
        val regionManager = WorldGuard.API.getRegionManager(to.world!!)

        val appRegions = CopyOnWriteArraySet(regionManager.getApplicableRegions(to).regions)
        val globalRegion = regionManager.getRegion("__global__")
        if(globalRegion != null) {
            appRegions.add(globalRegion)
        }

        appRegions.forEach {
            if(!regions.contains(it)) {
                val enterEvent = RegionEnterEvent(it, player, movement)
                enterEvent.call()

                if(enterEvent.isCancelled) {
                    regions.clear()
                    regions.addAll(oldRegions)
                    return true
                }

                regions.add(it)
            }
        }

        regions.forEach {
            if(!appRegions.contains(it)) {
                if(regionManager.getRegion(it.id) != it) {
                    regions.remove(it)
                } else {
                    val leaveEvent = RegionLeaveEvent(it, player, movement)
                    leaveEvent.call()

                    if(leaveEvent.isCancelled) {
                        regions.clear()
                        regions.addAll(oldRegions)
                        return true
                    }

                    regions.remove(it)
                }
            }
        }

        playerRegions[player] = regions
        return false
    }
}