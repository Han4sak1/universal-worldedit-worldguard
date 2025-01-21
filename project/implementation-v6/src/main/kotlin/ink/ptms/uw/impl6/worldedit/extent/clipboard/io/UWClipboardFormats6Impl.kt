package ink.ptms.uw.impl6.worldedit.extent.clipboard.io

import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter
import ink.ptms.uw.common.worldedit.WorldEdit
import ink.ptms.uw.common.worldedit.extent.clipboard.io.UWClipBoardFormats
import ink.ptms.uw.common.worldedit.extent.clipboard.io.UWClipBoardFormats.BuiltinClipBoardFormat
import taboolib.common.platform.function.warning
import java.io.File
import java.io.InputStream
import java.io.OutputStream

/**
 * ink.ptms.uw.impl6.worldedit
 *
 * @author Gei
 * @since 2025/01/21
 **/
class UWClipboardFormats6Impl : UWClipBoardFormats {
    override val aliasesMap: HashMap<String, BuiltinClipBoardFormat> =
        HashMap<String, BuiltinClipBoardFormat>().apply {
            listOf(BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7, BuiltinClipBoardFormat.FAST_FAWE6_FAWE7_2122DOWN, BuiltinClipBoardFormat.PNG_FAWE6_FAWE7, BuiltinClipBoardFormat.NBT_FAWE6_FAWE7).forEach { format ->
                format.aliases.forEach { alias -> this[alias] = format }
            }
        }

    override fun isFormat(file: File, format: BuiltinClipBoardFormat): Boolean {
        if (!WorldEdit.API.isFAWE) {
            return when (format) {
                BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> ClipboardFormat.SCHEMATIC.isFormat(file)
                else -> {
                    warning("Format $format not supported on 6.x worldedit")
                    false
                }
            }
        } else {
            return when (format) {
                BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> ClipboardFormat.SCHEMATIC.isFormat(file)
                BuiltinClipBoardFormat.FAST_FAWE6_FAWE7_2122DOWN -> ClipboardFormat.FAWE.isFormat(file)
                BuiltinClipBoardFormat.NBT_FAWE6_FAWE7 -> ClipboardFormat.STRUCTURE.isFormat(file)
                BuiltinClipBoardFormat.PNG_FAWE6_FAWE7 -> ClipboardFormat.PNG.isFormat(file)
                else -> {
                    warning("Format $format not supported on 6.x fastasyncworldedit")
                    false
                }
            }
        }
    }

    override fun getReader(format: BuiltinClipBoardFormat, inputStream: InputStream): ClipboardReader? {
        if (!WorldEdit.API.isFAWE) {
            return when (format) {
                BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> ClipboardFormat.SCHEMATIC.getReader(inputStream)
                else -> {
                    warning("Format $this not supported on 6.x worldedit")
                    null
                }
            }
        } else {
            return when (format) {
                BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> ClipboardFormat.SCHEMATIC.getReader(inputStream)
                BuiltinClipBoardFormat.FAST_FAWE6_FAWE7_2122DOWN -> ClipboardFormat.SCHEMATIC.getReader(inputStream)
                BuiltinClipBoardFormat.NBT_FAWE6_FAWE7 -> ClipboardFormat.STRUCTURE.getReader(inputStream)
                BuiltinClipBoardFormat.PNG_FAWE6_FAWE7 -> ClipboardFormat.PNG.getReader(inputStream)
                else -> {
                    warning("Format $this not supported on 6.x fastasyncworldedit")
                    null
                }
            }
        }
    }

    override fun getWriter(format: BuiltinClipBoardFormat, outputStream: OutputStream): ClipboardWriter? {
        if (!WorldEdit.API.isFAWE) {
            return when (format) {
                BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> ClipboardFormat.SCHEMATIC.getWriter(outputStream)
                else -> {
                    warning("Format $this not supported on 6.x worldedit")
                    null
                }
            }
        } else {
            return when (format) {
                BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> ClipboardFormat.SCHEMATIC.getWriter(outputStream)
                BuiltinClipBoardFormat.FAST_FAWE6_FAWE7_2122DOWN -> ClipboardFormat.SCHEMATIC.getWriter(outputStream)
                BuiltinClipBoardFormat.NBT_FAWE6_FAWE7 -> ClipboardFormat.STRUCTURE.getWriter(outputStream)
                BuiltinClipBoardFormat.PNG_FAWE6_FAWE7 -> ClipboardFormat.PNG.getWriter(outputStream)
                else -> {
                    warning("Format $this not supported on 6.x fastasyncworldedit")
                    null
                }
            }
        }
    }
}