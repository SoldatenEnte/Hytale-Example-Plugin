package com.examplepublisher.exampleplugin;

import com.examplepublisher.exampleplugin.commands.ExampleCommand;
import com.examplepublisher.exampleplugin.commands.ExampleGuiCommand;
import com.examplepublisher.exampleplugin.config.ExampleConfig;
import com.examplepublisher.exampleplugin.events.ExampleEventHandler;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;

public class ExamplePlugin extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    private final Config<ExampleConfig> config;

    public ExamplePlugin(JavaPluginInit init) {
        super(init);
        this.config = this.withConfig("ExamplePlugin", ExampleConfig.CODEC);
    }

    public Config<ExampleConfig> getConfig() {
        return config;
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + getName());

        saveConfig();
        registerCommands();
        registerEvents();

        LOGGER.atInfo().log("Plugin " + getName() + " initialized");
    }

    private void saveConfig() {
        this.config.save();
    }

    private void registerCommands() {
        this.getCommandRegistry().registerCommand(new ExampleCommand(config));
        this.getCommandRegistry().registerCommand(new ExampleGuiCommand(config));
    }

    private void registerEvents() {
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEventHandler::onPlayerReady);
    }
}