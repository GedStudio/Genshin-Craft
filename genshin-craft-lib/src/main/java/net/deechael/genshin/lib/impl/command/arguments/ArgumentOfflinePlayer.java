package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.ArgumentProfile;
import org.bukkit.Bukkit;

public class ArgumentOfflinePlayer implements EzArgumentType {
    @Override
    public ArgumentType<?> type() {
        return ArgumentProfile.a();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return ArgumentProfile.a((CommandContext<CommandListenerWrapper>) context, name).stream().map(GameProfile::getId).map(Bukkit::getOfflinePlayer).toList();
    }
}
