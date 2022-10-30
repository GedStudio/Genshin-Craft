package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.ArgumentMobEffect;
import org.bukkit.craftbukkit.v1_19_R1.potion.CraftPotionEffectType;

public class ArgumentPotionEffectType implements EzArgumentType {
    @Override
    public ArgumentType<?> type() {
        return ArgumentMobEffect.a();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        return new CraftPotionEffectType(ArgumentMobEffect.a((CommandContext<CommandListenerWrapper>) context, name));
    }
}
