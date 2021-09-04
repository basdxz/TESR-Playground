package com.github.basdxz.tesrplay.shipping;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.LayeredIcon;
import com.github.basdxz.tesrplay.newRender.providers.CuboidRenderProvider;
import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/*
    Creates four casings based on pre-made connected base textures.
    Second layer of clutter being applied on top with real alpha blending for best effect
    Third layer of grime generated on top.
    Second and Third layers have states determined by placement location, connected state and side drawn.
 */
public class IndustrialCasing extends BaseBlock implements CuboidRenderProvider {
    public IndustrialCasing(Material material) {
        super(material);
    }

    @Override
    public LayeredIcon getTextureLayers() {
        return null;
    }

    @Override
    public Block getBlock() {
        return null;
    }
}
