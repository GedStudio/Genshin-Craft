package net.deechael.genshin.api.battlepass;

import org.bukkit.inventory.ItemStack;

public interface BattlePass {

    int getLevel();

    int getMaxLevel();

    int getExp();

    int getMaxExp();

    void addExp(int exp);

    ItemStack getReward(int level);

}
