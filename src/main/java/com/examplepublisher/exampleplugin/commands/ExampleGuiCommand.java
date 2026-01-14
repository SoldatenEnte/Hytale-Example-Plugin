package com.examplepublisher.exampleplugin.commands;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;

import com.examplepublisher.exampleplugin.config.ExampleConfig;
import com.examplepublisher.exampleplugin.gui.ExampleGui;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;

public class ExampleGuiCommand extends AbstractAsyncCommand {

    private final Config<ExampleConfig> config;

    public ExampleGuiCommand(Config<ExampleConfig> config) {
        super("examplegui", "Opens the example GUI");
        this.config = config;
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Nonnull
    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext commandContext) {
        CommandSender sender = commandContext.sender();

        if (sender instanceof Player player) {
            player.getWorldMapTracker().tick(0);
            Ref<EntityStore> ref = player.getReference();

            if (ref != null && ref.isValid()) {
                Store<EntityStore> store = ref.getStore();
                World world = store.getExternalData().getWorld();

                return CompletableFuture.runAsync(() -> {
                    PlayerRef playerRefComponent = store.getComponent(ref, PlayerRef.getComponentType());
                    
                    if (playerRefComponent != null) {
                        String configMessage = config.get().getJoinMessage();
                        // Open GUI on world thread
                        player.getPageManager().openCustomPage(ref, store, new ExampleGui(playerRefComponent, configMessage));
                    }
                }, world);
            }
        }
        
        return CompletableFuture.completedFuture(null);
    }
}