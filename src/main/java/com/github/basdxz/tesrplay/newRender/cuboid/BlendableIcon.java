package com.github.basdxz.tesrplay.newRender.cuboid;

import net.minecraft.util.IIcon;

public interface BlendableIcon extends IIcon {
    boolean doStretch();

    double rotation();

    boolean skipScale();

    ColorRGBA colorRGBA();

    boolean flatTint();

    int renderPass();//TODO Determine this yourself with a proper check

    boolean noDraw();

    void applyBlending();

    default double scaleU() {
        return 1.0D;
    }

    default double scaleV() {
        return 1.0D;
    }

    default double offsetU() {
        return 0.0D;
    }

    default double offsetV() {
        return 0.0D;
    }
}
