package ink.ptms.uw.common.worldedit.bukkit

import com.sk89q.worldedit.util.Location
import com.sk89q.worldedit.world.World
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector3
import ink.ptms.uw.common.worldedit.util.math.UWVector3

/**
 * ink.ptms.uw.common.worldedit
 *
 * @author Gei
 * @since 2025/01/21
 **/
interface UWBukkitAdapter {
    //世界
    /**
     * 将Bukkit World转换为WorldEdit World
     */
    fun adapt(world: org.bukkit.World): World

    /**
     * 将WorldEdit World转换为Bukkit World
     */
    fun adapt(world: World): org.bukkit.World

    //位置
    /**
     * 将Bukkit Location转换为WorldEdit Location
     */
    fun adapt(location: org.bukkit.Location): Location

    /**
     * 将WorldEdit Location转换为Bukkit Location
     */
    fun adapt(location: Location): org.bukkit.Location

    //向量
    /**
     * 将Bukkit Location转换为WorldEdit BlockVector3
     */
    fun asBlockVector3(location: org.bukkit.Location): UWBlockVector3

    /**
     * 将Bukkit Location转换为WorldEdit Vector3
     */
    fun asVector3(location: org.bukkit.Location): UWVector3

    /**
     * 将UWVector3 转换为Bukkit Location
     */
    fun asBukkitLocation(vector: UWVector3, world: org.bukkit.World): org.bukkit.Location? {
        return org.bukkit.Location(world, vector.x, vector.y, vector.z)
    }

    /**
     * 将UWBlockVector3 转换为Bukkit Location
     */
    fun asBukkitLocation(vector: UWBlockVector3, world: org.bukkit.World): org.bukkit.Location {
        return org.bukkit.Location(world, vector.x.toDouble(), vector.y.toDouble(), vector.z.toDouble())
    }
}
