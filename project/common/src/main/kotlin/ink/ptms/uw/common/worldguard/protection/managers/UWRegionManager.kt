package ink.ptms.uw.common.worldguard.protection.managers

import com.sk89q.worldguard.protection.ApplicableRegionSet
import com.sk89q.worldguard.protection.managers.RemovalStrategy
import com.sk89q.worldguard.protection.managers.storage.StorageException
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector2
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector3
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player

/**
 * ink.ptms.uw.common.worldguard.protection.managers
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

    abstract fun loadChunk(position: UWBlockVector2)

    abstract fun loadChunks(positions: Collection<UWBlockVector2>)

    abstract fun unloadChunk(position: UWBlockVector2)

    abstract fun getRegions(): Map<String, ProtectedRegion>

    abstract fun setRegions(regions: Map<String?, ProtectedRegion>)

    abstract fun setRegions(regions: Collection<ProtectedRegion>?)

    abstract fun addRegion(region: ProtectedRegion?)

    abstract fun hasRegion(id: String?): Boolean

    abstract fun getRegion(id: String?): ProtectedRegion?

    abstract fun matchRegion(pattern: String): ProtectedRegion?

    abstract fun removeRegion(id: String?): Set<ProtectedRegion>?

    abstract fun removeRegion(id: String?, strategy: RemovalStrategy?): Set<ProtectedRegion>?

    abstract fun getApplicableRegions(position: UWBlockVector3): ApplicableRegionSet?

    abstract fun getApplicableRegions(region: ProtectedRegion?): ApplicableRegionSet

    abstract fun getApplicableRegionsIDs(position: UWBlockVector3): List<String>?

    abstract fun overlapsUnownedRegion(region: ProtectedRegion?, player: Player?): Boolean

    abstract fun size(): Int

    abstract fun getRegionCountOfPlayer(player: Player?): Int

    abstract fun getApplicableRegions(loc: Location): ApplicableRegionSet


}