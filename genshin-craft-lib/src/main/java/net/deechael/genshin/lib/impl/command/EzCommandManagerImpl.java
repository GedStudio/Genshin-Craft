package net.deechael.genshin.lib.impl.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.deechael.genshin.lib.open.Ref;
import net.deechael.genshin.lib.open.command.EzCommand;
import net.deechael.genshin.lib.open.command.EzCommandManager;
import net.deechael.genshin.lib.open.command.EzRegisteredCommand;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;
import org.bukkit.craftbukkit.v1_19_R1.command.VanillaCommandWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EzCommandManagerImpl implements Listener, EzCommandManager {

    private final static Field KNOWN_COMMANDS_FIELD;

    static {
        try {
            KNOWN_COMMANDS_FIELD = SimpleCommandMap.class.getDeclaredField("knownCommands");
            KNOWN_COMMANDS_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }


    public static final EzCommandManagerImpl INSTANCE = new EzCommandManagerImpl();
    private static final Map<String, List<EzCommandImpl>> REGISTERED = new HashMap<>();
    private static CommandMap BUKKIT_COMMAND_MAP;

    private EzCommandManagerImpl() {
        Bukkit.getPluginManager().addPermission(new Permission("gs.craft.lib.command.registering", PermissionDefault.TRUE));
    }

    public EzRegisteredCommand register(EzCommand ezCommand) {
        return register("genshin", ezCommand);
    }

    public EzRegisteredCommand register(String prefix, EzCommand ezCommand) {
        if (BUKKIT_COMMAND_MAP == null)
            BUKKIT_COMMAND_MAP = ((CraftServer) Bukkit.getServer()).getCommandMap();
        EzCommandRegistered ezCommandRegistered = ((EzCommandImpl) ezCommand).register();
        //EasyAPI.getBukkitCommandMap().register(prefix, new UnsupportCommand(ezCommandRegistered.commandNode.getName(), ezCommand.aliases.toArray(new String[0])));
        if (!REGISTERED.containsKey(prefix)) REGISTERED.put(prefix, new ArrayList<>());
        REGISTERED.get(prefix).add(ezCommandRegistered.ezCommand);
        Command command = new VanillaCommandWrapper(null, ezCommandRegistered.commandNode);
        command.setPermission("gs.craft.lib.command.registering");
        command.setAliases(ezCommandRegistered.getAliases());
        BUKKIT_COMMAND_MAP.register(prefix.toLowerCase(), command);
        return ezCommandRegistered;
    }

    private static void setDispatcher(Command vanillaListenerWrapper, Object nmsCommandDispatcher) {
        try {
            Field field = Ref.getObcClass("command.VanillaCommandWrapper").getDeclaredField("dispatcher");
            field.setAccessible(true);
            field.set(vanillaListenerWrapper, nmsCommandDispatcher);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Command> getKnownCommands() {
        if (BUKKIT_COMMAND_MAP == null)
            BUKKIT_COMMAND_MAP = ((CraftServer) Bukkit.getServer()).getCommandMap();
        try {
            return (Map<String, Command>) KNOWN_COMMANDS_FIELD.get(BUKKIT_COMMAND_MAP);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setKnownCommands(Map<String, Command> knownCommands) {
        try {
            KNOWN_COMMANDS_FIELD.set(BUKKIT_COMMAND_MAP, knownCommands);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onLoad(ServerLoadEvent serverLoadEvent) {
        net.minecraft.commands.CommandDispatcher commandDispatcher = MinecraftServer.getServer().aC();
        CommandDispatcher mojangDispatcher = commandDispatcher.a();
        Map<String, Command> knownCommands = getKnownCommands();
        if (knownCommands != null) {
            for (String prefix : REGISTERED.keySet()) {
                for (EzCommandImpl ezCommand : REGISTERED.get(prefix)) {
                    CommandNode<CommandListenerWrapper> commandNode = ezCommand.register().commandNode;
                    Command command = knownCommands.get(prefix.toLowerCase() + ":" + commandNode.getName().toLowerCase());
                    List<String> aliases = ezCommand.register().getAliases();
                    for (String string : aliases) {
                        String key = prefix.toLowerCase() + ":" + string.toLowerCase();
                        Command aliaCommand = knownCommands.get(key);
                        if (aliaCommand.getPermission().equalsIgnoreCase("gs.craft.lib.command.registering")) {
                            LiteralCommandNode<CommandListenerWrapper> literalCommandNode = new LiteralCommandNode<>(prefix.toLowerCase() + ":" + commandNode.getName().toLowerCase(), commandNode.getCommand(), commandNode.getRequirement(), commandNode.getRedirect(), commandNode.getRedirectModifier(), commandNode.isFork());
                            mojangDispatcher.getRoot().addChild(literalCommandNode);
                            setDispatcher(aliaCommand, commandDispatcher);
                            knownCommands.put(key, aliaCommand);
                        }
                        Command aliaCmd = knownCommands.get(string.toLowerCase());
                        if (aliaCmd.getPermission().equalsIgnoreCase("gs.craft.lib.command.registering")) {
                            LiteralCommandNode<CommandListenerWrapper> literalCommandNode = new LiteralCommandNode<>(commandNode.getName().toLowerCase(), commandNode.getCommand(), commandNode.getRequirement(), commandNode.getRedirect(), commandNode.getRedirectModifier(), commandNode.isFork());
                            mojangDispatcher.getRoot().addChild(literalCommandNode);
                            setDispatcher(aliaCmd, commandDispatcher);
                            knownCommands.put(string.toLowerCase(), aliaCmd);
                        }
                    }
                    Command cmd = knownCommands.get(commandNode.getName().toLowerCase());
                    if (cmd.getPermission().equalsIgnoreCase("gs.craft.lib.command.registering")) {
                        mojangDispatcher.getRoot().addChild(commandNode);
                        setDispatcher(cmd, commandDispatcher);
                        knownCommands.put(commandNode.getName().toLowerCase(), cmd);
                    }
                    LiteralCommandNode<CommandListenerWrapper> literalCommandNode = new LiteralCommandNode<>(prefix.toLowerCase() + ":" + commandNode.getName().toLowerCase(), commandNode.getCommand(), commandNode.getRequirement(), commandNode.getRedirect(), commandNode.getRedirectModifier(), commandNode.isFork());
                    mojangDispatcher.getRoot().addChild(literalCommandNode);
                    setDispatcher(command, commandDispatcher);
                    knownCommands.put(prefix.toLowerCase() + ":" + commandNode.getName().toLowerCase(), command);
                }
            }
            setKnownCommands(knownCommands);
        }
    }

}
