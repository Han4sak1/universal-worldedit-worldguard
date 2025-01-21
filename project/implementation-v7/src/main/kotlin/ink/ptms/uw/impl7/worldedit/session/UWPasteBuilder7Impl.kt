package ink.ptms.uw.impl7.worldedit.session

import com.sk89q.worldedit.extent.Extent
import com.sk89q.worldedit.function.mask.Mask
import com.sk89q.worldedit.function.operation.Operation
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldedit.session.ClipboardHolder
import ink.ptms.uw.common.worldedit.session.UWClipBoardHolder
import ink.ptms.uw.common.worldedit.session.UWPasteBuilder

/**
 * ink.ptms.uw.impl7.worldedit.session
 * @author Gei
 * @since 2025/01/21
 **/
class UWPasteBuilder7Impl(override val holder: UWClipBoardHolder, override val targetExtent: Extent) : UWPasteBuilder(holder, targetExtent) {
    private var to: BlockVector3? = null
    private var mask: Mask? = null
    private var ignoreAirBlocks: Boolean = false
    private var copyEntities: Boolean = true
    private var copyBiomes: Boolean = false

    override fun to(vector: Any): UWPasteBuilder? {
        return if (vector !is BlockVector3) null
        else {
            this.to = vector
            this
        }
    }

    override fun maskSource(sourceMask: Any): UWPasteBuilder? {
        return if (sourceMask !is Mask) null
        else {
            this.mask = sourceMask
            this
        }
    }

    override fun ignoreAirBlocks(ignoreAirBlocks: Boolean): UWPasteBuilder {
        this.ignoreAirBlocks = ignoreAirBlocks
        return this
    }

    override fun copyEntities(copyEntities: Boolean): UWPasteBuilder {
        this.copyEntities = copyEntities
        return this
    }

    override fun copyBiomes(copyBiomes: Boolean): UWPasteBuilder {
        this.copyBiomes = copyBiomes
        return this
    }

    override fun build(): Operation {
        return ClipboardHolder(holder.clipboard)
            .apply { this.transform = holder.transform }
            .createPaste(targetExtent)
            .to(this.to)
            .ignoreAirBlocks(this.ignoreAirBlocks)
            .copyEntities(this.copyEntities)
            .copyBiomes(this.copyBiomes)
            .build()
    }
}