package net.deechael.genshin.lib.open.command;

import net.deechael.genshin.lib.impl.command.EzArgumentImpl;
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

    static EzCommand literal(String name, int vanillaPermission) {
        return new EzCommandImpl(name, vanillaPermission);
    }

    static EzCommand literal(String name, String bukkitPermission) {
        return new EzCommandImpl(name, bukkitPermission);
    }

    static EzCommand literal(String name, int vanillaPermission, String bukkitPermission) {
        return new EzCommandImpl(name, vanillaPermission, bukkitPermission);
    }

    static EzCommand argument(EzArgumentType argumentType, String name) {
        return new EzArgumentImpl(argumentType.type(), name);
    }

    static EzCommand argument(EzArgumentType argumentType, String name, int vanillaPermission) {
        return new EzArgumentImpl(argumentType.type(), name, vanillaPermission);
    }

    static EzCommand argument(EzArgumentType argumentType, String name, String bukkitPermission) {
        return new EzArgumentImpl(argumentType.type(), name, bukkitPermission);
    }

    static EzCommand argument(EzArgumentType argumentType, String name, int vanillaPermission, String bukkitPermission) {
        return new EzArgumentImpl(argumentType.type(), name, vanillaPermission, bukkitPermission);
    }

}
