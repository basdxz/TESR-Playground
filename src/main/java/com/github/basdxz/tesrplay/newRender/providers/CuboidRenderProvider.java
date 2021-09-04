package com.github.basdxz.tesrplay.newRender.providers;

import com.github.basdxz.tesrplay.AOScratch.CuboidRenderer;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.CuboidBounds;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.LayeredIcon;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.PosXYZ;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public interface CuboidRenderProvider extends RenderProvider {
    @Override
    default void renderWorldBlock(RenderBlocks renderer, IBlockAccess world, ForgeDirection direction, int posX, int posY, int posZ) {
        new CuboidRenderer(getBlock(), new PosXYZ(posX, posY, posZ), getBounds(), getTextureLayers()).render();
    }

    @Override
    default void renderInventoryBlock(RenderBlocks renderer) {
    }

    LayeredIcon getTextureLayers();

    default CuboidBounds getBounds() {
        return CuboidBounds.CUBE_BOUNDS;
    }
}
