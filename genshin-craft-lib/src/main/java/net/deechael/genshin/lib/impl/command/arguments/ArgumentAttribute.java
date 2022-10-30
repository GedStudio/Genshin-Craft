package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.ResourceKeyArgument;
import net.minecraft.core.IRegistry;
import org.bukkit.craftbukkit.v1_19_R1.attribute.CraftAttributeMap;

public class ArgumentAttribute implements EzArgumentType {

    @Override
    public ArgumentType<?> type() {
        return ResourceKeyArgument.a(IRegistry.w);
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return CraftAttributeMap.fromMinecraft(ResourceKeyArgument.a((CommandContext<CommandListenerWrapper>) context, name).c());
    }

}
