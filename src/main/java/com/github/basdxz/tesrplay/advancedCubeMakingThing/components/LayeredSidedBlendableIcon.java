package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import net.minecraftforge.common.util.ForgeDirection;

public interface LayeredSidedBlendableIcon {
    Iterable<BlendableIcon> getBlendableIconLayers(ForgeDirection side);
}
