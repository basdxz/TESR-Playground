package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.function.BiFunction;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public class CuboidBounds {
    public static final CuboidBounds CUBE_BOUNDS = new CuboidBounds(new PosXYZ(0, 0, 0), new PosXYZ(1, 1, 1));

    private final PosXYZ min;
    private final PosXYZ max;

    public enum CuboidBoundGetters {
        MIN_X((min, max) -> min.x()),
        MIN_X_COMP((min, max) -> 1 - min.x()),
        MAX_X((min, max) -> max.x()),
        MAX_X_COMP((min, max) -> 1 - max.x()),

        MIN_Y((min, max) -> min.y()),
        MIN_Y_COMP((min, max) -> 1 - min.y()),
        MAX_Y((min, max) -> max.y()),
        MAX_Y_COMP((min, max) -> 1 - max.y()),

        MIN_Z((min, max) -> min.z()),
        MIN_Z_COMP((min, max) -> 1 - min.z()),
        MAX_Z((min, max) -> max.z()),
        MAX_Z_COMP((min, max) -> 1 - max.z());

        private final BiFunction<PosXYZ, PosXYZ, Double> biFunction;

        CuboidBoundGetters(BiFunction<PosXYZ, PosXYZ, Double> biFunction) {
            this.biFunction = biFunction;
        }
    }

    public double getPos(CuboidBoundGetters getter) {
        return getter.biFunction.apply(min, max);
    }
}
