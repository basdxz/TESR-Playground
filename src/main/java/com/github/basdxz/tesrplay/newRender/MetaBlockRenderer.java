package com.github.basdxz.tesrplay.newRender;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MetaBlockRenderer extends BlockItemSpecialRenderer {
    @Getter
    private static BlockItemSpecialRenderer INSTANCE = new MetaBlockRenderer();

    public static void load() {
        INSTANCE = new MetaBlockRenderer();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int posX, int posY, int posZ, Block block, int modelId,
                                    RenderBlocks renderer) {
        // Check block for the correct interface
        // Get the tile entity in world from the pos
        // Call getModel() on the metablock
        return true;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        // Check block for the correct interface
        // Get the metablock using the metadata
        // Call getModel() on the metablock
        //
    }
}
