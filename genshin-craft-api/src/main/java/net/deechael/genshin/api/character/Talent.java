package net.deechael.genshin.api.character;

import net.deechael.genshin.api.item.ItemType;

import java.util.Map;

public interface Talent {

    String getName();

    String getLocalizedName(String locale);

    String getDescription();

    String getLocalizedDescription(String locale);

    int getLevel();

    int getMaxLevel();

    Map<ItemType, Integer> nextLevelItems();

    long nextLevelMoras();

    default boolean isMax() {
        return getLevel() == getMaxLevel();
    }

}
