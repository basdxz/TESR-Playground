package com.github.basdxz.tesrplay.newRender;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;

public abstract class BlockItemSpecialRenderer implements ISimpleBlockRenderingHandler {
    @Getter
    private final int renderId;

    public BlockItemSpecialRenderer() {
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

    protected void renderWorldBlock(TextureProvider texture, RenderBlocks renderer, int posX, int posY, int posZ) {
    }

    protected void renderWorldBlock(ModelProvider model, RenderBlocks renderer, int posX, int posY, int posZ) {
    }

    public void renderInventoryBlock(TextureProvider texture, RenderBlocks renderer) {
    }

    public void renderInventoryBlock(ModelProvider model, RenderBlocks renderer) {
    }
}
