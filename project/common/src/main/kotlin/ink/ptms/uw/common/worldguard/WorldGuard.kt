package ink.ptms.uw.common.worldguard

import ink.ptms.uw.common.worldguard.protection.managers.UWRegionManager
import org.bukkit.World

/**
 * ink.ptms.uw.common.worldguard
 *
 * @author Gei
 * @since 2025/01/21
 **/
interface WorldGuard {

    /** 是否为6.x版本 **/
    val isLegacy: Boolean

    /**
     * 主版本号, 由于WorldGuard自身不提供获取版本号的方法, 故只提供大版本号 e.g. 6 7
     */
    val majorVersion: Int

    /** RegionManager **/
    fun getRegionManager(world: World): UWRegionManager

    companion object {

        @JvmStatic
        lateinit var API: WorldGuard

        fun isLoaded(): Boolean {
            return ::API.isInitialized
        }
    }
}