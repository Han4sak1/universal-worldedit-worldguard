package ink.ptms.uw.common.worldedit.session

import com.sk89q.worldedit.extent.Extent
import com.sk89q.worldedit.function.operation.Operation

/**
 * ink.ptms.uw.common.worldedit.session
 * @author Gei
 * @since 2025/01/21
 **/
abstract class UWPasteBuilder(open val holder: UWClipBoardHolder, open val targetExtent: Extent) {
    /**
     * 目标
     * @param vector Vector(6.x) BlockVector3(7.x), 与UWBukkitAdapter搭配使用
     * @return vector参数不匹配时为null
     */
    abstract fun to(vector: Any): UWPasteBuilder?

    /**
     * 在6.x时不会做任何更改
     * @return sourceMask参数在7.x不匹配时为null
     */
    abstract fun maskSource(sourceMask: Any): UWPasteBuilder?

    /**
     * 忽略空气
     */
    abstract fun ignoreAirBlocks(ignoreAirBlocks: Boolean): UWPasteBuilder

    /**
     * 在6.x时不会做任何更改
     */
    abstract fun copyEntities(copyEntities: Boolean): UWPasteBuilder

    /**
     * 在6.x时不会做任何更改
     */
    abstract fun copyBiomes(copyBiomes: Boolean): UWPasteBuilder

    /**
     * 获得Operation
     */
    abstract fun build(): Operation
}