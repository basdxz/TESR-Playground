package com.github.basdxz.tesrplay.newRender.providers;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public interface RenderProvider {
    void renderWorldBlock(IBlockAccess world, int posX, int posY, int posZ, Block block);

    void renderInventoryBlock(Block block, int metadata);
}
