package com.github.basdxz.tesrplay.newRender.cuboid;

import net.minecraftforge.common.util.ForgeDirection;

public interface LayeredIcon {
    Iterable<BlendableIcon> getBlendableIconLayers(ForgeDirection side);
}
