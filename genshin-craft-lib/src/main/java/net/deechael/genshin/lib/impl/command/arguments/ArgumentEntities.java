package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.ArgumentEntity;
import net.minecraft.world.entity.Entity;

public class ArgumentEntities implements EzArgumentType {

    @Override
    public ArgumentType<?> type() {
        return ArgumentEntity.b();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return ArgumentEntity.b((CommandContext<CommandListenerWrapper>) context, name).stream().map(Entity::getBukkitEntity).toList();
    }

}
