package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import org.bukkit.craftbukkit.v1_19_R1.CraftParticle;

public class ArgumentParticle implements EzArgumentType {
    @Override
    public ArgumentType<?> type() {
        return net.minecraft.commands.arguments.ArgumentParticle.a();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return CraftParticle.toBukkit(net.minecraft.commands.arguments.ArgumentParticle.a((CommandContext<CommandListenerWrapper>) context, name));
    }
}
