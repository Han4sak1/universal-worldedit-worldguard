package ink.ptms.uw.common.worldedit.extent.clipboard.io

import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter
import java.io.File
import java.io.InputStream
import java.io.OutputStream

/**
 * ink.ptms.uw.common.worldedit
 *
 * @author Gei
 * @since 2025/01/21
 **/
interface UWClipBoardFormats {

    /**
     * 存储剪贴板类型
     */
    val aliasesMap: HashMap<String, BuiltinClipBoardFormat>

    /**
     * 根据别名查询
     */
    fun findByAlias(alias: String): BuiltinClipBoardFormat? {
        return aliasesMap[alias]
    }

    /**
     * 根据文件查询
     */
    fun findByFile(file: File): BuiltinClipBoardFormat? {
        return aliasesMap.values.toSet().find { isFormat(file, it) }
    }

    /**
     * 检查文件是否为指定剪贴板类型
     */
    fun isFormat(file: File, format: BuiltinClipBoardFormat): Boolean

    /**
     * 获取ClipBoardReader
     */
    fun getReader(format: BuiltinClipBoardFormat, inputStream: InputStream): ClipboardReader?

    /**
     * 获取ClipBoardWriter
     */
    fun getWriter(format: BuiltinClipBoardFormat, outputStream: OutputStream): ClipboardWriter?

    /**
     * WorldEdit 6/7 剪贴板类型
     */
    enum class BuiltinClipBoardFormat(vararg val aliases: String) {
        //WorldEdit共有
        MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7("mcedit", "mce", "schematic"),
        //WorldEdit 7.x 7.3以下专有
        SPONGE_SCHEM_WE730DOWN("schem"),
        //FAWE专有
        //FAWE 6.x(以21.03.26为例) FAWE 7.x 2.12.2以下共有
        FAST_FAWE6_FAWE7_2122DOWN("fast"),
        //FAWE 7.x 2.12.2及以上专有
        FAST_V2_FAWE7_2122UP("fast.2"),
        FAST_V3_FAWE7_2122UP("fast.3"),
        //FAWE 6.x 7.x共有
        NBT_FAWE6_FAWE7("STRUCTURE", "structure", "nbt"),
        PNG_FAWE6_FAWE7("PNG", "png", "image"),
        //FAWE 7.x共有
        BROKENENTITY_FAWE7("brokenentity", "legacyentity", "le", "be", "brokenentities", "legacyentities"),
        //WorldEdit 7.3及以上 FAWE 2.12.2及以上共有
        SPONGE_V1_SCHEMATIC_WE730UP_FAWE2122UP("sponge.1"),
        SPONGE_V2_SCHEMATIC_WE730UP_FAWE_2122UP("sponge.2"),
        SPONGE_V3_SCHEMATIC_WE730UP_FAWE2122UP("sponge.3");
    }
}