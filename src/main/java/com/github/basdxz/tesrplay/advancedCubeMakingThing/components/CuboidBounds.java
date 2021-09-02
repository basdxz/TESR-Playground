package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Value
public class CuboidBounds {
    public static final CuboidBounds CUBE_BOUNDS = new CuboidBounds(new PosXYZ(0, 0, 0), new PosXYZ(1, 1, 1));

    PosXYZ min;
    PosXYZ max;
}
