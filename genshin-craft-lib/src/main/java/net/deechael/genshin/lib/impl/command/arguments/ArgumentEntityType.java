package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.ArgumentEntitySummon;
import org.bukkit.entity.EntityType;

public class ArgumentEntityType implements EzArgumentType {
    @Override
    public ArgumentType<?> type() {
        return ArgumentEntitySummon.a();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return EntityType.fromName(ArgumentEntitySummon.a((CommandContext<CommandListenerWrapper>) context, name).a());
    }
}
