package net.deechael.genshin.lib.impl.command.arguments;

import com.google.gson.JsonParser;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgumentType;
import net.deechael.genshin.lib.open.nbt.NBTItem;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ArgumentNBTTag implements EzArgumentType {
    @Override
    public ArgumentType<?> type() {
        return net.minecraft.commands.arguments.ArgumentNBTTag.a();
    }

    @Override
    public Object get(CommandContext<?> context, String name) throws CommandSyntaxException {
        NBTTagCompound nbtTagCompound = net.minecraft.commands.arguments.ArgumentNBTTag.a(context, name);
        nbtTagCompound.toString();
        NBTItem nbtItem = new NBTItem(new ItemStack(Material.DIAMOND));
        nbtItem.setCompound(nbtTagCompound);
        return JsonParser.parseString(nbtItem.toString()).getAsJsonObject();
    }
}
