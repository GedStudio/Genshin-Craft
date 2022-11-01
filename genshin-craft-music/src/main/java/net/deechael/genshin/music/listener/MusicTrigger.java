package net.deechael.genshin.music.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

public final class MusicTrigger implements Listener {

    private final Map<UUID, Set<UUID>> targeting = new HashMap<>();
    private final Map<UUID, UUID> entityToPlayer = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        targeting.put(event.getPlayer().getUniqueId(), new HashSet<>());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        targeting.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onEntityDie(EntityDeathEvent event) {
        UUID uuid = event.getEntity().getUniqueId();
        if (!entityToPlayer.containsKey(uuid))
            return;
        entityToPlayer.remove(uuid);
        targeting.forEach((k, v) -> v.remove(uuid));
        targeting.keySet().stream().map(Bukkit::getPlayer).forEach(this::check);
    }

    @EventHandler
    public void onTarget(EntityTargetEvent event) {
        if (event.getEntity() instanceof Player)
            return;
        if (event.getTarget() == null) {
            UUID uuid = event.getEntity().getUniqueId();
            if (!entityToPlayer.containsKey(uuid))
                return;
            entityToPlayer.remove(uuid);
            targeting.forEach((k, v) -> v.remove(uuid));
        } else if (event.getTarget() instanceof Player player) {
            UUID uuid = event.getEntity().getUniqueId();
            if (!entityToPlayer.containsKey(uuid))
                return;
            targeting.forEach((k, v) -> v.remove(uuid));
            targeting.get(player.getUniqueId()).add(uuid);
            entityToPlayer.put(uuid, player.getUniqueId());
        } else
            return;
        targeting.keySet().stream().map(Bukkit::getPlayer).forEach(this::check);
    }

    private void check(Player player) {
        if (player == null)
            return;
        if (this.targeting.get(player.getUniqueId()).size() > 0) {
            if (isPlayingCombat(player))
                return;
            playCombat(player);
        } else {
            if (!isPlayingCombat(player))
                return;
            stopPlayerCombat(player);
        }
    }

    private void playCombat(Player player) {

    }

    private void stopPlayerCombat(Player player) {

    }

    public boolean isPlayingCombat(Player player) {
        return player != null;
    }

}
