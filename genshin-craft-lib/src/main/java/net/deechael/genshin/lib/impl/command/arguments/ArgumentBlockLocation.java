package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.coordinates.ArgumentPosition;
import net.minecraft.core.BlockPosition;
import org.bukkit.Location;

public class ArgumentBlockLocation implements EzArgumentType {

    @Override
    public ArgumentType<?> type() {
        return ArgumentPosition.a();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        BlockPosition blockPosition = ArgumentPosition.a((CommandContext<CommandListenerWrapper>) context, name);
        return new Location(null, blockPosition.u(), blockPosition.v(), blockPosition.w());
    }

}
