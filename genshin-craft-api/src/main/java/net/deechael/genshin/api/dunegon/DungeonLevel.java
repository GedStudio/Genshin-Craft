package net.deechael.genshin.api.dunegon;

import net.deechael.genshin.api.item.ItemType;

import java.util.List;

public interface DungeonLevel {

    Dungeon getDungeon();

    DungeonType getType();

    boolean allowCoop();

    List<ItemType> getDrops();

}
