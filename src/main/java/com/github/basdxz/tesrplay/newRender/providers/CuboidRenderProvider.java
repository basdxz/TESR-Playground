package com.github.basdxz.tesrplay.newRender.providers;

import com.github.basdxz.tesrplay.newRender.cuboid.CuboidBounds;
import com.github.basdxz.tesrplay.newRender.cuboid.CuboidRenderer;
import com.github.basdxz.tesrplay.newRender.cuboid.LayeredIcon;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public interface CuboidRenderProvider extends RenderProvider {
    @Override
    default void renderWorldBlock(IBlockAccess world, int posX, int posY, int posZ, Block block) {
        val metadata = world.getBlockMetadata(posX, posY, posZ);
        CuboidRenderer.renderWorldBlock(world, posX, posY, posZ, block, getBounds(), getTextureLayers(metadata));
    }

    @Override
    default void renderInventoryBlock(Block block, int metadata) {
        CuboidRenderer.renderInventoryBlock(block, getBounds(), getTextureLayers(metadata));
    }

    LayeredIcon getTextureLayers(int metadata);

    default CuboidBounds getBounds() {
        return CuboidBounds.CUBE_BOUNDS;
    }
}
