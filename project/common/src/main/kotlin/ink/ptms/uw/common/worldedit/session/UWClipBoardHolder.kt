package ink.ptms.uw.common.worldedit.session

import com.sk89q.worldedit.extent.Extent
import com.sk89q.worldedit.extent.clipboard.Clipboard
import com.sk89q.worldedit.math.transform.Identity
import com.sk89q.worldedit.math.transform.Transform

/**
 * ink.ptms.uw.common.worldedit.session
 * @author Gei
 * @since 2025/01/21
 **/
abstract class UWClipBoardHolder(open val clipboard: Clipboard) {

    var transform: Transform = Identity()

    abstract fun createPaste(targetExtent: Extent): UWPasteBuilder
}