package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.*;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Value
public class CuboidBounds {
    PosXYZ min;
    PosXYZ max;
}
