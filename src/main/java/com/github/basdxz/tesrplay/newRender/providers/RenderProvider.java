package com.github.basdxz.tesrplay.newRender.providers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public interface RenderProvider {
    int getRenderType();

    void renderWorldBlock(RenderBlocks renderer, IBlockAccess world, ForgeDirection direction,
                          int posX, int posY, int posZ);

    void renderInventoryBlock(RenderBlocks renderer);

    Block getBlock();
}
