package net.deechael.genshin.lib.impl.command;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.deechael.genshin.lib.open.command.EzArgument;
import net.deechael.genshin.lib.open.command.EzArgumentTypes;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.server.DataPackResources;
import net.minecraft.server.MinecraftServer;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public final class EzArgumentHelperImpl implements EzArgument {

    private static CommandBuildContext commandBuildContext;

    public static CommandBuildContext getCommandBuildContext() {
        if (commandBuildContext == null)
            try {
                Field field = DataPackResources.class.getDeclaredField("c");
                field.setAccessible(true);
                commandBuildContext = (CommandBuildContext) field.get(MinecraftServer.getServer().at.b());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        return commandBuildContext;
    }

    private final CommandContext<CommandListenerWrapper> commandContext;
    private final CommandSender commandSender;

    EzArgumentHelperImpl(CommandContext<CommandListenerWrapper> commandContext) {
        this.commandContext = commandContext;
        this.commandSender = commandContext.getSource().getBukkitSender();
    }

    public String getAsString(String argumentName) {
        return StringArgumentType.getString(commandContext, argumentName);
    }

    public Integer getAsInteger(String argumentName) {
        return IntegerArgumentType.getInteger(commandContext, argumentName);
    }

    public Boolean getAsBoolean(String argumentName) {
        return BoolArgumentType.getBool(commandContext, argumentName);
    }

    public Double getAsDouble(String argumentName) {
        return DoubleArgumentType.getDouble(commandContext, argumentName);
    }

    public Long getAsLong(String argumentName) {
        return LongArgumentType.getLong(commandContext, argumentName);
    }

    public Float getAsFloat(String argumentName) {
        return FloatArgumentType.getFloat(commandContext, argumentName);
    }

    public List<Player> getAsPlayers(String argumentName) throws CommandSyntaxException {
        return (List<Player>) EzArgumentTypes.PLAYER.get(commandContext, argumentName);
    }

    public Location getAsLocation(String argumentName) throws CommandSyntaxException {
        Location location = (Location) EzArgumentTypes.LOCATION.get(commandContext, argumentName);
        if (commandSender instanceof Player player)
            location.setWorld(player.getWorld());
        return location;
    }

    public World getAsWorld(String argumentName) throws CommandSyntaxException {
        return (World) EzArgumentTypes.WORLD.get(commandContext, argumentName);
    }

    public Enchantment getAsEnchantment(String argumentName) throws CommandSyntaxException {
        return (Enchantment) EzArgumentTypes.ENCHANTMENT.get(commandContext, argumentName);
    }

    public String getAsChatMessage(String argumentName) throws CommandSyntaxException {
        return (String) EzArgumentTypes.CHAT.get(commandContext, argumentName);
    }

    public Location getAsBlockLocation(String argumentName) throws CommandSyntaxException {
        Location location = (Location) EzArgumentTypes.BLOCK_LOCATION.get(commandContext, argumentName);
        if (commandSender instanceof Player player)
            location.setWorld(player.getWorld());
        return location;
    }

    public ItemStack getAsItemStack(String argumentName, int amount) throws CommandSyntaxException {
        ItemStack itemStack = (ItemStack) EzArgumentTypes.ITEM_STACK.get(commandContext, argumentName);
        itemStack.setAmount(amount);
        return itemStack;
    }

    public Material getAsBlock(String argumentName) throws CommandSyntaxException {
        return (Material) EzArgumentTypes.BLOCK.get(commandContext, argumentName);
    }

    public List<Entity> getAsEntities(String argumentName) throws CommandSyntaxException {
        return (List<Entity>) EzArgumentTypes.ENTITIES.get(commandContext, argumentName);
    }

    public Entity getAsEntity(String argumentName) throws CommandSyntaxException {
        return (Entity) EzArgumentTypes.ENTITY.get(commandContext, argumentName);
    }

    public List<OfflinePlayer> getAsOfflinePlayer(String argumentName) throws CommandSyntaxException {
        return (List<OfflinePlayer>) EzArgumentTypes.OFFLINE_PLAYER.get(commandContext, argumentName);
    }

    public Attribute getAsAttribute(String argumentName) throws CommandSyntaxException {
        return (Attribute) EzArgumentTypes.ATTRIBUTE.get(commandContext, argumentName);
    }

    public PotionEffectType getAsPotionEffectType(String argumentName) throws CommandSyntaxException {
        return (PotionEffectType) EzArgumentTypes.POTION_EFFECT_TYPE.get(commandContext, argumentName);
    }

    public EntityType getAsEntityType(String argumentName) throws CommandSyntaxException {
        return (EntityType) EzArgumentTypes.ENTITY_TYPE.get(commandContext, argumentName);
    }

    public Particle getAsParticle(String argumentName) throws CommandSyntaxException {
        return (Particle) EzArgumentTypes.PLAYER.get(commandContext, argumentName);
    }

    public UUID getAsUUID(String argumentName) throws CommandSyntaxException {
        return (UUID) EzArgumentTypes.UUID.get(commandContext, argumentName);
    }

    public JsonObject getAsNBTTag(String argumentName) throws CommandSyntaxException {
        return (JsonObject) EzArgumentTypes.NBT_TAG.get(commandContext, argumentName);
    }

}
