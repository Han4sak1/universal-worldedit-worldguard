package ink.ptms.uw.impl6.worldedit.session

import com.sk89q.worldedit.extent.Extent
import com.sk89q.worldedit.extent.clipboard.Clipboard
import ink.ptms.uw.common.worldedit.session.UWClipBoardHolder
import ink.ptms.uw.common.worldedit.session.UWPasteBuilder

/**
 * ink.ptms.uw.impl6.worldedit.session
 * @author Gei
 * @since 2025/01/21
 **/
class UWClipboardHolder6Impl(override val clipboard: Clipboard): UWClipBoardHolder(clipboard) {
    override fun createPaste(targetExtent: Extent): UWPasteBuilder {
        return UWPasterBuilder6Impl(this, targetExtent)
    }
}