package com.github.basdxz.tesrplay;

import com.github.basdxz.tesrplay.newRender.ModelProvider;
import com.github.basdxz.tesrplay.newRender.TextureProvider;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class ObamaBlockRenderer implements ISimpleBlockRenderingHandler {
    private final ObamaRendererr wavefrontBlockSpecialRenderer;
    private final int mRenderID;

    public ObamaBlockRenderer(String name) {
        wavefrontBlockSpecialRenderer = new ObamaRendererr(name);
        mRenderID = RenderingRegistry.getNextAvailableRenderId();
        registerRenderer();
    }

    private void registerRenderer() {
        RenderingRegistry.registerBlockHandler(this);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
                                    RenderBlocks renderer) {
        wavefrontBlockSpecialRenderer.renderBlock(x, y, z);
        return true;
    }



    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        wavefrontBlockSpecialRenderer.renderItem();
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return mRenderID;
    }
}
