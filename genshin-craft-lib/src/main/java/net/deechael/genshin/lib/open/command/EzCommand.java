package net.deechael.genshin.lib.open.command;

import net.deechael.genshin.lib.impl.command.EzCommandImpl;
import net.deechael.useless.function.functions.Function;
import org.bukkit.command.CommandSender;

public interface EzCommand {

    EzCommand then(EzCommand command);

    EzCommand executes(CommandSenderInvoker executes);

    EzCommand executesPlayer(PlayerInvoker executes);

    EzCommand redirect(EzRegisteredCommand command);

    EzCommand requires(Function<CommandSender, Boolean> function);

    static EzCommand literal(String name) {
        return new EzCommandImpl(name);
    }

}
