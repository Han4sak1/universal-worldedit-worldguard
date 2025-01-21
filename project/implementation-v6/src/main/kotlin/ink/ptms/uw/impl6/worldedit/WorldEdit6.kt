package ink.ptms.uw.impl6.worldedit

import com.boydti.fawe.Fawe
import com.sk89q.worldedit.extent.clipboard.Clipboard
import ink.ptms.uw.common.worldedit.WorldEdit
import ink.ptms.uw.common.worldedit.bukkit.UWBukkitAdapter
import ink.ptms.uw.common.worldedit.extent.clipboard.io.UWClipBoardFormats
import ink.ptms.uw.common.worldedit.session.UWClipBoardHolder
import ink.ptms.uw.common.worldedit.tools.UWWETools
import ink.ptms.uw.impl6.worldedit.bukkit.UWBukkitAdapter6Impl
import ink.ptms.uw.impl6.worldedit.extent.clipboard.io.UWClipboardFormats6Impl
import ink.ptms.uw.impl6.worldedit.session.UWClipboardHolder6Impl
import ink.ptms.uw.impl6.worldedit.tools.UWWETools6Impl
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

/**
 * ink.ptms.uw.common.worldguard
 *
 * @author Gei
 * @since 2025/01/21
 **/
internal class WorldEdit6 : WorldEdit {

    override val isLegacy: Boolean = true

    override val isFAWE: Boolean = runCatching { Class.forName("com.boydti.fawe.Fawe") }.getOrNull() != null

    override val bukkitAdapter: UWBukkitAdapter = UWBukkitAdapter6Impl()

    override val clipBoardFormats: UWClipBoardFormats = UWClipboardFormats6Impl()

    override fun getClipboardHolder(clipboard: Clipboard): UWClipBoardHolder = UWClipboardHolder6Impl(clipboard)

    override val tools: UWWETools = UWWETools6Impl()

    override val majorVersion: Long =
        if (isFAWE) {
            //6.x FAWE 以日期命名
            val version = Fawe.get().version!!
            (version.year.toString() + version.month.toString().padStart(2, '0') + version.day.toString().padStart(2, '0')).toLong()
        } else {
            60109
            //篮子sk89q, 6.x调这方法总是(unknown)
            //com.sk89q.worldedit.WorldEdit.getVersion().split(".").take(3).joinToString("") { it.padStart(2, '0') }.toLong()
        }

    object Loader {
        @Awake(LifeCycle.ENABLE)
        fun setup() {
            //因为6.x FAWE也没WorldEdit主类, 故直接检测ClipBoardFormats(7.x)
            if (runCatching { Class.forName("com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats") }.getOrNull() == null) {
                WorldEdit.API = WorldEdit6()
            }
        }
    }
}