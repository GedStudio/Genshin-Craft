package net.deechael.genshin.lib.open.command;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface CommandSenderInvoker {

    int execute(CommandSender sender, EzArgument argument) throws CommandSyntaxException;

}
