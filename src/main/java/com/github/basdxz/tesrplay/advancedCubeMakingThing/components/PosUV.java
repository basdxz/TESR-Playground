package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.Rotation;
import lombok.*;
import lombok.experimental.Accessors;
import net.minecraft.util.IIcon;
import org.ejml.simple.SimpleMatrix;

@Accessors(fluent = true)
@Value
public class PosUV {
    private static final double ICON_SCALE = 16.0D;
    private static final double CENTER_OFFSET = -0.5D;

    double posU;
    double posV;

    @Builder
    private PosUV(@NonNull IIcon icon, double posU, double posV, double rotRad, Rotation rotOrd, boolean skipScale) {
        posU = clampUV(posU);
        posV = clampUV(posV);

        if (rotOrd != null)
            rotRad = -rotOrd.getRad();

        if (rotRad != 0.0D) {
            val rotatedPosUV = rotate(posU, posV, rotRad, skipScale);
            posU = rotatedPosUV.posU();
            posV = rotatedPosUV.posV();
        }

        this.posU = icon.getInterpolatedU(posU * ICON_SCALE);
        this.posV = icon.getInterpolatedV(posV * ICON_SCALE);
    }

    private static double clampUV(double pos) {
        return Math.max(0.0D, Math.min(pos, 1.0D));
    }

    private PosUV(double posU, double posV) {
        this.posU = posU;
        this.posV = posV;
    }

    private static PosUV rotate(double posU, double posV, double rotRad, boolean skipScale) {
        var posMatrix = getPosMatrix(posU, posV);

        posMatrix = offsetMatrixToCenter(posMatrix);
        posMatrix = rotateMatrix(posMatrix, rotRad);
        posMatrix = scaleMatrix(posMatrix, rotRad, skipScale);
        posMatrix = offsetMatrixFromCenter(posMatrix);

        return new PosUV(posMatrix.get(0), posMatrix.get(1));
    }

    private static SimpleMatrix getPosMatrix(double posU, double posV) {
        return new SimpleMatrix(
                new double[][]{
                        new double[]{posU},
                        new double[]{posV}
                }
        );
    }

    private static SimpleMatrix offsetMatrixToCenter(SimpleMatrix matrix) {
        return matrix.plus(CENTER_OFFSET);
    }

    private static SimpleMatrix offsetMatrixFromCenter(SimpleMatrix matrix) {
        return matrix.minus(CENTER_OFFSET);
    }

    private static SimpleMatrix rotateMatrix(SimpleMatrix matrix, double rotRad) {
        return getRotationMatrix(rotRad).mult(matrix);
    }

    private static SimpleMatrix getRotationMatrix(double rotRad) {
        return new SimpleMatrix(
                new double[][]{
                        new double[]{Math.cos(rotRad), -Math.sin(rotRad)},
                        new double[]{Math.sin(rotRad), Math.cos(rotRad)}
                }
        );
    }

    private static SimpleMatrix scaleMatrix(SimpleMatrix matrix, double rotRad, boolean skipScale) {
        if (skipScale) return matrix;
        return getScalingMatrix(rotRad).mult(matrix);
    }

    private static SimpleMatrix getScalingMatrix(double rotRad) {
        val scale = getScale(rotRad);
        return new SimpleMatrix(
                new double[][]{
                        new double[]{scale, 0},
                        new double[]{0, scale}
                }
        );
    }

    /**
     * This method is divine, scales the texture proportional to rotation going from 1 to sqrt(1/2)
     * Written with a lot of laughter and fun in the TecTech Voice Chat on 30th of August 2021 by Tec
     *
     * @param rotRad Rotation in Radians
     * @return The minimum scale to prevent texture atlas overlap
     */
    private static double getScale(double rotRad) {
        var scale = rotRad % (Math.PI / 2);
        if (scale > (Math.PI / 4))
            scale = (Math.PI / 2) - scale;
        return Math.sqrt(2) / Math.cos(Math.PI / 4 - scale) / 2;
    }
}
