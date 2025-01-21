package ink.ptms.uw.impl6.worldedit.tools

import com.boydti.fawe.FaweAPI
import com.boydti.fawe.`object`.FaweQueue
import com.sk89q.worldedit.regions.Region
import ink.ptms.uw.common.worldedit.WorldEdit
import ink.ptms.uw.common.worldedit.tools.UWWETools
import org.bukkit.World

/**
 * ink.ptms.uw.impl6.worldedit
 *
 * @author Gei
 * @since 2025/01/21
 **/
class UWWETools6Impl: UWWETools {
    override fun fixLighting(world: World, selection: Region, mode: UWWETools.FixLightingRelightMode): Int {
        if (!WorldEdit.API.isFAWE) return 0
        return when (mode) {
            UWWETools.FixLightingRelightMode.NONE -> FaweAPI.fixLighting(WorldEdit.API.bukkitAdapter.adapt(world), selection, null, FaweQueue.RelightMode.NONE)
            UWWETools.FixLightingRelightMode.OPTIMAL -> FaweAPI.fixLighting(WorldEdit.API.bukkitAdapter.adapt(world), selection, null, FaweQueue.RelightMode.OPTIMAL)
            UWWETools.FixLightingRelightMode.ALL -> FaweAPI.fixLighting(WorldEdit.API.bukkitAdapter.adapt(world), selection, null, FaweQueue.RelightMode.ALL)
        }
    }
}