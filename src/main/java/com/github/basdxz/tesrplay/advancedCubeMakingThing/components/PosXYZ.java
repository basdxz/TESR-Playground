package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.Value;
import lombok.experimental.Accessors;


@Accessors(fluent = true)
@Value
public class PosXYZ {
    double x;
    double y;
    double z;

    public PosXYZ add(PosXYZ that) {
        return new PosXYZ(x + that.x, y + that.y, z + that.z);
    }
}
