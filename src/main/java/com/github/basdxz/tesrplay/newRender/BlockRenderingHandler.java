package com.github.basdxz.tesrplay.newRender;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import lombok.Getter;

public abstract class BlockRenderingHandler implements ISimpleBlockRenderingHandler {
    @Getter
    private final int renderId;

    public BlockRenderingHandler() {
        renderId = generateRenderID();
        registerBlockHandler();
    }

    private int generateRenderID() {
        return RenderingRegistry.getNextAvailableRenderId();
    }

    private void registerBlockHandler() {
        RenderingRegistry.registerBlockHandler(this);
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
