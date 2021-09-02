package com.github.basdxz.tesrplay.newRender.providers;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendEquations;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendFuncs;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.RenderMessAround;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.MaterialTexture;
import com.github.basdxz.tesrplay.newRender.TestBlock;
import lombok.val;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public interface CubeRenderProvider extends RenderProvider {
    @Override
    default void renderWorldBlock(RenderBlocks renderer, IBlockAccess world, ForgeDirection direction, int posX, int posY, int posZ) {
        //Tessellator.instance.setColorOpaque_F(1, 1, 1);
        //Drawing.renderAllFaces(renderer, null, 0, 0, 0, Blocks.iron_block.getIcon(0, 0));

        //val icon = Blocks.stainedD_glass.getIcon(0, 9);

        val woagGoag = MaterialTexture.builder()
                .icon(TestBlock.RED_TINT)
                .glBlendEquation(GLBlendEquations.MIN)
                .glBlendFunc(GLBlendFuncs.ADDITIVE)
                .build();

        RenderMessAround.cubeDrawingButRoundAboutWay(woagGoag, posX, posY, posZ);

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
