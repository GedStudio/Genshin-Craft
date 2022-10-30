package net.deechael.genshin.lib.open.command;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface PlayerInvoker {

    int execute(Player sender, EzArgument argument) throws CommandSyntaxException;

}
