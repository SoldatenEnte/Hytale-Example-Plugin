package com.examplepublisher.exampleplugin;

import java.util.logging.Level;

import javax.annotation.Nonnull;

import com.examplepublisher.exampleplugin.commands.ExampleCommand;
import com.examplepublisher.exampleplugin.commands.ExampleGuiCommand;
import com.examplepublisher.exampleplugin.config.ExampleConfig;
import com.examplepublisher.exampleplugin.events.ExampleEventHandler;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;

public class ExamplePlugin extends JavaPlugin {

    private static ExamplePlugin INSTANCE;
    private final Config<ExampleConfig> config;

    public static ExamplePlugin getInstance() {
        return INSTANCE;
    }

    public ExamplePlugin(@Nonnull JavaPluginInit init) {
        super(init);
        INSTANCE = this;
        this.config = this.withConfig("ExamplePlugin", ExampleConfig.CODEC);
    }

    public Config<ExampleConfig> getConfig() {
        return config;
    }

    @Override
    protected void setup() {
        super.setup();
        
        this.getLogger().at(Level.INFO).log("Initializing ExamplePlugin...");

        this.config.save();

        this.getCommandRegistry().registerCommand(new ExampleCommand(config));
        this.getCommandRegistry().registerCommand(new ExampleGuiCommand(config));
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEventHandler::onPlayerReady);

        this.getLogger().at(Level.INFO).log("ExamplePlugin loaded successfully!");
    }
}