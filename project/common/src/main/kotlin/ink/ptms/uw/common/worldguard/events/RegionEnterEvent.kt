package ink.ptms.uw.common.worldguard.events

import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.bukkit.entity.Player

/**
 * ink.ptms.uw.common.worldguard
 *
 * WorldGuard区域进入事件
 * @param movementWay 进入方式
 * 若想正确的取消事件, 请使用setCancelled(value: Boolean)
 *
 * @author Gei
 * @since 2025/01/21
 **/
class RegionEnterEvent(
    override val region: ProtectedRegion,
    override val player: Player,
    override val movementWay: MovementWay
): RegionEvent(region, player, movementWay) {

    private var isCancellable = true

    init {
        if (movementWay == MovementWay.SPAWN || movementWay == MovementWay.DISCONNECT) {
            this.isCancellable = false
        }
    }

    override fun setCancelled(value: Boolean) {
        if(isCancellable) {
            super.setCancelled(value)
        }
    }
}