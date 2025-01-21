package ink.ptms.uw.common.worldedit

import com.sk89q.worldedit.extent.clipboard.Clipboard
import ink.ptms.uw.common.worldedit.bukkit.UWBukkitAdapter
import ink.ptms.uw.common.worldedit.extent.clipboard.io.UWClipBoardFormats
import ink.ptms.uw.common.worldedit.session.UWClipBoardHolder
import ink.ptms.uw.common.worldedit.tools.UWWETools

/**
 * ink.ptms.uw.common.worldedit
 *
 * @author Gei
 * @since 2025/01/21
 **/
interface WorldEdit {

    /** 是否为6.x版本 **/
    val isLegacy: Boolean

    /** 是否为FastAsyncWorldEdit **/
    val isFAWE: Boolean

    /** BukkitAdapter **/
    val bukkitAdapter: UWBukkitAdapter

    /**
     * ClipboardFormats
     * 配合majorVersion使用获取相应的剪贴板
     */
    val clipBoardFormats: UWClipBoardFormats

    /** 获取ClipboardHolder **/
    fun getClipboardHolder(clipboard: Clipboard): UWClipBoardHolder

    /** 其余Bukkit工具 **/
    val tools: UWWETools

    /**
     * 主版本号
     * 对于WorldEdit 版本号为 e.g. 60109(6.1.9) 70300(7.3.0)
     * 对于FAWE 版本号为 210326(21.03.26 6.x 日期命名) 20802(2.8.2 7.x 1.16.5以后命名)
     */
    val majorVersion: Long

    companion object {

        @JvmStatic
        lateinit var API: WorldEdit

        fun isLoaded(): Boolean {
            return ::API.isInitialized
        }
    }
}
