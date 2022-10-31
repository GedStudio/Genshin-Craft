package net.deechael.genshin.item.nova.behaviour

import net.deechael.genshin.lib.LibLauncher
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nmsutils.network.event.serverbound.ServerboundPlayerActionPacketEvent
import xyz.xenondevs.nova.item.behavior.ItemBehavior
import xyz.xenondevs.particle.ParticleBuilder
import xyz.xenondevs.particle.ParticleEffect

object BowItemBehaviour : ItemBehavior() {

    override fun handleRelease(player: Player, itemStack: ItemStack, event: ServerboundPlayerActionPacketEvent) {
        if (event.action != ServerboundPlayerActionPacket.Action.RELEASE_USE_ITEM)
            return
        // Shoot Arrow
        val playerLocation = player.eyeLocation
        val arrow = playerLocation.world!!.spawnArrow(playerLocation, getNearestEntity(playerLocation)!!.location.toVector().subtract(playerLocation.toVector()), 1.0f, 24f)
        Bukkit.getScheduler().runTaskAsynchronously(LibLauncher.getCore(), Runnable {
            while (!arrow.isDead) {
                ParticleBuilder(ParticleEffect.FLAME, arrow.location).display()
            }
        })
    }

    private fun getNearestEntity(location: Location): Entity? {
        var nearestDisctance = 12.0;
        var entity: Entity? = null;
        location.world!!.entities
            .asSequence()
            .filter { it !is Player }
            .filter { location.distance(it.location) <= 12.0 }
            .forEach {
                val distance = it.location.distance(location);
                if (distance < nearestDisctance) {
                    nearestDisctance = distance;
                    entity = it;
                }
            }
        return entity;
    }

}
