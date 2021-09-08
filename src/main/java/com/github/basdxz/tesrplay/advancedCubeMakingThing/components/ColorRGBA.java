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
    float r;
    float g;
    float b;
    float a;

    public void set(ColorRGBA that) {
        r = that.r();
        g = that.g();
        b = that.b();
        a = that.a();
    }

    public ColorRGBA mult(float factor) {
        return new ColorRGBA(r * factor, r * factor, g * factor, a);
    }
}
