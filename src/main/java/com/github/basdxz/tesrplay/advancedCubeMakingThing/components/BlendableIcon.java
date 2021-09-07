package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import net.minecraft.util.IIcon;

//TODO: Blending should be provided as a Runnable
public interface BlendableIcon extends IIcon {
    boolean doStretch();

    double rotation();

    boolean skipScale();

    ColorRGBA colorRGBA();

    boolean hasAlpha();

    boolean noDraw();

    void applyBlending(boolean noDraw);
}
