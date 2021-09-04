package com.github.basdxz.tesrplay.shipping;

import com.github.basdxz.tesrplay.newRender.model.Model;
import com.github.basdxz.tesrplay.newRender.providers.ModelRenderProvider;
import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/*
    Creates a single in-world model of the Obama prism which is rotated based on placement.
    Constant lighting and glow for glow-in-the-dark effect with no real light.
 */
public class ObamaPrism extends BaseBlock implements ModelRenderProvider {
    public ObamaPrism(Material material) {
        super(material);
    }

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public Block getBlock() {
        return null;
    }
}
