package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import lombok.Value;
import lombok.experimental.Accessors;
import lombok.val;
import lombok.var;
import net.minecraft.util.IIcon;
import org.ejml.simple.SimpleMatrix;

import java.util.Random;

import static java.lang.Math.toRadians;

@Value
@Accessors(fluent = true)
public class PosUV {
    private static final double ICON_SCALE = 16.0D;
    private static final double CENTER_TRANSLATION = -0.5D;
    private static final double SCALE_ROTATION_RATIO = Math.sqrt(0.5D) - 1.0D;

    double posU;
    double posV;

    public PosUV(IIcon icon, double posU, double posV, double rot) {
        rot = toRadians(rot);
        posU = clamp(posU);
        posV = clamp(posV);

        var scaleRatio = 1.0D;

        scaleRatio = rot % (Math.PI / 2);
        if (scaleRatio > (Math.PI / 4))
            scaleRatio = (Math.PI / 2) - scaleRatio;
        // This function is divine
        scaleRatio = Math.sqrt(2) / Math.cos(Math.PI / 4 - scaleRatio) / 2;

        val rotation = new SimpleMatrix(
                new double[][]{
                        new double[]{Math.cos(rot), -Math.sin(rot)},
                        new double[]{Math.sin(rot), Math.cos(rot)}
                }
        );

        val scale = new SimpleMatrix(
                new double[][]{
                        new double[]{scaleRatio, 0},
                        new double[]{0, scaleRatio}
                }
        );

        var uvMatrix = new SimpleMatrix(
                new double[][]{
                        new double[]{posU},
                        new double[]{posV}
                }
        );

        uvMatrix = uvMatrix.plus(CENTER_TRANSLATION);
        uvMatrix = rotation.mult(uvMatrix);
        uvMatrix = scale.mult(uvMatrix);
        uvMatrix = uvMatrix.minus(CENTER_TRANSLATION);

        posU = uvMatrix.get(0);
        posV = uvMatrix.get(1);

        this.posU = icon.getInterpolatedU(posU * ICON_SCALE);
        this.posV = icon.getInterpolatedV(posV * ICON_SCALE);
    }

    private static double clamp(double x) {
        return Math.max(0.0D, Math.min(x, 1.0D));
    }
}
