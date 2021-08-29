package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import lombok.Value;
import lombok.experimental.Accessors;
import lombok.val;
import lombok.var;
import net.minecraft.util.IIcon;
import org.ejml.simple.SimpleMatrix;

import static java.lang.Math.toRadians;

@Value
@Accessors(fluent = true)
public class PosUV {
    private static final double ICON_SCALE = 16.0D;

    double posU;
    double posV;

    public PosUV(IIcon icon, double posU, double posV, double rot) {
        posU = clamp(posU);
        posV = clamp(posV);
        rot = toRadians(45);//fixme switch to use actual rot

        val translation = -0.5D;
        val rotation = new SimpleMatrix(
                new double[][]{
                        new double[]{Math.cos(rot), -Math.sin(rot)},
                        new double[]{Math.sin(rot), Math.cos(rot)}
                }
        );

        var uvMatrix = new SimpleMatrix(
                new double[][]{
                        new double[]{posU},
                        new double[]{posV}
                }
        );

        uvMatrix = uvMatrix.plus(translation);
        uvMatrix = rotation.mult(uvMatrix);
        uvMatrix = uvMatrix.minus(translation);

        posU = uvMatrix.get(0);
        posV = uvMatrix.get(1);

        this.posU = icon.getInterpolatedU(posU * ICON_SCALE);
        this.posV = icon.getInterpolatedV(posV * ICON_SCALE);
    }

    private static double clamp(double x) {
        return Math.max(0.0D, Math.min(x, 1.0D));
    }
}
