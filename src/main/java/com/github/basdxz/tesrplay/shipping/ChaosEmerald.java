package com.github.basdxz.tesrplay.shipping;

import com.github.basdxz.tesrplay.newRender.model.Model;
import com.github.basdxz.tesrplay.newRender.providers.ModelRenderProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/*
    Creates 16 variants using the minecraft pallet.
    Transparent gem model with a single orientation using hard Min functions
 */
public class ChaosEmerald extends BaseBlock implements ModelRenderProvider {
    public ChaosEmerald(Material material) {
        super(material,0);
    }

    @Override
    public Model getModel() {
        return null;
    }

}
