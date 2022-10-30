package net.deechael.genshin;

import net.deechael.genshin.lib.LibLauncher;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GsCorePlugin extends JavaPlugin {

    private final static Logger LOGGER = LoggerFactory.getLogger("Genshin Craft");

    @Override
    public void onLoad() {
        LibLauncher.load(this);
    }

    @Override
    public void onEnable() {
        LibLauncher.enable(this);
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
