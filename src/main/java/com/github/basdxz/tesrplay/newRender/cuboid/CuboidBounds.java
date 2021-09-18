package com.github.basdxz.tesrplay.newRender.cuboid;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.function.BiFunction;

import static com.github.basdxz.tesrplay.newRender.cuboid.CuboidBounds.CuboidBoundGetters.*;

@Accessors(fluent = true)
@Getter
public class CuboidBounds {
    public static final CuboidBounds CUBE_BOUNDS = new CuboidBounds(new PosXYZ(0, 0, 0), new PosXYZ(1, 1, 1));
    public static final CuboidBounds CUBE_BOUNDS_IV = new CuboidBounds(new PosXYZ(-1, -1, -1), new PosXYZ(2, 2, 2));

    private final PosXYZ min;
    private final PosXYZ max;

    //TODO these should be integers but i don't care right now
    private final PosXYZ minAOOffset;
    private final PosXYZ maxAOOffset;

    public CuboidBounds(PosXYZ min, PosXYZ max) {
        this.min = min;
        this.max = max;
        minAOOffset = generateMinAOOffset();
        maxAOOffset = generateMaxAOOffset();
    }

    private PosXYZ generateMinAOOffset() {
        return new PosXYZ().set(generateAOOffsetsMin(getPos(MIN_X, MIN_Y, MIN_Z))).add(-1.0D);
    }

    private PosXYZ generateMaxAOOffset() {
        return new PosXYZ().set(generateAOOffsetsMax(getPos(MAX_X_INV, MAX_Y_INV, MAX_Z_INV))).add(1.0D);
    }

    private static double[] generateAOOffsetsMin(double... boundPos) {
        double[] posAOOffsetArray = new double[boundPos.length];
        Arrays.setAll(posAOOffsetArray, i -> Math.ceil(boundPos[i]));
        return posAOOffsetArray;
    }

    private static double[] generateAOOffsetsMax(double... boundPos) {
        double[] posAOOffsetArray = new double[boundPos.length];
        Arrays.setAll(posAOOffsetArray, i -> Math.floor(boundPos[i]));
        return posAOOffsetArray;
    }

    public enum CuboidBoundGetters implements BiFunction<PosXYZ, PosXYZ, Double> {
        ZERO((min, max) -> 0.0D),

        MIN_X((min, max) -> min.x()),
        MIN_X_COMP((min, max) -> 1 - min.x()),
        MIN_X_INV((min, max) -> min.x() - 1),
        MAX_X((min, max) -> max.x()),
        MAX_X_COMP((min, max) -> 1 - max.x()),
        MAX_X_INV((min, max) -> max.x() - 1),

        MIN_Y((min, max) -> min.y()),
        MIN_Y_COMP((min, max) -> 1 - min.y()),
        MIN_Y_INV((min, max) -> min.y() - 1),
        MAX_Y((min, max) -> max.y()),
        MAX_Y_COMP((min, max) -> 1 - max.y()),
        MAX_Y_INV((min, max) -> max.y() - 1),
        MAX_Y_LIM((min, max) -> Math.max(1, max.y())),

        MIN_Z((min, max) -> min.z()),
        MIN_Z_COMP((min, max) -> 1 - min.z()),
        MIN_Z_INV((min, max) -> min.z() - 1),
        MAX_Z((min, max) -> max.z()),
        MAX_Z_COMP((min, max) -> 1 - max.z()),
        MAX_Z_INV((min, max) -> max.z() - 1);

        private final BiFunction<PosXYZ, PosXYZ, Double> biFunction;

        CuboidBoundGetters(BiFunction<PosXYZ, PosXYZ, Double> biFunction) {
            this.biFunction = biFunction;
        }

        @Override
        public Double apply(PosXYZ posXYZ, PosXYZ posXYZ2) {
            return biFunction.apply(posXYZ, posXYZ2);
        }
    }

    public double[] getPos(CuboidBoundGetters... getter) {
        double[] posArray = new double[getter.length];
        for (int i = 0; i < posArray.length; i++)
            posArray[i] = getPos(getter[i]);
        return posArray;
    }

    public double getPos(CuboidBoundGetters getter) {
        return getter.apply(min, max);
    }

    public int[] getPosAOOffset(CuboidBoundGetters... getter) {
        int[] posAOOffsetArray = new int[getter.length];
        for (int i = 0; i < posAOOffsetArray.length; i++)
            posAOOffsetArray[i] = getPosAOOffset(getter[i]);
        return posAOOffsetArray;
    }

    public int getPosAOOffset(CuboidBoundGetters getter) {
        try {
            return Math.toIntExact(Math.round(getter.apply(minAOOffset, maxAOOffset)));
        } catch (NumberFormatException e) {
            throw new ArithmeticException("Bounding box too big somehow.");
        }
    }
}
