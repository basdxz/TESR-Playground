package com.github.basdxz.tesrplay.newRender.providers;

import com.github.basdxz.tesrplay.newRender.model.Model;
import com.github.basdxz.tesrplay.newRender.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public interface ModelRenderProvider extends RenderProvider {
    @Override
    default void renderWorldBlock(RenderBlocks renderer, IBlockAccess world,
                                  ForgeDirection direction, int posX, int posY, int posZ) {
        ModelRenderer.renderWorldBlock(getModel(), direction, posX, posY, posZ);
    }

    @Override
    default void renderInventoryBlock(RenderBlocks renderer) {
        ModelRenderer.renderInventoryBlock(getModel());
    }

    Model getModel();
}
