package net.deechael.genshin.item;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.xenondevs.nova.api.Nova;
import xyz.xenondevs.nova.api.material.NovaMaterialRegistry;

public class GsCraftItemModule extends JavaPlugin {

    private static Nova NOVA;

    @Override
    public void onEnable() {
        NOVA = Nova.getNova();
        NovaMaterialRegistry materialRegistry = NOVA.getMaterialRegistry();
        
    }

}
