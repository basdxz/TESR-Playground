package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColorRGBA {
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
