package net.deechael.genshin.api.dunegon;

import java.util.List;

public interface Dungeon {

    List<DungeonLevel> getLevels();

    DungeonType getType();

    boolean allowCoop();

}
