package net.deechael.genshin.lib.impl.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.deechael.genshin.lib.open.command.*;
import net.deechael.useless.function.functions.Function;
import net.deechael.useless.function.parameters.DuParameter;
import net.minecraft.commands.CommandDispatcher;
import net.minecraft.commands.CommandListenerWrapper;
import net.minecraft.commands.arguments.ArgumentEntitySummon;
import net.minecraft.commands.synchronization.CompletionProviders;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public final class EzArgumentImpl implements EzCommand {

    final RequiredArgumentBuilder<CommandListenerWrapper, ?> requiredArgumentBuilder;

    public EzArgumentImpl(ArgumentType<?> argumentType, String argumentName) {
        argumentName = argumentName.toLowerCase();
        requiredArgumentBuilder = CommandDispatcher.a(argumentName, argumentType);
        if (argumentType instanceof ArgumentEntitySummon) {
            requiredArgumentBuilder.suggests(CompletionProviders.d);
        }
    }

    public EzArgumentImpl(ArgumentType<?> argumentType, String argumentName, int permission, String bukkitPermission, PermissionDefault permissionDefault) {
        requiredArgumentBuilder = CommandDispatcher.a(argumentName, argumentType);
        if (permission < 0) permission = 0;
        if (permission > 4) permission = 4;
        int finalPermission = permission;
        requiredArgumentBuilder.requires((requirement) -> requirement.hasPermission(finalPermission, bukkitPermission.toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission) == null) {
            Bukkit.getPluginManager().addPermission(new Permission(bukkitPermission, permissionDefault));
        } else {
            Bukkit.getPluginManager().getPermission(bukkitPermission).setDefault(permissionDefault);
        }
        if (argumentType instanceof ArgumentEntitySummon) {
            requiredArgumentBuilder.suggests(CompletionProviders.d);
        }
    }

    public EzArgumentImpl(ArgumentType<?> argumentType, String argumentName, int permission, Permission bukkitPermission) {
        requiredArgumentBuilder = CommandDispatcher.a(argumentName, argumentType);
        if (permission < 0) permission = 0;
        if (permission > 4) permission = 4;
        int finalPermission = permission;
        requiredArgumentBuilder.requires((requirement) -> requirement.hasPermission(finalPermission, bukkitPermission.getName().toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission.getName()) == null) {
            Bukkit.getPluginManager().addPermission(bukkitPermission);
        }
        if (argumentType instanceof ArgumentEntitySummon) {
            requiredArgumentBuilder.suggests(CompletionProviders.d);
        }
    }

    public EzArgumentImpl(ArgumentType<?> argumentType, String argumentName, int permission, String bukkitPermission) {
        requiredArgumentBuilder = CommandDispatcher.a(argumentName, argumentType);
        if (permission < 0) permission = 0;
        if (permission > 4) permission = 4;
        int finalPermission = permission;
        requiredArgumentBuilder.requires((requirement) -> requirement.hasPermission(finalPermission, bukkitPermission.toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission) == null) {
            Bukkit.getPluginManager().addPermission(new Permission(bukkitPermission, PermissionDefault.OP));
        }
        if (argumentType instanceof ArgumentEntitySummon) {
            requiredArgumentBuilder.suggests(CompletionProviders.d);
        }
    }

    public EzArgumentImpl(ArgumentType<?> argumentType, String argumentName, int permission) {
        requiredArgumentBuilder = CommandDispatcher.a(argumentName, argumentType);
        if (permission < 0) permission = 0;
        if (permission > 4) permission = 4;
        int finalPermission = permission;
        requiredArgumentBuilder.requires((requirement) -> requirement.c(finalPermission));
        if (argumentType instanceof ArgumentEntitySummon) {
            requiredArgumentBuilder.suggests(CompletionProviders.d);
        }
    }

    public EzArgumentImpl(ArgumentType<?> argumentType, String argumentName, Permission bukkitPermission) {
        requiredArgumentBuilder = CommandDispatcher.a(argumentName, argumentType);
        requiredArgumentBuilder.requires((requirement) -> requirement.getBukkitSender().hasPermission(bukkitPermission.getName().toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission.getName()) == null) {
            Bukkit.getPluginManager().addPermission(bukkitPermission);
        }
        if (argumentType instanceof ArgumentEntitySummon) {
            requiredArgumentBuilder.suggests(CompletionProviders.d);
        }
    }

    public EzArgumentImpl(ArgumentType<?> argumentType, String argumentName, String bukkitPermission) {
        requiredArgumentBuilder = CommandDispatcher.a(argumentName, argumentType);
        requiredArgumentBuilder.requires((requirement) -> requirement.getBukkitSender().hasPermission(bukkitPermission.toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission) == null) {
            Bukkit.getPluginManager().addPermission(new Permission(bukkitPermission, PermissionDefault.OP));
        }
        if (argumentType instanceof ArgumentEntitySummon) {
            requiredArgumentBuilder.suggests(CompletionProviders.d);
        }
    }

    public EzArgumentImpl(ArgumentType<?> argumentType, String argumentName, String bukkitPermission, PermissionDefault permissionDefault) {
        requiredArgumentBuilder = CommandDispatcher.a(argumentName, argumentType);
        requiredArgumentBuilder.requires((requirement) -> requirement.getBukkitSender().hasPermission(bukkitPermission.toLowerCase()));
        if (Bukkit.getPluginManager().getPermission(bukkitPermission) == null) {
            Bukkit.getPluginManager().addPermission(new Permission(bukkitPermission, permissionDefault));
        }
        if (argumentType instanceof ArgumentEntitySummon) {
            requiredArgumentBuilder.suggests(CompletionProviders.d);
        }
    }

    public EzArgumentImpl then(EzCommand command) {
        if (command instanceof EzCommandImpl commandImpl)
            then(commandImpl);
        else if (command instanceof EzArgumentImpl argumentImpl)
            then(argumentImpl);
        return this;
    }

    public void then(EzArgumentImpl ezArgument) {
        requiredArgumentBuilder.then(ezArgument.requiredArgumentBuilder);
    }

    public void then(EzCommandImpl ezCommand) {
        requiredArgumentBuilder.then(ezCommand.literalArgumentBuilder);
    }

    public EzArgumentImpl then(EzCommandRegistered ezCommandRegistered) {
        requiredArgumentBuilder.then(ezCommandRegistered.commandNode);
        return this;
    }

    public EzArgumentImpl executes(CommandSenderInvoker executes) {
        requiredArgumentBuilder.executes(commandContext -> executes.execute(commandContext.getSource().getBukkitSender(), new EzArgumentHelperImpl(commandContext)));
        return this;
    }

    public EzCommand executesPlayer(PlayerInvoker executes) {
        requiredArgumentBuilder.executes(commandContext -> executes.execute(commandContext.getSource().i().getBukkitEntity(), new EzArgumentHelperImpl(commandContext)));
        return this;
    }

    public EzArgumentImpl redirect(EzRegisteredCommand ezCommandRegistered) {
        requiredArgumentBuilder.redirect(((EzCommandRegistered) ezCommandRegistered).commandNode);
        return this;
    }

    public EzArgumentImpl requires(Function<CommandSender, Boolean> function) {
        Predicate<CommandListenerWrapper> predicate = this.requiredArgumentBuilder.getRequirement();
        this.requiredArgumentBuilder.requires((object) -> {
            CommandSender sender = object.getBukkitSender();
            return predicate.test(object) && function.apply(sender);
        });
        return this;
    }

    public EzArgumentImpl suggest(DuParameter<CommandSender, SuggestionsBuilder> biFunction) {
        requiredArgumentBuilder.suggests(((commandContext, suggestionsBuilder) -> {
            biFunction.apply(commandContext.getSource().getBukkitSender(), suggestionsBuilder);
            return suggestionsBuilder.buildFuture();
        }));
        return this;
    }

}
