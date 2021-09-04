package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import net.minecraft.util.IIcon;

//TODO implement glow
//TODO
public interface BlendableIcon extends IIcon {
    void applyBlending(boolean noDraw);

    boolean doStretch();

    boolean hasAlpha();

    boolean noDraw();

    double rotation();
}
