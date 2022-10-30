package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.ArgumentEntity;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;

public class ArgumentPlayer implements EzArgumentType {
    @Override
    public ArgumentType<?> type() {
        return ArgumentEntity.d();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return ArgumentEntity.f((CommandContext<CommandListenerWrapper>) context, name).stream().map(EntityPlayer::getBukkitEntity).map(CraftPlayer::getPlayer).toList();
    }

}
