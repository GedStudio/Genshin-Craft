package net.deechael.genshin.impl;

import net.deechael.genshin.api.player.AdventureRanks;
import net.deechael.genshin.api.player.GenshinProfile;

import java.util.UUID;

public class GenshinProfileImpl implements GenshinProfile {

    private final int uid;
    private final UUID uuid;
    private int adventureExp;
    private boolean worldLevelDropped;
    private long mora;
    private int primogem;
    private int genesisCrystal;
    private boolean ascensionQuest_1;
    private boolean ascensionQuest_2;
    private boolean ascensionQuest_3;
    private boolean ascensionQuest_4;
    private int originalResin;

    public GenshinProfileImpl(int uid,
                              String uuid,
                              int adventureExp,
                              boolean worldLevelDropped,
                              long mora,
                              int primogem,
                              int genesisCrystal,
                              String ascensionQuests,
                              int originalResin
                          ) {
        this.uid = uid;
        this.uuid = UUID.fromString(uuid);
        this.adventureExp = adventureExp;
        this.worldLevelDropped = worldLevelDropped;
        this.mora = mora;
        this.primogem = primogem;
        this.genesisCrystal = genesisCrystal;
        char[] ascensionQuestsChars = ascensionQuests.toCharArray();
        this.ascensionQuest_1 = ascensionQuestsChars[0] == '1';
        this.ascensionQuest_2 = ascensionQuestsChars[1] == '1';
        this.ascensionQuest_3 = ascensionQuestsChars[2] == '1';
        this.ascensionQuest_4 = ascensionQuestsChars[3] == '1';
    }

    public int getUid() {
        return uid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public UUID getUniqueId() {
        return getUuid();
    }

    public int getAdventureExp() {
        return adventureExp;
    }

    public int getAdventureRank() {
        int rank = AdventureRanks.getLevel(this.adventureExp);
        if (rank <= 25)
            return rank;
        if (!ascensionQuest_1)
            return 25;
        if (rank <= 35)
            return rank;
        if (!ascensionQuest_2)
            return 35;
        if (rank <= 45)
            return rank;
        if (!ascensionQuest_3)
            return 45;
        if (rank <= 50)
            return rank;
        if (!ascensionQuest_4)
            return 50;
        return rank;
    }

    public int getWorldLevel() {
        int worldLevel = AdventureRanks.getWorldLevel(adventureExp);
        int realLevel = AdventureRanks.getLevel(this.adventureExp);
        if (realLevel <= 24)
            return worldLevel;
        if (realLevel == 25 && !ascensionQuest_1)
            return 1;
        if (realLevel <= 34)
            return worldLevel;
        if (realLevel == 35 && !ascensionQuest_2)
            return 3;
        if (realLevel <= 39)
            return worldLevel;
        if (realLevel <= 44)
            return worldLevelDropped ? worldLevel - 1 : worldLevel;
        if (realLevel == 45 && !ascensionQuest_3)
            return worldLevelDropped ? 4 : 5;
        if (realLevel <= 49)
            return worldLevelDropped ? worldLevel - 1 : worldLevel;
        if (realLevel == 50 && !ascensionQuest_2)
            return worldLevelDropped ? 5 : 6;
        return worldLevelDropped ? worldLevel - 1 : worldLevel;
    }

    public long getMora() {
        return mora;
    }

    public int getPrimogem() {
        return primogem;
    }

    public int getGenesisCrystal() {
        return genesisCrystal;
    }

    public int getOriginalResin() {
        return originalResin;
    }

    public void addAdventureExp(int exp) {
        this.adventureExp += exp;
    }

    public void addMora(long mora) {
        this.mora += mora;
    }

    public void addPrimogem(int primogem) {
        this.primogem += primogem;
    }

    public void addGenesisCrystal(int genesisCrystal) {
        this.genesisCrystal += genesisCrystal;
    }

    public void addOriginalResin(int originalResin) {
        this.originalResin += originalResin;
    }

    public void restoreWorldLevel() {
        this.worldLevelDropped = false;
    }

    public void decreaseWorldLevel() {
        this.worldLevelDropped = true;
    }

    public void setMora(long mora) {
        this.mora = mora;
    }

    public void setPrimogem(int primogem) {
        this.primogem = primogem;
    }

    public void setGenesisCrystal(int genesisCrystal) {
        this.genesisCrystal = genesisCrystal;
    }

    public void setOriginalResin(int originalResin) {
        this.originalResin = originalResin;
    }

    public void completeAscensionQuest1() {
        this.ascensionQuest_1 = true;
    }

    public void completeAscensionQuest2() {
        this.ascensionQuest_2 = true;
    }

    public void completeAscensionQuest3() {
        this.ascensionQuest_3 = true;
    }

    public void completeAscensionQuest4() {
        this.ascensionQuest_4 = true;
    }

}
