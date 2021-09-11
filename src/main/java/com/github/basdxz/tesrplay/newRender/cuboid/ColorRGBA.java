package com.github.basdxz.tesrplay.newRender.cuboid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
