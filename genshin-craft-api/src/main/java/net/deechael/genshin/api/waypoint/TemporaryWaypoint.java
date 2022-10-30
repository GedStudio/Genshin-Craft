package net.deechael.genshin.api.waypoint;

import net.deechael.genshin.api.player.GenshinProfile;

public interface TemporaryWaypoint {

    GenshinProfile getCreator();

    String getWorld();

    int getX();

    int getY();

    int getZ();

}
