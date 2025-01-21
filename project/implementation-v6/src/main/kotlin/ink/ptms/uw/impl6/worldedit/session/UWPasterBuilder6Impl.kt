package ink.ptms.uw.impl6.worldedit.session

import com.sk89q.worldedit.Vector
import com.sk89q.worldedit.extent.Extent
import com.sk89q.worldedit.function.operation.Operation
import com.sk89q.worldedit.session.ClipboardHolder
import ink.ptms.uw.common.worldedit.session.UWClipBoardHolder
import ink.ptms.uw.common.worldedit.session.UWPasteBuilder
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector3

/**
 * ink.ptms.uw.impl6.worldedit.session
 * @author Gei
 * @since 2025/01/21
 **/
class UWPasterBuilder6Impl(override val holder: UWClipBoardHolder, override val targetExtent: Extent) : UWPasteBuilder(holder, targetExtent) {
    private var to: Vector? = null
    private var ignoreAirBlocks: Boolean = false

    override fun to(vector: UWBlockVector3): UWPasteBuilder {
        this.to = Vector(vector.x, vector.y, vector.z)
        return this
    }

    override fun maskSource(sourceMask: Any): UWPasteBuilder {
        return this
    }

    override fun ignoreAirBlocks(ignoreAirBlocks: Boolean): UWPasteBuilder {
        this.ignoreAirBlocks = ignoreAirBlocks
        return this
    }

    override fun copyEntities(copyEntities: Boolean): UWPasteBuilder {
        return this
    }

    override fun copyBiomes(copyBiomes: Boolean): UWPasteBuilder {
        return this
    }

    override fun build(): Operation {
        return ClipboardHolder(holder.clipboard, null)
            .apply { this.transform = holder.transform }
            .createPaste(targetExtent, null)
            .to(this.to)
            .ignoreAirBlocks(this.ignoreAirBlocks)
            .build()
    }
}