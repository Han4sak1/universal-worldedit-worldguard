package ink.ptms.uw.impl7.worldedit.bukkit

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldedit.math.Vector3
import ink.ptms.uw.common.worldedit.bukkit.UWBukkitAdapter
import org.bukkit.Location
import org.bukkit.World

/**
 * ink.ptms.uw.common.worldguard
 *
 * @author Gei
 * @since 2025/01/21
 **/
class UWBukkitAdapter7Impl: UWBukkitAdapter {

    override fun adapt(world: World): com.sk89q.worldedit.world.World {
        return BukkitAdapter.adapt(world)
    }

    override fun adapt(location: Location): com.sk89q.worldedit.util.Location {
        return BukkitAdapter.adapt(location)
    }

    override fun adapt(world: com.sk89q.worldedit.world.World): World {
        return BukkitAdapter.adapt(world)
    }

    override fun adapt(location: com.sk89q.worldedit.util.Location): Location {
        return BukkitAdapter.adapt(location)
    }

    override fun asBlockVector3(location: Location): Any {
        return BlockVector3.at(location.x, location.y, location.z)
    }

    override fun asVector3(location: Location): Any {
        return Vector3.at(location.x, location.y, location.z)
    }

    override fun asBukkitLocation(vector: Any, world: World): Location? {
        //WE7 BlockVector3从Vector3中独立了出来, 需要额外判断
        return when (vector) {
            is Vector3 -> Location(world, vector.x, vector.y, vector.z)
            is BlockVector3 -> Location(world, vector.x.toDouble(), vector.y.toDouble(), vector.z.toDouble())
            else -> null
        }
    }
}