package com.examplepublisher.exampleplugin.commands;

import javax.annotation.Nonnull;

import com.examplepublisher.exampleplugin.config.ExampleConfig;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;

public class ExampleCommand extends CommandBase {

    private final Config<ExampleConfig> config;

    public ExampleCommand(Config<ExampleConfig> config) {
        super("example", "A simple example command");
        this.config = config;
        // Allows players in Adventure mode (default) to use this command
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext context) {
        // Read message from config
        String message = config.get().getJoinMessage();
        context.sendMessage(Message.raw(message));
    }
}