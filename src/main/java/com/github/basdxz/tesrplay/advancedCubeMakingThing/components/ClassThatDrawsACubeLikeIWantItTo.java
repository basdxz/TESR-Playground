package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.Quad;
import lombok.experimental.UtilityClass;
import lombok.var;
import net.minecraftforge.common.util.ForgeDirection;

@UtilityClass
public class ClassThatDrawsACubeLikeIWantItTo {
    public void makeCube(CuboidBounds bounds, LayeredSidedBlendableIcon shading) {
        for (ForgeDirection validDirection : ForgeDirection.VALID_DIRECTIONS)
            drawSide(bounds, shading.getBlendableIconLayers(validDirection), validDirection);
    }

    private void drawSide(CuboidBounds bounds, Iterable<BlendableIcon> layers, ForgeDirection side) {
        var vertMinX = getVertMinX(bounds, side);
        var vertMaxX = getVertMaxX(bounds, side);
        var vertMinY = getVertMinY(bounds, side);
        var vertMaxY = getVertMaxY(bounds, side);
        var vertMinZ = getVertMinZ(bounds, side);
        var vertMaxZ = getVertMaxZ(bounds, side);
        var isReversed = false;

        for (BlendableIcon layer : layers) {
            layer.applyBlending();
        }
    }

    private double getVertMinX(CuboidBounds bounds, ForgeDirection side) {
        if (side.offsetX == 1)
            return bounds.max().x();
        return bounds.min().x();
    }

    private double getVertMaxX(CuboidBounds bounds, ForgeDirection side) {
        if (side.offsetX == -1)
            return bounds.min().x();
        return bounds.max().x();
    }

    private double getVertMinY(CuboidBounds bounds, ForgeDirection side) {
        if (side.offsetY == 1)
            return bounds.max().y();
        return bounds.min().y();
    }

    private double getVertMaxY(CuboidBounds bounds, ForgeDirection side) {
        if (side.offsetY == -1)
            return bounds.min().y();
        return bounds.max().y();
    }

    private double getVertMinZ(CuboidBounds bounds, ForgeDirection side) {
        if (side.offsetZ == 1)
            return bounds.max().z();
        return bounds.min().z();
    }

    private double getVertMaxZ(CuboidBounds bounds, ForgeDirection side) {
        if (side.offsetZ == -1)
            return bounds.min().z();
        return bounds.max().z();
    }
}
