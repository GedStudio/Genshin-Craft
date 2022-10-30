package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.impl.command.EzArgumentHelperImpl;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;

public class ArgumentItemStack implements EzArgumentType {
    @Override
    public ArgumentType<?> type() {
        return net.minecraft.commands.arguments.item.ArgumentItemStack.a(EzArgumentHelperImpl.getCommandBuildContext());
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return CraftItemStack.asBukkitCopy(net.minecraft.commands.arguments.item.ArgumentItemStack.a(context, name).a(1, false));
    }
}
