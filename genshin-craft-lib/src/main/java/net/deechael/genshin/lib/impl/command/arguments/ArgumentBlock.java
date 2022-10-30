package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.impl.command.EzArgumentHelperImpl;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.blocks.ArgumentTile;
import org.bukkit.craftbukkit.v1_19_R1.util.CraftMagicNumbers;

public class ArgumentBlock implements EzArgumentType {

    @Override
    public ArgumentType<?> type() {
        return ArgumentTile.a(EzArgumentHelperImpl.getCommandBuildContext());
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return CraftMagicNumbers.getMaterial(ArgumentTile.a((CommandContext<CommandListenerWrapper>) context, name).a().b());
    }

}
