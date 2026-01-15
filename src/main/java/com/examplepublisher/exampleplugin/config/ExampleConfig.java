package com.examplepublisher.exampleplugin.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public final class ExampleConfig {

    public static final BuilderCodec<ExampleConfig> CODEC = BuilderCodec.builder(ExampleConfig.class, ExampleConfig::new)
            .append(new KeyedCodec<>("JoinMessage", Codec.STRING),
                    (config, value, ignored) -> config.joinMessage = value,
                    (config, ignored) -> config.joinMessage)
            .add()
            .build();

    private String joinMessage = "Hello from the ExamplePlugin Config!";

    public String joinMessage() {
        return joinMessage;
    }
}