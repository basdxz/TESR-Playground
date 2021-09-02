package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import net.minecraftforge.common.util.ForgeDirection;

public interface LayeredIcon {
    Iterable<BlendableIcon> getBlendableIconLayers(ForgeDirection side);
}
