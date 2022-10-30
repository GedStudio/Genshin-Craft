package net.deechael.genshin.lib.open.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public interface EzArgumentType {

    ArgumentType<?> type();

    Object get(CommandContext<?> context, String name) throws CommandSyntaxException;

}
