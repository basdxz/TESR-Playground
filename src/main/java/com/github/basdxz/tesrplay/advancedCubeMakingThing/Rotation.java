package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import lombok.Getter;
import lombok.val;

@Getter
public enum Rotation {
    CW0, CW90, CW180, CW270;

    private final double rad;

    Rotation() {
        this.rad = getRadFromName();
    }

    private double getRadFromName() {
        val deg = Integer.parseInt(name().replaceAll("[\\D]", ""));
        return Math.toRadians(deg);
    }
}
