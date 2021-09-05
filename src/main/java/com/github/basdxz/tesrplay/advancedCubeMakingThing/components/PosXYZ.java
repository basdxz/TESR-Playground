package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.Callable;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PosXYZ {
    private double x;
    private double y;
    private double z;

    public PosXYZ set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public PosXYZ add(PosXYZ that) {
        x += that.x;
        y += that.y;
        z += that.z;
        return this;
    }
}
