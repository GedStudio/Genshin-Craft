package net.deechael.genshin.api.player;

import java.util.UUID;

public interface GenshinPlayer {

    int getUid();

    UUID getUniqueId();

    int getAdventureExp();

    int getAdventureRank();

    int getWorldLevel();

    long getMora();

    int getPrimogem();

    int getGenesisCrystal();

    int getOriginalResin();

    void addAdventureExp(int exp);

    void addMora(long mora);

    void addPrimogem(int primogem);

    void addGenesisCrystal(int genesisCrystal);

    void addOriginalResin(int originalResin);

    void restoreWorldLevel();

    void decreaseWorldLevel();

    void setMora(long mora);

    void setPrimogem(int primogem);

    void setGenesisCrystal(int genesisCrystal);

    void setOriginalResin(int originalResin);

}
