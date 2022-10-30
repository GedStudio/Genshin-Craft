package net.deechael.genshin.lib.open.command;

import com.mojang.brigadier.arguments.*;
import net.deechael.genshin.lib.impl.command.arguments.*;

public class EzArgumentTypes {

    public final static ArgumentAttribute ATTRIBUTE = new ArgumentAttribute();
    public final static ArgumentBlock BLOCK = new ArgumentBlock();
    public final static ArgumentBlockLocation BLOCK_LOCATION = new ArgumentBlockLocation();
    public final static ArgumentChat CHAT = new ArgumentChat();
    public final static ArgumentEnchantment ENCHANTMENT = new ArgumentEnchantment();
    public final static ArgumentEntities ENTITIES = new ArgumentEntities();
    public final static ArgumentEntity ENTITY = new ArgumentEntity();
    public final static ArgumentEntityType ENTITY_TYPE = new ArgumentEntityType();
    public final static ArgumentItemStack ITEM_STACK = new ArgumentItemStack();
    public final static ArgumentLocation LOCATION = new ArgumentLocation();
    public final static ArgumentNBTTag NBT_TAG = new ArgumentNBTTag();
    public final static ArgumentOfflinePlayer OFFLINE_PLAYER = new ArgumentOfflinePlayer();
    public final static ArgumentParticle PARTICLE = new ArgumentParticle();
    public final static ArgumentPlayer PLAYER = new ArgumentPlayer();
    public final static ArgumentPotionEffectType POTION_EFFECT_TYPE = new ArgumentPotionEffectType();
    public final static ArgumentUUID UUID = new ArgumentUUID();
    public final static ArgumentWorld WORLD = new ArgumentWorld();

    public static ArgumentType<String> string() {
        return StringArgumentType.string();
    }

    public static ArgumentType<String> stringWord() {
        return StringArgumentType.word();
    }

    public static ArgumentType<String> greedyString() {
        return StringArgumentType.greedyString();
    }

    public static ArgumentType<Integer> integer() {
        return integer(-2147483648);
    }

    public static ArgumentType<Integer> integer(int min) {
        return integer(min, 2147483647);
    }

    public static ArgumentType<Integer> integer(int min, int max) {
        return IntegerArgumentType.integer(min, max);
    }

    public static ArgumentType<Boolean> bool() {
        return BoolArgumentType.bool();
    }

    public static ArgumentType<Double> doubleArg() {
        return doubleArg(-1.7976931348623157E308D);
    }

    public static ArgumentType<Double> doubleArg(double min) {
        return doubleArg(min, 1.7976931348623157E308D);
    }

    public static ArgumentType<Double> doubleArg(double min, double max) {
        return DoubleArgumentType.doubleArg(min, max);
    }

    public static ArgumentType<Long> longArg() {
        return longArg(-9223372036854775808L);
    }

    public static ArgumentType<Long> longArg(long min) {
        return longArg(min, 9223372036854775807L);
    }

    public static ArgumentType<Long> longArg(long min, long max) {
        return LongArgumentType.longArg(min, max);
    }

    public static ArgumentType<Float> floatArg() {
        return floatArg(-3.4028235E38F);
    }

    public static ArgumentType<Float> floatArg(float min) {
        return floatArg(min, 3.4028235E38F);
    }

    public static ArgumentType<Float> floatArg(float min, float max) {
        return FloatArgumentType.floatArg(min, max);
    }

}
