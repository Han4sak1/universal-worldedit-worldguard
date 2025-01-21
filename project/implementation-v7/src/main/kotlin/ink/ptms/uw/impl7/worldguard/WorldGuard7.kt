package ink.ptms.uw.impl7.worldguard

import ink.ptms.uw.common.worldguard.WorldGuard
import ink.ptms.uw.common.worldguard.protection.managers.UWRegionManager
import ink.ptms.uw.impl7.worldguard.protection.managers.UWRegionManager7Impl
import org.bukkit.World
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

/**
 * ink.ptms.uw.common.worldguard
 *
 * @author Gei
 * @since 2025/01/21
 **/
internal class WorldGuard7: WorldGuard {
    override val isLegacy: Boolean  = false

    override val majorVersion: Int = 7

    override fun getRegionManager(world: World): UWRegionManager = UWRegionManager7Impl(world)

    object Loader {
        @Awake(LifeCycle.ENABLE)
        fun setup() {
            if (runCatching { Class.forName("com.sk89q.worldguard.bukkit.WGBukkit") }.getOrNull() == null) {
                WorldGuard.API = WorldGuard7()
            }
        }
    }
}