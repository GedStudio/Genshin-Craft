package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.ArgumentDimension;

public class ArgumentWorld implements EzArgumentType {
    @Override
    public ArgumentType<?> type() {
        return ArgumentDimension.a();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return ArgumentDimension.a((CommandContext<CommandListenerWrapper>) context, name).getWorld();
    }
}
