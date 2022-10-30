package net.deechael.genshin.api.event;

import net.deechael.genshin.api.dunegon.Dungeon;
import net.deechael.genshin.api.dunegon.DungeonType;

public interface EventDungeon extends Dungeon {

    @Override
    default DungeonType getType() {
        return DungeonType.EVENT;
    }

}
