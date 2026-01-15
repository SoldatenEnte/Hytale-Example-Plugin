package com.examplepublisher.exampleplugin.commands;

import com.examplepublisher.exampleplugin.config.ExampleConfig;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.util.Config;

public final class ExampleCommand extends CommandBase {

    private final Config<ExampleConfig> config;

    public ExampleCommand(Config<ExampleConfig> config) {
        super("example", "A simple example command");
        this.config = config;
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Override
    protected void executeSync(CommandContext context) {
        context.sendMessage(Message.raw(config.get().joinMessage()));
    }
}
