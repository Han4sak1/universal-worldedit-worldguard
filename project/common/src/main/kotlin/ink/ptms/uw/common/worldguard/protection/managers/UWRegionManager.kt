package ink.ptms.uw.common.worldguard.protection.managers

import com.sk89q.worldguard.protection.ApplicableRegionSet
import com.sk89q.worldguard.protection.managers.RemovalStrategy
import com.sk89q.worldguard.protection.managers.storage.StorageException
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player

/**
 * ink.ptms.uw.common.worldguard
 *
 * @author Gei
 * @since 2025/01/21
 **/
abstract class UWRegionManager(val world: World) {

    abstract fun getName(): String

    @Throws(StorageException::class)
    abstract fun load()

    @Throws(StorageException::class)
    abstract fun save()

    @Throws(StorageException::class)
    abstract fun saveChanges(): Boolean

    /**
     * @param position Vector2D(WE 6.x) BlockVector2(WE7.x)
     */
    abstract fun loadChunk(position: Any?)

    /**
     * @param positions Vector2D(WE 6.x) BlockVector2(WE7.x)
     */
    abstract fun loadChunks(positions: Collection<Any>?)

    /**
     * @param position Vector2D(WE 6.x) BlockVector2(WE7.x)
     */
    abstract fun unloadChunk(position: Any?)


    abstract fun getRegions(): Map<String, ProtectedRegion>

    abstract fun setRegions(regions: Map<String?, ProtectedRegion>)

    abstract fun setRegions(regions: Collection<ProtectedRegion>?)

    abstract fun addRegion(region: ProtectedRegion?)

    abstract fun hasRegion(id: String?): Boolean

    abstract fun getRegion(id: String?): ProtectedRegion?

    abstract fun matchRegion(pattern: String): ProtectedRegion?

    abstract fun removeRegion(id: String?): Set<ProtectedRegion>?

    abstract fun removeRegion(id: String?, strategy: RemovalStrategy?): Set<ProtectedRegion>?

    /**
     * @param position Vector(WE 6.x) BlockVector3(WE7.x)
     */
    abstract fun getApplicableRegions(position: Any?): ApplicableRegionSet?


    abstract fun getApplicableRegions(region: ProtectedRegion?): ApplicableRegionSet

    /**
     * @param position Vector(WE 6.x) BlockVector3(WE7.x)
     */
    abstract fun getApplicableRegionsIDs(position: Any?): List<String>?

    abstract fun overlapsUnownedRegion(region: ProtectedRegion?, player: Player?): Boolean

    abstract fun size(): Int

    abstract fun getRegionCountOfPlayer(player: Player?): Int

    abstract fun getApplicableRegions(loc: Location): ApplicableRegionSet


}