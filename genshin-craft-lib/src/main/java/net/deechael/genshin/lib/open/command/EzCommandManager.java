package net.deechael.genshin.lib.open.command;

import net.deechael.genshin.lib.impl.command.EzCommandManagerImpl;

public interface EzCommandManager {

    EzRegisteredCommand register(EzCommand ezCommand);

    EzRegisteredCommand register(String prefix, EzCommand ezCommand);

    static EzCommandManager getManager() {
        return EzCommandManagerImpl.INSTANCE;
    }

}
