package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.*;
import lombok.experimental.Accessors;


//TODO: make a constructor that makes max = min + 1
@Accessors(fluent = true)
@Value
public class CuboidBounds {
    PosXYZ min;
    PosXYZ max;
}
