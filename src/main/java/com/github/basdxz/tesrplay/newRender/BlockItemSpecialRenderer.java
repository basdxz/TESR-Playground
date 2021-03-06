package com.github.basdxz.tesrplay.newRender;

import com.github.basdxz.tesrplay.newRender.providers.RenderProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockItemSpecialRenderer extends BlockRenderingHandler {
    private static BlockRenderingHandler INSTANCE = new BlockItemSpecialRenderer();

    public static void load() {
        INSTANCE = new BlockItemSpecialRenderer();
    }

    public static int getInstanceRenderId() {
        return INSTANCE.getRenderId();
    }

    //This should crash when you try to render poorly.
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int posX, int posY, int posZ, Block block, int modelId,
                                    RenderBlocks renderer) {
        if (block instanceof RenderProvider)
            ((RenderProvider) block).renderWorldBlock(world, posX, posY, posZ, block);
        // Check blocks for the correct interface
        // Get the tile entity in world from the pos
        // Check the tile entity for the RenderProvider interface
        // Cast the RenderProvider and call the renderWorldBlock() method
        return true;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        if (block instanceof RenderProvider)
            ((RenderProvider) block).renderInventoryBlock(block, metadata);
        // Check blocks for the correct interface
        // Get the metablock using the metadata
        // Call getModel() on the metablock
        // Cast the RenderProvider and call the renderInventoryBlock() method
    }
}
