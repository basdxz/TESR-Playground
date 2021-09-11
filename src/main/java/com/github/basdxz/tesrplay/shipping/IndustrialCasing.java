package com.github.basdxz.tesrplay.shipping;

import com.github.basdxz.tesrplay.newRender.cuboid.LayeredIcon;
import com.github.basdxz.tesrplay.newRender.providers.CuboidRenderProvider;
import net.minecraft.block.material.Material;

/*
    Creates four casings based on pre-made connected base textures.
    Second layer of clutter being applied on top with real alpha blending for best effect
    Third layer of grime generated on top.
    Second and Third layers have states determined by placement location, connected state and side drawn.
 */
public class IndustrialCasing extends BaseBlock implements CuboidRenderProvider {
    public IndustrialCasing(Material material) {
        super(material,0);
    }

    @Override
    public LayeredIcon getTextureLayers(int metadata) {
        return null;
    }
}
