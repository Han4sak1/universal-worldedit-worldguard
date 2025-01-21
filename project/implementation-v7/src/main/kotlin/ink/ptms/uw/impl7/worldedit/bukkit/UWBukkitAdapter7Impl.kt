package ink.ptms.uw.impl7.worldedit.bukkit

import com.sk89q.worldedit.bukkit.BukkitAdapter
import ink.ptms.uw.common.worldedit.bukkit.UWBukkitAdapter
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector3
import ink.ptms.uw.common.worldedit.util.math.UWVector3
import org.bukkit.Location
import org.bukkit.World

/**
 * ink.ptms.uw.impl7.worldedit
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

    override fun asBlockVector3(location: Location): UWBlockVector3 {
        return UWBlockVector3(location.blockX, location.blockY, location.blockZ)
    }

    override fun asVector3(location: Location): UWVector3 {
        return UWVector3(location.x, location.y, location.z)
    }
}