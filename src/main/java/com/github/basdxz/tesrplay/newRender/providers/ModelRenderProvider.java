package com.github.basdxz.tesrplay.newRender.providers;

import com.github.basdxz.tesrplay.newRender.model.Model;
import com.github.basdxz.tesrplay.newRender.model.ModelRenderer;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public interface ModelRenderProvider extends RenderProvider {
    @Override
    default void renderWorldBlock(IBlockAccess world, int posX, int posY, int posZ, Block block) {
        ModelRenderer.renderWorldBlock(getModel(), null, posX, posY, posZ);//add rotation support
    }

    @Override
    default void renderInventoryBlock(Block block, int metadata) {
        ModelRenderer.renderInventoryBlock(getModel());
    }

    Model getModel();
}
