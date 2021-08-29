package com.github.basdxz.tesrplay.newRender.providers;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.RenderMessAround;
import lombok.val;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public interface CubeRenderProvider extends RenderProvider {
    @Override
    default void renderWorldBlock(RenderBlocks renderer, IBlockAccess world, ForgeDirection direction, int posX, int posY, int posZ) {
        //Tessellator.instance.setColorOpaque_F(1, 1, 1);
        //Drawing.renderAllFaces(renderer, null, 0, 0, 0, Blocks.iron_block.getIcon(0, 0));

        val icon = Blocks.stained_glass.getIcon(0, 0);
        RenderMessAround.renderFaceYPos(null, posX, posY, posZ, icon);

        //posX = 0;
        //posY = 0;
        //posZ = 0;

        //val bottomLeftCorner = new QuadCorner(posX, posY + 1, posZ, icon.getMinU(), icon.getMinV(), 0, 0, 0, 0);
        //val bottomRightCorner = new QuadCorner(posX + 1, posY + 1, posZ, icon.getMaxU(), icon.getMinV(), 0, 0, 0, 0);
        //val topLeftCorner = new QuadCorner(posX, posY + 1, posZ + 1, icon.getMinU(), icon.getMaxV(), 0, 0, 0, 0);
        //val topRightCorner = new QuadCorner(posX + 1, posY + 1, posZ + 1, icon.getMaxU(), icon.getMaxV(), 0, 0, 0, 0);
        //val quad = new FullQuad(topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner);
        //RenderMessAround.renderQuad(quad);
    }

    @Override
    default void renderInventoryBlock(RenderBlocks renderer) {
        //
    }
}
