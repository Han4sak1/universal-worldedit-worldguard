package ink.ptms.uw.common.worldguard.events

import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.bukkit.entity.Player
import taboolib.platform.type.BukkitProxyEvent

/**
 * ink.ptms.uw.common.worldguard.events
 *
 * @author Gei
 * @since 2025/01/21
 **/
abstract class RegionEvent(open val region: ProtectedRegion, open val player: Player, open val movementWay: MovementWay) : BukkitProxyEvent() {
    enum class MovementWay {
        MOVE, TELEPORT, SPAWN, DISCONNECT, WORLD_CHANGE, RIDE
    }
}
