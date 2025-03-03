package ink.ptms.uw.impl7.worldguard.protection.managers

import com.sk89q.worldedit.math.BlockVector2
import com.sk89q.worldedit.math.BlockVector3
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.bukkit.BukkitPlayer
import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import ink.ptms.uw.common.worldedit.WorldEdit
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector2
import ink.ptms.uw.common.worldedit.util.math.UWBlockVector3
import ink.ptms.uw.common.worldguard.protection.managers.UWRegionManager
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player

/**
 * ink.ptms.uw.impl7.worldguard
 *
 * @author Gei
 * @since 2025/01/21
 **/
class UWRegionManager7Impl(world: World): UWRegionManager(world) {

    val wgRegionManager = WorldGuard.getInstance().platform.regionContainer.get(WorldEdit.API.bukkitAdapter.adapt(world))!!

    override fun getName(): String {
        return wgRegionManager.name
    }

    override fun load() {
        wgRegionManager.load()
    }

    override fun save() {
        wgRegionManager.save()
    }

    override fun saveChanges(): Boolean {
        return wgRegionManager.saveChanges()
    }

    override fun loadChunk(position: UWBlockVector2) {
        wgRegionManager.loadChunk(BlockVector2.at(position.x, position.z))
    }

    override fun loadChunks(positions: Collection<UWBlockVector2>) {
        val vectors = positions.map { BlockVector2.at(it.x, it.z) }
        wgRegionManager.loadChunks(vectors)
    }

    override fun unloadChunk(position: UWBlockVector2) {
        val vector = BlockVector2.at(position.x, position.z)
        wgRegionManager.unloadChunk(vector)
    }

    override fun getRegions(): Map<String, com.sk89q.worldguard.protection.regions.ProtectedRegion> {
        return wgRegionManager.regions
    }

    override fun setRegions(regions: Map<String?, com.sk89q.worldguard.protection.regions.ProtectedRegion>) {
        wgRegionManager.regions = regions
    }

    override fun setRegions(regions: Collection<com.sk89q.worldguard.protection.regions.ProtectedRegion>?) {
        wgRegionManager.setRegions(regions)
    }

    override fun addRegion(region: com.sk89q.worldguard.protection.regions.ProtectedRegion?) {
        wgRegionManager.addRegion(region)
    }

    override fun hasRegion(id: String?): Boolean {
        return wgRegionManager.hasRegion(id)
    }

    override fun getRegion(id: String?): com.sk89q.worldguard.protection.regions.ProtectedRegion? {
        return wgRegionManager.getRegion(id)
    }

    override fun matchRegion(pattern: String): com.sk89q.worldguard.protection.regions.ProtectedRegion? {
        return wgRegionManager.matchRegion(pattern)
    }

    override fun removeRegion(id: String?): Set<com.sk89q.worldguard.protection.regions.ProtectedRegion>? {
        return wgRegionManager.removeRegion(id)
    }

    override fun removeRegion(id: String?, strategy: com.sk89q.worldguard.protection.managers.RemovalStrategy?): Set<com.sk89q.worldguard.protection.regions.ProtectedRegion>? {
        return wgRegionManager.removeRegion(id, strategy)
    }

    override fun getApplicableRegions(position: UWBlockVector3): com.sk89q.worldguard.protection.ApplicableRegionSet? {
        val vector = BlockVector3.at(position.x, position.y, position.z)
        return wgRegionManager.getApplicableRegions(vector)
    }

    override fun getApplicableRegions(region: com.sk89q.worldguard.protection.regions.ProtectedRegion?): com.sk89q.worldguard.protection.ApplicableRegionSet {
        return wgRegionManager.getApplicableRegions(region)
    }

    override fun getApplicableRegionsIDs(position: UWBlockVector3): List<String>? {
        val vector = BlockVector3.at(position.x, position.y, position.z)
        return wgRegionManager.getApplicableRegionsIDs(vector)
    }

    override fun overlapsUnownedRegion(region: com.sk89q.worldguard.protection.regions.ProtectedRegion?, player: Player?): Boolean {
        return wgRegionManager.overlapsUnownedRegion(region, BukkitPlayer(WorldGuardPlugin.inst(), player))
    }

    override fun size(): Int {
        return wgRegionManager.size()
    }

    override fun getRegionCountOfPlayer(player: Player?): Int {
        return wgRegionManager.getRegionCountOfPlayer(BukkitPlayer(WorldGuardPlugin.inst(), player))
    }

    override fun getApplicableRegions(loc: Location): com.sk89q.worldguard.protection.ApplicableRegionSet {
        return wgRegionManager.getApplicableRegions(BlockVector3.at(loc.x, loc.y, loc.z))
    }
}