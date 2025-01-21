package ink.ptms.uw.impl6.worldguard.protection.managers

import com.sk89q.worldedit.Vector
import com.sk89q.worldedit.Vector2D
import com.sk89q.worldguard.bukkit.BukkitPlayer
import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import com.sk89q.worldguard.protection.ApplicableRegionSet
import com.sk89q.worldguard.protection.managers.RemovalStrategy
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector2
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector3
import ink.ptms.uw.common.worldguard.protection.managers.UWRegionManager
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player

class UWRegionManager6Impl(world: World): UWRegionManager(world) {

    val wgRegionManager = WGBukkit.getRegionManager(world)

    override fun getName(): String {
        return wgRegionManager.name
    }

    override fun load() {
        wgRegionManager.load()
    }

    override fun save() {
        wgRegionManager.save()
    }

    override fun saveChanges(): Boolean {
        return wgRegionManager.saveChanges()
    }

    override fun loadChunk(position: UWBlockVector2) {
        val vector = Vector2D(position.x, position.z)
        wgRegionManager.loadChunk(vector)
    }

    override fun loadChunks(positions: Collection<UWBlockVector2>) {
        val vectors = positions.map { Vector2D(it.x, it.z) }
        wgRegionManager.loadChunks(vectors)
    }

    override fun unloadChunk(position: UWBlockVector2) {
        val vector = Vector2D(position.x, position.z)
        wgRegionManager.unloadChunk(vector)
    }

    override fun getRegions(): Map<String, ProtectedRegion> {
        return wgRegionManager.regions
    }

    override fun setRegions(regions: Map<String?, ProtectedRegion>) {
        return wgRegionManager.setRegions(regions)
    }

    override fun setRegions(regions: Collection<ProtectedRegion>?) {
        return wgRegionManager.setRegions(regions)
    }

    override fun addRegion(region: ProtectedRegion?) {
        return wgRegionManager.addRegion(region)
    }

    override fun hasRegion(id: String?): Boolean {
        return wgRegionManager.hasRegion(id)
    }

    override fun getRegion(id: String?): ProtectedRegion? {
        return wgRegionManager.getRegion(id)
    }

    override fun matchRegion(pattern: String): ProtectedRegion? {
        return wgRegionManager.matchRegion(pattern)
    }

    override fun removeRegion(id: String?): Set<ProtectedRegion>? {
        return wgRegionManager.removeRegion(id)
    }

    override fun removeRegion(id: String?, strategy: RemovalStrategy?): Set<ProtectedRegion>? {
        return wgRegionManager.removeRegion(id, strategy)
    }

    override fun getApplicableRegions(position: UWBlockVector3): ApplicableRegionSet? {
        val vector = Vector(position.x, position.y, position.z)
        return wgRegionManager.getApplicableRegions(vector)
    }

    override fun getApplicableRegions(region: ProtectedRegion?): ApplicableRegionSet {
        return wgRegionManager.getApplicableRegions(region)
    }

    override fun getApplicableRegions(loc: Location): ApplicableRegionSet {
        return wgRegionManager.getApplicableRegions(loc)
    }

    override fun getApplicableRegionsIDs(position: UWBlockVector3): List<String>? {
        val vector = Vector(position.x, position.y, position.z)
        return wgRegionManager.getApplicableRegionsIDs(vector)
    }

    override fun overlapsUnownedRegion(region: ProtectedRegion?, player: Player?): Boolean {
        return wgRegionManager.overlapsUnownedRegion(region, BukkitPlayer(WorldGuardPlugin.inst(), player))
    }

    override fun size(): Int {
        return wgRegionManager.size()
    }

    override fun getRegionCountOfPlayer(player: Player?): Int {
        return wgRegionManager.getRegionCountOfPlayer(BukkitPlayer(WorldGuardPlugin.inst(), player))
    }
}