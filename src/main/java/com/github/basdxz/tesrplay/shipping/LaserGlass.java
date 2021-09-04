package com.github.basdxz.tesrplay.shipping;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.LayeredIcon;
import com.github.basdxz.tesrplay.newRender.providers.CuboidRenderProvider;
import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/*
    Creates 16 variants using the minecraft pallet.
    Transparent model using more realistic lensing with a set number of hues
    Has a second layer with a frame and connecting textures
 */
public class LaserGlass extends BaseBlock implements CuboidRenderProvider {
    public LaserGlass(Material material) {
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
