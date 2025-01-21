package ink.ptms.uw.impl7.worldedit

import com.fastasyncworldedit.core.Fawe
import com.sk89q.worldedit.extent.clipboard.Clipboard
import ink.ptms.uw.common.worldedit.WorldEdit
import ink.ptms.uw.common.worldedit.bukkit.UWBukkitAdapter
import ink.ptms.uw.common.worldedit.extent.clipboard.io.UWClipBoardFormats
import ink.ptms.uw.common.worldedit.session.UWClipBoardHolder
import ink.ptms.uw.common.worldedit.tools.UWWETools
import ink.ptms.uw.impl7.worldedit.bukkit.UWBukkitAdapter7Impl
import ink.ptms.uw.impl7.worldedit.extent.clipboard.io.UWClipboardFormats7Impl
import ink.ptms.uw.impl7.worldedit.session.UWClipboardHolder7Impl
import ink.ptms.uw.impl7.worldedit.tools.UWWETools7Impl
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

/**
 * ink.ptms.uw.common.worldguard
 *
 * @author Gei
 * @since 2025/01/21
 **/
internal class WorldEdit7: WorldEdit {
    override val isLegacy: Boolean = false

    override val isFAWE: Boolean = runCatching { Class.forName("com.fastasyncworldedit.core.Fawe") }.getOrNull() != null

    override val bukkitAdapter: UWBukkitAdapter = UWBukkitAdapter7Impl()

    override val clipBoardFormats: UWClipBoardFormats = UWClipboardFormats7Impl()

    override fun getClipboardHolder(clipboard: Clipboard): UWClipBoardHolder = UWClipboardHolder7Impl(clipboard)

    override val tools: UWWETools = UWWETools7Impl()

    override val majorVersion: Long =
        if (isFAWE) {
            //7.x FAWE 以semver命名
            Fawe.instance().version!!.semver.take(3).joinToString("") { it.toString().padStart(2, '0') }.toLong()
        } else {
            com.sk89q.worldedit.WorldEdit.getVersion().split(".").take(3).joinToString("") { it.padStart(2, '0') }.toLong()
        }

    object Loader {
        @Awake(LifeCycle.ENABLE)
        fun setup() {
            //因为6.x FAWE也没WorldEdit主类, 故直接检测ClipBoardFormats(7.x)
            if (runCatching { Class.forName("com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats") }.getOrNull() != null) {
                WorldEdit.API = WorldEdit7()
            }
        }
    }
}