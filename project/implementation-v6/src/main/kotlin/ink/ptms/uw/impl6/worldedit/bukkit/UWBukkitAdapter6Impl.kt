package ink.ptms.uw.impl6.worldedit.bukkit

import ink.ptms.uw.common.worldedit.bukkit.UWBukkitAdapter
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector3
import ink.ptms.uw.common.worldedit.util.math.UWVector3
import org.bukkit.Location
import org.bukkit.World
import taboolib.library.reflex.ReflexClass

/**
 * ink.ptms.uw.impl6.worldedit
 *
 * @author Gei
 * @since 2025/01/21
 **/
class UWBukkitAdapter6Impl: UWBukkitAdapter {

    //你给adapter设成package private我还adapt个锅巴
    private val bukkitAdapter6 by lazy {
        ReflexClass.of(
            Class.forName("com.sk89q.worldedit.bukkit.BukkitAdapter")
        )
    }

    override fun adapt(world: World): com.sk89q.worldedit.world.World {
        return bukkitAdapter6.getLocalMethod("adapt", org.bukkit.World::class.java).invokeStatic(world) as com.sk89q.worldedit.world.World
    }

    override fun adapt(location: Location): com.sk89q.worldedit.util.Location {
        return bukkitAdapter6.getLocalMethod("adapt", Location::class.java).invokeStatic(location) as com.sk89q.worldedit.util.Location
    }

    override fun adapt(world: com.sk89q.worldedit.world.World): World {
        return bukkitAdapter6.getLocalMethod("adapt", com.sk89q.worldedit.world.World::class.java).invokeStatic(world) as World
    }

    override fun adapt(location: com.sk89q.worldedit.util.Location): Location {
        return bukkitAdapter6.getLocalMethod("adapt", com.sk89q.worldedit.util.Location::class.java).invokeStatic(location) as Location
    }

    override fun asBlockVector3(location: Location): UWBlockVector3 {
        return UWBlockVector3(location.blockX, location.blockY, location.blockZ)
    }

    override fun asVector3(location: Location): UWVector3 {
        return UWVector3(location.x, location.y, location.z)
    }
}