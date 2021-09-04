package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.Value;
import lombok.experimental.Accessors;


@Accessors(fluent = true)
@Value
public class ColorRGBA {
    float r;
    float g;
    float b;
    float a;

    public ColorRGBA mult(float factor) {
        return new ColorRGBA(r * factor, r * factor, g * factor, a);
    }
}
