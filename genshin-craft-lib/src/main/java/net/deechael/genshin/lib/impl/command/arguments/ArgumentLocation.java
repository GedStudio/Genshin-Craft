package net.deechael.genshin.lib.impl.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.coordinates.ArgumentVec3;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Location;

public class ArgumentLocation implements EzArgumentType {
    @Override
    public ArgumentType<?> type() {
        return ArgumentVec3.a();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        Vec3D vec3D = ArgumentVec3.a((CommandContext<CommandListenerWrapper>) context, name);
        return new Location(null, vec3D.c, vec3D.d, vec3D.e);
    }
}
