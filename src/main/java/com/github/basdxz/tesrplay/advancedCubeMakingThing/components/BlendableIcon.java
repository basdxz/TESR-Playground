package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import net.minecraft.util.IIcon;

//TODO Should pass along expected rotation and implement glow now glow along side passing alpha state
public interface BlendableIcon extends IIcon {
    void applyBlending();
}
