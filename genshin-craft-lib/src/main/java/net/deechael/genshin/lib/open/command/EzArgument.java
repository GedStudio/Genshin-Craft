package net.deechael.genshin.lib.open.command;

import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.UUID;

public interface EzArgument {

    String getAsString(String argumentName);

    Integer getAsInteger(String argumentName);

    Boolean getAsBoolean(String argumentName);

    Double getAsDouble(String argumentName);

    Long getAsLong(String argumentName);

    Float getAsFloat(String argumentName);

    List<Player> getAsPlayers(String argumentName) throws CommandSyntaxException;

    Location getAsLocation(String argumentName) throws CommandSyntaxException;

    public World getAsWorld(String argumentName) throws CommandSyntaxException;

    Enchantment getAsEnchantment(String argumentName) throws CommandSyntaxException;

    String getAsChatMessage(String argumentName) throws CommandSyntaxException;

    Location getAsBlockLocation(String argumentName) throws CommandSyntaxException;

    ItemStack getAsItemStack(String argumentName, int amount) throws CommandSyntaxException;

    Material getAsBlock(String argumentName) throws CommandSyntaxException;

    List<Entity> getAsEntities(String argumentName) throws CommandSyntaxException;

    Entity getAsEntity(String argumentName) throws CommandSyntaxException;

    List<OfflinePlayer> getAsOfflinePlayer(String argumentName) throws CommandSyntaxException;

    Attribute getAsAttribute(String argumentName) throws CommandSyntaxException;

    PotionEffectType getAsPotionEffectType(String argumentName) throws CommandSyntaxException;

    EntityType getAsEntityType(String argumentName) throws CommandSyntaxException;

    Particle getAsParticle(String argumentName) throws CommandSyntaxException;

    UUID getAsUUID(String argumentName) throws CommandSyntaxException;

    JsonObject getAsNBTTag(String argumentName) throws CommandSyntaxException;

}
