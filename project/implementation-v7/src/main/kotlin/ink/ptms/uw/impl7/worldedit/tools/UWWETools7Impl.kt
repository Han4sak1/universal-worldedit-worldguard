package ink.ptms.uw.impl7.worldedit.tools

import com.fastasyncworldedit.core.FaweAPI
import com.fastasyncworldedit.core.extent.processor.lighting.RelightMode
import com.sk89q.worldedit.regions.Region
import ink.ptms.uw.common.worldedit.WorldEdit
import ink.ptms.uw.common.worldedit.tools.UWWETools
import org.bukkit.World

/**
 * ink.ptms.uw.impl7.worldedit
 *
 * @author Gei
 * @since 2025/01/21
 **/
class UWWETools7Impl: UWWETools {
    override fun fixLighting(world: World, selection: Region, mode: UWWETools.FixLightingRelightMode): Int {
        if (!WorldEdit.API.isFAWE) return 0
        return when (mode) {
            UWWETools.FixLightingRelightMode.NONE -> FaweAPI.fixLighting(WorldEdit.API.bukkitAdapter.adapt(world), selection, null, RelightMode.NONE)
            UWWETools.FixLightingRelightMode.OPTIMAL -> FaweAPI.fixLighting(WorldEdit.API.bukkitAdapter.adapt(world), selection, null, RelightMode.OPTIMAL)
            UWWETools.FixLightingRelightMode.ALL -> FaweAPI.fixLighting(WorldEdit.API.bukkitAdapter.adapt(world), selection, null, RelightMode.ALL)
        }
    }
}