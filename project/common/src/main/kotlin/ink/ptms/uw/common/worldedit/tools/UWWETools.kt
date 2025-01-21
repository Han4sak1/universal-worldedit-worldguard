package ink.ptms.uw.common.worldedit.tools

import com.sk89q.worldedit.extent.clipboard.Clipboard
import com.sk89q.worldedit.regions.Region
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin

/**
 * ink.ptms.uw.common.worldedit.tools
 *
 * @author Gei
 * @since 2025/01/21
 **/
interface UWWETools {
    /**
     *  刷新WE操作过后的区块光照(仅限FAWE)
     *  @return 执行次数
     **/
    fun fixLighting(world: World, selection: Region, mode: FixLightingRelightMode): Int

    /**
     * 获取剪贴板板选区最低点的实际位置.
     *
     * @param clipboard     剪切板.
     * @param pasteLocation 目的地.
     * @param rotation      旋转角.
     *
     * @return 实际最低点.
     */
    fun getMinimumLocation(clipboard: Clipboard, pasteLocation: Location, rotation: Double): Location {
        val originalOrigin = clipboard.origin.let { Vector(it.x, it.y, it.z) }
        val originalMinimumPoint = clipboard.region.minimumPoint.let { Vector(it.x, it.y, it.z) }

        val originalMinimumOffset = originalOrigin.subtract(originalMinimumPoint)

        val newOrigin = Vector(pasteLocation.x, pasteLocation.y, pasteLocation.z)
        val newMinimumPoint = newOrigin.subtract(originalMinimumOffset)

        val newRotatedMinimumPoint = rotateAround(newMinimumPoint, newOrigin, rotation)

        return Location(
            pasteLocation.world,
            newRotatedMinimumPoint.x,
            newRotatedMinimumPoint.y,
            newRotatedMinimumPoint.z
        )
    }


    /**
     * 获取剪贴板选区最高点的实际位置.
     *
     * @param clipboard     剪切板.
     * @param pasteLocation 目的地.
     * @param rotation      旋转角.
     *
     * @return 实际最高点.
     */
    fun getMaximumLocation(clipboard: Clipboard, pasteLocation: Location, rotation: Double): Location {
        val originalOrigin = clipboard.origin.let { Vector(it.x, it.y, it.z) }
        val originalMaximumPoint = clipboard.region.maximumPoint.let { Vector(it.x, it.y, it.z) }

        val originalMaximumOffset = originalOrigin.subtract(originalMaximumPoint)

        val newOrigin = Vector(pasteLocation.x, pasteLocation.y, pasteLocation.z)
        val newMaximumPoint = newOrigin.subtract(originalMaximumOffset)

        val newRotatedMaximumPoint = rotateAround(newMaximumPoint, newOrigin, rotation)

        return Location(
            pasteLocation.world,
            newRotatedMaximumPoint.x,
            newRotatedMaximumPoint.y,
            newRotatedMaximumPoint.z
        )
    }

    /**
     * 旋转向量.
     *
     * @param point  点
     * @param center 中心点
     * @param angle  角度
     * @return 最终结果.
     */
    fun rotateAround(point: Vector, center: Vector, angle: Double): Vector {
        var newAngle = angle
        newAngle = Math.toRadians(newAngle * -1)
        val rotatedX = cos(newAngle) * (point.x - center.x) - sin(newAngle) * (point.z - center.z) + center.x
        val rotatedZ = sin(newAngle) * (point.x - center.x) + cos(newAngle) * (point.z - center.z) + center.z

        return Vector(rotatedX, point.y, rotatedZ)
    }

    /**
     * 刷新等级
     */
    enum class FixLightingRelightMode {
        NONE, OPTIMAL, ALL
    }
}