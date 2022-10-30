package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;

public class ArgumentChat implements EzArgumentType {

    @Override
    public ArgumentType<?> type() {
        return net.minecraft.commands.arguments.ArgumentChat.a();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return net.minecraft.commands.arguments.ArgumentChat.a((CommandContext<CommandListenerWrapper>) context, name).getString();
    }

}
