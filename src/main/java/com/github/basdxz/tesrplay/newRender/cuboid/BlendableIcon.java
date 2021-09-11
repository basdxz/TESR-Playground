package com.github.basdxz.tesrplay.newRender.cuboid;

import net.minecraft.util.IIcon;

//TODO: Blending should be provided as a Runnable
public interface BlendableIcon extends IIcon {
    boolean doStretch();

    double rotation();

    boolean skipScale();

    ColorRGBA colorRGBA();

    boolean flatTint();

    boolean hasAlpha();

    boolean noDraw();

    void applyBlending();
}
