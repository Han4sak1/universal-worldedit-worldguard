package ink.ptms.uw.impl7.worldedit.extent.clipboard.io

import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat
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
 * ink.ptms.uw.impl7.worldedit
 *
 * @author Gei
 * @since 2025/01/21
 **/
class UWClipboardFormats7Impl : UWClipBoardFormats {
    override val aliasesMap: HashMap<String, BuiltinClipBoardFormat> =
        HashMap<String, BuiltinClipBoardFormat>().apply {
            BuiltinClipBoardFormat.entries.forEach { format ->
                format.aliases.forEach { alias -> this[alias] = format }
            }
        }

    override fun isFormat(file: File, format: BuiltinClipBoardFormat): Boolean {
        if (!WorldEdit.API.isFAWE) {
            //WorldEdit
            if (WorldEdit.API.majorVersion < 70300) {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.isFormat(file)
                    BuiltinClipBoardFormat.SPONGE_SCHEM_WE730DOWN -> BuiltInClipboardFormat.SPONGE_SCHEMATIC.isFormat(file)
                    else -> {
                        warning("Format $format not supported on worldedit version 7.3 below")
                        false
                    }
                }
            } else {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.isFormat(file)
                    BuiltinClipBoardFormat.SPONGE_V1_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V1_SCHEMATIC").isFormat(file)
                    BuiltinClipBoardFormat.SPONGE_V2_SCHEMATIC_WE730UP_FAWE_2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V2_SCHEMATIC").isFormat(file)
                    BuiltinClipBoardFormat.SPONGE_V3_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V3_SCHEMATIC").isFormat(file)
                    else -> {
                        warning("Format $format not supported on worldedit version 7.3 up")
                        false
                    }
                }
            }
        } else {
            //FAWE
            if (WorldEdit.API.majorVersion <= 21202) {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.isFormat(file)
                    BuiltinClipBoardFormat.NBT_FAWE6_FAWE7 -> BuiltInClipboardFormat.MINECRAFT_STRUCTURE.isFormat(file)
                    BuiltinClipBoardFormat.PNG_FAWE6_FAWE7 -> BuiltInClipboardFormat.PNG.isFormat(file)
                    BuiltinClipBoardFormat.FAST_FAWE6_FAWE7_2122DOWN -> BuiltInClipboardFormat.FAST.isFormat(file)
                    BuiltinClipBoardFormat.BROKENENTITY_FAWE7 -> BuiltInClipboardFormat.BROKENENTITY.isFormat(file)
                    else -> {
                        warning("Format $format not supported on fastasyncworldedit version 2.12.2 below")
                        false
                    }
                }
            } else {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.isFormat(file)
                    BuiltinClipBoardFormat.NBT_FAWE6_FAWE7 -> BuiltInClipboardFormat.MINECRAFT_STRUCTURE.isFormat(file)
                    BuiltinClipBoardFormat.PNG_FAWE6_FAWE7 -> BuiltInClipboardFormat.PNG.isFormat(file)
                    BuiltinClipBoardFormat.FAST_V2_FAWE7_2122UP -> BuiltInClipboardFormat.valueOf("FAST_V2").isFormat(file)
                    BuiltinClipBoardFormat.FAST_V3_FAWE7_2122UP -> BuiltInClipboardFormat.valueOf("FAST_V3").isFormat(file)
                    BuiltinClipBoardFormat.SPONGE_V1_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V1_SCHEMATIC").isFormat(file)
                    BuiltinClipBoardFormat.SPONGE_V2_SCHEMATIC_WE730UP_FAWE_2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V2_SCHEMATIC").isFormat(file)
                    BuiltinClipBoardFormat.SPONGE_V3_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V3_SCHEMATIC").isFormat(file)
                    BuiltinClipBoardFormat.BROKENENTITY_FAWE7 -> BuiltInClipboardFormat.BROKENENTITY.isFormat(file)
                    else -> {
                        warning("Format $format not supported on fastasyncworldedit version 2.12.2 below")
                        false
                    }
                }
            }
        }
    }

    override fun getReader(format: BuiltinClipBoardFormat, inputStream: InputStream): ClipboardReader? {
        if (!WorldEdit.API.isFAWE) {
            //WorldEdit
            if (WorldEdit.API.majorVersion < 70300) {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.getReader(inputStream)
                    BuiltinClipBoardFormat.SPONGE_SCHEM_WE730DOWN -> BuiltInClipboardFormat.SPONGE_SCHEMATIC.getReader(inputStream)
                    else -> {
                        warning("Format $this not supported on worldedit version 7.3 below")
                        null
                    }
                }
            } else {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.getReader(inputStream)
                    BuiltinClipBoardFormat.SPONGE_V1_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V1_SCHEMATIC").getReader(inputStream)
                    BuiltinClipBoardFormat.SPONGE_V2_SCHEMATIC_WE730UP_FAWE_2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V2_SCHEMATIC").getReader(inputStream)
                    BuiltinClipBoardFormat.SPONGE_V3_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V3_SCHEMATIC").getReader(inputStream)
                    else -> {
                        warning("Format $this not supported on worldedit version 7.3 up")
                        null
                    }
                }
            }
        } else {
            //FAWE
            if (WorldEdit.API.majorVersion <= 21202) {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.getReader(inputStream)
                    BuiltinClipBoardFormat.NBT_FAWE6_FAWE7 -> BuiltInClipboardFormat.MINECRAFT_STRUCTURE.getReader(inputStream)
                    BuiltinClipBoardFormat.PNG_FAWE6_FAWE7 -> BuiltInClipboardFormat.PNG.getReader(inputStream)
                    BuiltinClipBoardFormat.FAST_FAWE6_FAWE7_2122DOWN -> BuiltInClipboardFormat.FAST.getReader(inputStream)
                    BuiltinClipBoardFormat.BROKENENTITY_FAWE7 -> BuiltInClipboardFormat.BROKENENTITY.getReader(inputStream)
                    else -> {
                        warning("Format $this not supported on fastasyncworldedit version 2.12.2 below")
                        null
                    }
                }
            } else {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.getReader(inputStream)
                    BuiltinClipBoardFormat.NBT_FAWE6_FAWE7 -> BuiltInClipboardFormat.MINECRAFT_STRUCTURE.getReader(inputStream)
                    BuiltinClipBoardFormat.PNG_FAWE6_FAWE7 -> BuiltInClipboardFormat.PNG.getReader(inputStream)
                    BuiltinClipBoardFormat.FAST_V2_FAWE7_2122UP -> BuiltInClipboardFormat.valueOf("FAST_V2").getReader(inputStream)
                    BuiltinClipBoardFormat.FAST_V3_FAWE7_2122UP -> BuiltInClipboardFormat.valueOf("FAST_V3").getReader(inputStream)
                    BuiltinClipBoardFormat.SPONGE_V1_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V1_SCHEMATIC").getReader(inputStream)
                    BuiltinClipBoardFormat.SPONGE_V2_SCHEMATIC_WE730UP_FAWE_2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V2_SCHEMATIC").getReader(inputStream)
                    BuiltinClipBoardFormat.SPONGE_V3_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V3_SCHEMATIC").getReader(inputStream)
                    BuiltinClipBoardFormat.BROKENENTITY_FAWE7 -> BuiltInClipboardFormat.BROKENENTITY.getReader(inputStream)
                    else -> {
                        warning("Format $this not supported on fastasyncworldedit version 2.12.2 below")
                        null
                    }
                }
            }
        }
    }

    override fun getWriter(format: BuiltinClipBoardFormat, outputStream: OutputStream): ClipboardWriter? {
        if (!WorldEdit.API.isFAWE) {
            //WorldEdit
            if (WorldEdit.API.majorVersion < 70300) {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.getWriter(outputStream)
                    BuiltinClipBoardFormat.SPONGE_SCHEM_WE730DOWN -> BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(outputStream)
                    else -> {
                        warning("Format $this not supported on worldedit version 7.3 below")
                        null
                    }
                }
            } else {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.getWriter(outputStream)
                    BuiltinClipBoardFormat.SPONGE_V1_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V1_SCHEMATIC").getWriter(outputStream)
                    BuiltinClipBoardFormat.SPONGE_V2_SCHEMATIC_WE730UP_FAWE_2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V2_SCHEMATIC").getWriter(outputStream)
                    BuiltinClipBoardFormat.SPONGE_V3_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V3_SCHEMATIC").getWriter(outputStream)
                    else -> {
                        warning("Format $this not supported on worldedit version 7.3 up")
                        null
                    }
                }
            }
        } else {
            //FAWE
            if (WorldEdit.API.majorVersion <= 21202) {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.getWriter(outputStream)
                    BuiltinClipBoardFormat.NBT_FAWE6_FAWE7 -> BuiltInClipboardFormat.MINECRAFT_STRUCTURE.getWriter(outputStream)
                    BuiltinClipBoardFormat.PNG_FAWE6_FAWE7 -> BuiltInClipboardFormat.PNG.getWriter(outputStream)
                    BuiltinClipBoardFormat.FAST_FAWE6_FAWE7_2122DOWN -> BuiltInClipboardFormat.FAST.getWriter(outputStream)
                    BuiltinClipBoardFormat.BROKENENTITY_FAWE7 -> BuiltInClipboardFormat.BROKENENTITY.getWriter(outputStream)
                    else -> {
                        warning("Format $this not supported on fastasyncworldedit version 2.12.2 below")
                        null
                    }
                }
            } else {
                return when (format) {
                    BuiltinClipBoardFormat.MCEDIT_SCHEMATIC_WE6_WE7_FAWE6_FAWE7 -> BuiltInClipboardFormat.MCEDIT_SCHEMATIC.getWriter(outputStream)
                    BuiltinClipBoardFormat.NBT_FAWE6_FAWE7 -> BuiltInClipboardFormat.MINECRAFT_STRUCTURE.getWriter(outputStream)
                    BuiltinClipBoardFormat.PNG_FAWE6_FAWE7 -> BuiltInClipboardFormat.PNG.getWriter(outputStream)
                    BuiltinClipBoardFormat.FAST_V2_FAWE7_2122UP -> BuiltInClipboardFormat.valueOf("FAST_V2").getWriter(outputStream)
                    BuiltinClipBoardFormat.FAST_V3_FAWE7_2122UP -> BuiltInClipboardFormat.valueOf("FAST_V3").getWriter(outputStream)
                    BuiltinClipBoardFormat.SPONGE_V1_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V1_SCHEMATIC").getWriter(outputStream)
                    BuiltinClipBoardFormat.SPONGE_V2_SCHEMATIC_WE730UP_FAWE_2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V2_SCHEMATIC").getWriter(outputStream)
                    BuiltinClipBoardFormat.SPONGE_V3_SCHEMATIC_WE730UP_FAWE2122UP -> BuiltInClipboardFormat.valueOf("SPONGE_V3_SCHEMATIC").getWriter(outputStream)
                    BuiltinClipBoardFormat.BROKENENTITY_FAWE7 -> BuiltInClipboardFormat.BROKENENTITY.getWriter(outputStream)
                    else -> {
                        warning("Format $this not supported on fastasyncworldedit version 2.12.2 below")
                        null
                    }
                }
            }
        }
    }
}