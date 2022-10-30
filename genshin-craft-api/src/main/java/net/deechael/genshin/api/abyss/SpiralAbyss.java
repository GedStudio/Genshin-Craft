package net.deechael.genshin.api.abyss;

import net.deechael.genshin.api.dunegon.Dungeon;
import net.deechael.genshin.api.dunegon.DungeonType;

import java.util.List;

public interface SpiralAbyss extends Dungeon {

    List<SpiralAbyssFloor> getAbyssCorridorFloors();

    List<SpiralAbyssFloor> getAbyssalMoonSpireFloors();

    @Override
    default DungeonType getType() {
        return DungeonType.SPIRAL_ABYSS;
    }

}
