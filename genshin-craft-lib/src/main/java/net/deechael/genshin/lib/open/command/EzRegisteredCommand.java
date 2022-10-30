package net.deechael.genshin.lib.open.command;

import com.mojang.brigadier.tree.CommandNode;

public interface EzRegisteredCommand {

    CommandNode<?> asBrigadier();

    EzCommand getCommand();

}
