package com.examplepublisher.exampleplugin.commands;

import java.util.concurrent.CompletableFuture;

import com.examplepublisher.exampleplugin.config.ExampleConfig;
import com.examplepublisher.exampleplugin.gui.ExampleGui;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;

public final class ExampleGuiCommand extends AbstractAsyncCommand {

    private final Config<ExampleConfig> config;

    public ExampleGuiCommand(Config<ExampleConfig> config) {
        super("examplegui", "Opens the example GUI");
        this.config = config;
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        if (!(context.sender() instanceof Player player)) {
            return CompletableFuture.completedFuture(null);
        }

        player.getWorldMapTracker().tick(0);

        Ref<EntityStore> ref = player.getReference();
        if (ref == null || !ref.isValid()) {
            return CompletableFuture.completedFuture(null);
        }

        Store<EntityStore> store = ref.getStore();
        World world = store.getExternalData().getWorld();

        return CompletableFuture.runAsync(() -> {
            PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());
            if (playerRef != null) {
                player.getPageManager()
                        .openCustomPage(ref, store,
                                new ExampleGui(playerRef, config.get().joinMessage()));
            }
        }, world);
    }
}