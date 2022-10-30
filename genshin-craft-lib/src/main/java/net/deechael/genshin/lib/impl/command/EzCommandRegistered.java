package net.deechael.genshin.lib.impl.command;

import com.mojang.brigadier.tree.CommandNode;
import net.deechael.genshin.lib.open.command.EzRegisteredCommand;
import net.minecraft.commands.CommandListenerWrapper;

import java.util.ArrayList;
import java.util.List;

public final class EzCommandRegistered implements EzRegisteredCommand {

    final EzCommandImpl ezCommand;
    private final List<String> stringAliases = new ArrayList<>();
    CommandNode<CommandListenerWrapper> commandNode;

    EzCommandRegistered(EzCommandImpl ezCommand) {
        this.ezCommand = ezCommand;
        if (ezCommand.isRegistered()) {
            commandNode = ezCommand.register().commandNode;
        } else {
            commandNode = ezCommand.literalArgumentBuilder.build();
            /*
            CommandDispatcher<Object> commandDispatcher = getMojangCommandDispatcher();
            if (commandDispatcher != null) {
                commandNode = commandDispatcher.register(ezCommand.literalArgumentBuilder);
            }
            */
        }
    }

    public void addAliases(String... aliases) {
        if (aliases.length > 0) {
            this.stringAliases.addAll(List.of(aliases));
        }
    }

    public List<String> getAliases() {
        return stringAliases;
    }

    public EzCommandImpl getCommand() {
        return ezCommand;
    }

    @Override
    public CommandNode<?> asBrigadier() {
        return this.commandNode;
    }

}
