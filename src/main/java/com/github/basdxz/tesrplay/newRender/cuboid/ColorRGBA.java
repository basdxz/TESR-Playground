package com.github.basdxz.tesrplay.newRender.cuboid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.awt.*;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColorRGBA {
    public static final ColorRGBA NO_COLOR = new ColorRGBA(1,1,1,1);

    private float r;
    private float g;
    private float b;
    private float a;

    public ColorRGBA(Color color) {
        r = color.getRed() / 255F;
        g = color.getGreen() / 255F;
        b = color.getBlue() / 255F;
        a = color.getAlpha() / 255F;
    }

    public ColorRGBA set(ColorRGBA that) {
        r = that.r();
        g = that.g();
        b = that.b();
        a = that.a();
        return this;
    }

    public void mult(float factor) {
        r *= factor;
        g *= factor;
        b *= factor;
    }
}
