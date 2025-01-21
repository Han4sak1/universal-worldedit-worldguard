package ink.ptms.uw.common.worldedit.bukkit

import com.sk89q.worldedit.util.Location
import com.sk89q.worldedit.world.World

/**
 * ink.ptms.uw.common.worldguard
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
     * @return 6返回BlockVector 7返回BlockVector3
     */
    fun asBlockVector3(location: org.bukkit.Location): Any

    /**
     * 将Bukkit Location转换为WorldEdit Vector3
     * @return 6返回Vector 7返回Vector3
     */
    fun asVector3(location: org.bukkit.Location): Any

    /**
     * 将WorldEdit Vector(6:Vector/BlockVector 7:Vector3/BlockVector3) 转换为Bukkit Location
     */
    fun asBukkitLocation(vector: Any, world: org.bukkit.World): org.bukkit.Location?
}
