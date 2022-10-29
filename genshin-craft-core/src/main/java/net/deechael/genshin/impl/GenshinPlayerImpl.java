package net.deechael.genshin.impl;

import net.deechael.genshin.GenshinProfile;
import net.deechael.genshin.api.player.GenshinPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GenshinPlayerImpl implements GenshinPlayer {

    private final Player player;
    private final GenshinProfile profile;

    public GenshinPlayerImpl(Player player, GenshinProfile profile) {
        this.player = player;
        this.profile = profile;
    }

    @Override
    public int getUid() {
        return profile.getUid();
    }

    @Override
    public UUID getUniqueId() {
        return profile.getUuid();
    }

    @Override
    public int getAdventureExp() {
        return profile.getAdventureExp();
    }

    @Override
    public int getAdventureRank() {
        return profile.getAdventureRank();
    }

    @Override
    public int getWorldLevel() {
        return profile.getWorldLevel();
    }

    @Override
    public long getMora() {
        return profile.getMora();
    }

    @Override
    public int getPrimogem() {
        return profile.getPrimogem();
    }

    @Override
    public int getGenesisCrystal() {
        return profile.getGenesisCrystal();
    }

    @Override
    public int getOriginalResin() {
        return profile.getOriginalResin();
    }

    @Override
    public void addAdventureExp(int exp) {
        profile.addAdventureExp(exp);
    }

    @Override
    public void addMora(long mora) {
        profile.addMora(mora);
    }

    @Override
    public void addPrimogem(int primogem) {
        profile.addPrimogem(primogem);
    }

    @Override
    public void addGenesisCrystal(int genesisCrystal) {
        profile.addGenesisCrystal(genesisCrystal);
    }

    @Override
    public void addOriginalResin(int originalResin) {
        profile.addOriginalResin(originalResin);
    }

    @Override
    public void restoreWorldLevel() {
        profile.restoreWorldLevel();
    }

    @Override
    public void decreaseWorldLevel() {
        profile.decreaseWorldLevel();
    }

    @Override
    public void setMora(long mora) {
        profile.setMora(mora);
    }

    @Override
    public void setPrimogem(int primogem) {
        profile.setPrimogem(primogem);
    }

    @Override
    public void setGenesisCrystal(int genesisCrystal) {
        profile.setGenesisCrystal(genesisCrystal);
    }

    @Override
    public void setOriginalResin(int originalResin) {
        profile.setOriginalResin(originalResin);
    }

}
