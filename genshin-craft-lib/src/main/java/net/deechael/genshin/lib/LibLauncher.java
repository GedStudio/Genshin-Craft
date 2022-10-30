package net.deechael.genshin.lib;

import net.deechael.genshin.lib.open.command.EzCommandManager;
import net.deechael.genshin.lib.open.nbt.injector.NBTInjector;
import net.deechael.genshin.lib.open.nbt.utils.MinecraftVersion;
import net.deechael.genshin.lib.open.particle.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class LibLauncher {

    public static void load(Plugin genshinCraftCore) {
        NBTInjector.inject(); // NBT API
    }

    public static void enable(Plugin genshinCraftCore) {
        ReflectionUtils.setPlugin(genshinCraftCore); // Particle Lib

        Bukkit.getPluginManager().registerEvents((Listener) EzCommandManager.getManager(), genshinCraftCore);

        // NBT API start
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onCommand(org.bukkit.event.server.ServerCommandEvent event) {
                if (event.getCommand().toLowerCase().startsWith("reload")) {
                    event.setCancelled(true);
                    event.getSender().sendMessage("[Genshin Craft] The plugin is not supported with \"/reload\" commnad, please restart the server if you want to reload!");
                }
            }

            @EventHandler
            public void onCommand(org.bukkit.event.player.PlayerCommandPreprocessEvent event) {
                if (event.getMessage().toLowerCase().startsWith("/reload")) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("[Genshin Craft] The plugin is not supported with \"/reload\" commnad, please restart the server if you want to reload!");
                }
            }
        }, genshinCraftCore);
        MinecraftVersion.getVersion();
        // NBT API end
    }

}
