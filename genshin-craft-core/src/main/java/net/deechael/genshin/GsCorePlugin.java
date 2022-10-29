package net.deechael.genshin;

import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GsCorePlugin extends JavaPlugin {

    private final static Logger LOGGER = LoggerFactory.getLogger("Genshin Craft");

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    public static GsCorePlugin getInstance() {
        return JavaPlugin.getPlugin(GsCorePlugin.class);
    }

    public static Logger logger() {
        return LOGGER;
    }

}
