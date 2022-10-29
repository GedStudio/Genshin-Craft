package net.deechael.genshin.item.nova.behaviour

import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nmsutils.network.event.serverbound.ServerboundPlayerActionPacketEvent
import xyz.xenondevs.nova.item.behavior.ItemBehavior

object BowItemBehaviour : ItemBehavior() {

    override fun handleRelease(player: Player, itemStack: ItemStack, event: ServerboundPlayerActionPacketEvent) {
        if (event.action != ServerboundPlayerActionPacket.Action.RELEASE_USE_ITEM)
            return;
        // Shoot Arrow
    }

}