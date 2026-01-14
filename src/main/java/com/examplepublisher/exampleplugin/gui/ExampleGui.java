package com.examplepublisher.exampleplugin.gui;

import javax.annotation.Nonnull;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class ExampleGui extends InteractiveCustomUIPage<ExampleGui.ExampleGuiData> {

    private final String message;

    public ExampleGui(@Nonnull PlayerRef playerRef, String message) {
        super(playerRef, CustomPageLifetime.CanDismiss, ExampleGuiData.CODEC);
        this.message = message;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/ExamplePublisher_ExamplePlugin_Gui.ui");
        
        uiCommandBuilder.set("#WelcomeMessage.Text", this.message);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull ExampleGuiData data) {
        super.handleDataEvent(ref, store, data);
    }

    public static class ExampleGuiData {
        public static final BuilderCodec<ExampleGuiData> CODEC = BuilderCodec.builder(ExampleGuiData.class, ExampleGuiData::new)
                .build();
    }
}