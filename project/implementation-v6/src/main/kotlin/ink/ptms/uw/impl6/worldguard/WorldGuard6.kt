package ink.ptms.uw.impl6.worldguard

import ink.ptms.uw.common.worldguard.WorldGuard
import ink.ptms.uw.common.worldguard.protection.managers.UWRegionManager
import ink.ptms.uw.impl6.worldguard.protection.managers.UWRegionManager6Impl
import org.bukkit.World
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

/**
 * ink.ptms.uw.impl6.worldguard
 *
 * @author Gei
 * @since 2025/01/21
 **/
internal class WorldGuard6: WorldGuard {
    override val isLegacy: Boolean = true

    override val majorVersion: Int = 6

    override fun getRegionManager(world: World): UWRegionManager = UWRegionManager6Impl(world)

    object Loader {
        @Awake(LifeCycle.ENABLE)
        fun setup() {
            if (runCatching { Class.forName("com.sk89q.worldguard.bukkit.WGBukkit") }.getOrNull() != null) {
                WorldGuard.API = WorldGuard6()
            }
        }
    }
}