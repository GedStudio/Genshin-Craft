package net.deechael.genshin.lib.impl.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.deechael.genshin.lib.open.command.*;
import net.deechael.useless.function.functions.Function;
import net.minecraft.commands.CommandDispatcher;
import net.minecraft.commands.CommandListenerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public final class EzCommandImpl implements EzCommand {

    private static final int SINGLE_SUCCESS = 1;

    protected final LiteralArgumentBuilder<CommandListenerWrapper> literalArgumentBuilder;
    private boolean registered = false;
    private EzCommandRegistered ezCommandRegistered;

    public EzCommandImpl(String commandName) {
        this.literalArgumentBuilder = CommandDispatcher.a(commandName);
    }

    public EzCommandImpl(String commandName, int permission, String bukkitPermission, PermissionDefault permissionDefault) {
        commandName = commandName.toLowerCase();
        this.literalArgumentBuilder = CommandDispatcher.a(commandName);
        if (permission < 0) permission = 0;
        if (permission > 4) permission = 4;
        int finalPermission = permission;
        literalArgumentBuilder.requires((requirement) -> requirement.hasPermission(finalPermission, bukkitPermission.toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission) == null) {
            Bukkit.getPluginManager().addPermission(new Permission(bukkitPermission, permissionDefault));
        } else {
            Bukkit.getPluginManager().getPermission(bukkitPermission).setDefault(permissionDefault);
        }
    }

    public EzCommandImpl(String commandName, int permission, Permission bukkitPermission) {
        commandName = commandName.toLowerCase();
        this.literalArgumentBuilder = CommandDispatcher.a(commandName);
        if (permission < 0) permission = 0;
        if (permission > 4) permission = 4;
        int finalPermission = permission;
        literalArgumentBuilder.requires((requirement) -> requirement.hasPermission(finalPermission, bukkitPermission.getName().toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission.getName()) == null) {
            Bukkit.getPluginManager().addPermission(bukkitPermission);
        }
    }

    public EzCommandImpl(String commandName, int permission, String bukkitPermission) {
        commandName = commandName.toLowerCase();
        this.literalArgumentBuilder = CommandDispatcher.a(commandName);
        if (permission < 0) permission = 0;
        if (permission > 4) permission = 4;
        int finalPermission = permission;
        literalArgumentBuilder.requires((requirement) -> requirement.hasPermission(finalPermission, bukkitPermission.toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission) == null) {
            Bukkit.getPluginManager().addPermission(new Permission(bukkitPermission, PermissionDefault.OP));
        }
    }

    public EzCommandImpl(String commandName, int permission) {
        commandName = commandName.toLowerCase();
        this.literalArgumentBuilder = CommandDispatcher.a(commandName);
        if (permission < 0) permission = 0;
        if (permission > 4) permission = 4;
        int finalPermission = permission;
        literalArgumentBuilder.requires((requirement) -> requirement.c(finalPermission));
    }

    public EzCommandImpl(String commandName, String bukkitPermission) {
        commandName = commandName.toLowerCase();
        this.literalArgumentBuilder = CommandDispatcher.a(commandName);
        literalArgumentBuilder.requires((requirement) -> requirement.getBukkitSender().hasPermission(bukkitPermission.toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission) == null) {
            Bukkit.getPluginManager().addPermission(new Permission(bukkitPermission, PermissionDefault.OP));
        }
    }

    public EzCommandImpl(String commandName, String bukkitPermission, PermissionDefault permissionDefault) {
        commandName = commandName.toLowerCase();
        this.literalArgumentBuilder = CommandDispatcher.a(commandName);
        literalArgumentBuilder.requires((requirement) -> requirement.getBukkitSender().hasPermission(bukkitPermission.toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission) == null) {
            Bukkit.getPluginManager().addPermission(new Permission(bukkitPermission, permissionDefault));
        } else {
            Bukkit.getPluginManager().getPermission(bukkitPermission).setDefault(permissionDefault);
        }
    }

    public EzCommandImpl(String commandName, Permission bukkitPermission) {
        commandName = commandName.toLowerCase();
        this.literalArgumentBuilder = CommandDispatcher.a(commandName);
        literalArgumentBuilder.requires((requirement) -> requirement.getBukkitSender().hasPermission(bukkitPermission.getName().toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission.getName()) == null) {
            Bukkit.getPluginManager().addPermission(bukkitPermission);
        }
    }

    public EzCommandImpl then(EzCommand command) {
        if (registered) return this;
        if (command instanceof EzCommandImpl commandImpl)
            then(commandImpl);
        else if (command instanceof EzArgumentImpl argumentImpl)
            then(argumentImpl);
        return this;
    }

    public void then(EzArgumentImpl ezArgument) {
        if (registered) return;
        literalArgumentBuilder.then(ezArgument.requiredArgumentBuilder);
    }

    public void then(EzCommandImpl ezCommand) {
        if (registered) return;
        literalArgumentBuilder.then(ezCommand.literalArgumentBuilder);
    }

    public EzCommandImpl then(EzCommandRegistered ezCommandRegistered) {
        if (registered) return this;
        literalArgumentBuilder.then(ezCommandRegistered.commandNode);
        return this;
    }

    public EzCommand executes(CommandSenderInvoker executes) {
        if (registered) return this;
        literalArgumentBuilder.executes(commandContext ->
                executes.execute(commandContext.getSource().getBukkitSender(), new EzArgumentHelperImpl(commandContext)));
        return this;
    }

    public EzCommand executesPlayer(PlayerInvoker executes) {
        if (registered) return this;
        literalArgumentBuilder
                .executes(commandContext ->
                executes.execute(commandContext.getSource().i().getBukkitEntity(), new EzArgumentHelperImpl(commandContext)));
        return this;
    }

    public EzCommandImpl redirect(EzRegisteredCommand ezCommandRegistered) {
        if (registered) return this;
        literalArgumentBuilder.redirect(((EzCommandRegistered) ezCommandRegistered).commandNode);
        return this;
    }

    public EzCommandImpl requires(Function<CommandSender, Boolean> function) {
        Predicate<CommandListenerWrapper> predicate = this.literalArgumentBuilder.getRequirement();
        this.literalArgumentBuilder.requires((commandListenerWrapper) -> {
            CommandSender sender = commandListenerWrapper.getBukkitSender();
            return predicate.test(commandListenerWrapper) && function.apply(sender);
        });
        return this;
    }

    public boolean isRegistered() {
        return registered;
    }

    EzCommandRegistered register() {
        if (!registered) {
            ezCommandRegistered = new EzCommandRegistered(this);
            registered = true;
        }
        return ezCommandRegistered;
    }

}
