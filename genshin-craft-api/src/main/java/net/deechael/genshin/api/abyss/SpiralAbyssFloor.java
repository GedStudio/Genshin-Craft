package net.deechael.genshin.api.abyss;

import net.deechael.genshin.api.dunegon.DungeonLevel;
import net.deechael.genshin.api.dunegon.DungeonType;
import net.deechael.genshin.api.item.ItemType;

import java.util.ArrayList;
import java.util.List;

public interface SpiralAbyssFloor extends DungeonLevel {

    List<SpiralAbyssChamber> getChambers();

    @Override
    default DungeonType getType() {
        return DungeonType.SPIRAL_ABYSS;
    }

    @Override
    default boolean allowCoop() {
        return false;
    }

    @Override
    default List<ItemType> getDrops() {
        return new ArrayList<>();
    }

}
