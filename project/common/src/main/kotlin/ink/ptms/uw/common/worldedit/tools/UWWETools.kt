package ink.ptms.uw.common.worldedit.tools

import com.sk89q.worldedit.regions.Region
import org.bukkit.World

/**
 * ink.ptms.uw.common.worldguard
 *
 * @author Gei
 * @since 2025/01/21
 **/
interface UWWETools {
    /**
     *  刷新WE操作过后的区块光照(仅限FAWE)
     *  @return 执行次数
     **/
    fun fixLighting(world: World, selection: Region, mode: FixLightingRelightMode): Int

    /**
     * 刷新等级
     */
    enum class FixLightingRelightMode {
        NONE, OPTIMAL, ALL
    }
}