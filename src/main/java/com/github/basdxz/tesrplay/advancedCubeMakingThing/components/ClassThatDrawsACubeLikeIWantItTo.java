package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.Quad;
import lombok.experimental.UtilityClass;
import lombok.val;
import lombok.var;
import net.minecraftforge.common.util.ForgeDirection;

@UtilityClass
public class ClassThatDrawsACubeLikeIWantItTo {
    public void makeCube(PosXYZ posXYZ, CuboidBounds bounds, LayeredSidedBlendableIcon shading) {
        for (ForgeDirection validDirection : ForgeDirection.VALID_DIRECTIONS)
            drawSide(posXYZ, bounds, shading.getBlendableIconLayers(validDirection), validDirection);
    }

    //TODO: I hate this. It is very static and doesn't define the blocks in an expressive way.
    private void drawSide(PosXYZ posXYZ, CuboidBounds bounds, Iterable<BlendableIcon> layers, ForgeDirection side) {
        PosXYZ vertAPos;
        PosXYZ vertBPos;
        PosXYZ vertCPos;
        PosXYZ vertDPos;
        PosUV vertAUV;
        PosUV vertBUV;
        PosUV vertCUV;
        PosUV vertDUV;
        var isFlipped = false;
        val squeezeUV = false;

        switch (side) {
            case DOWN:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
                if (squeezeUV) {
                    vertAUV = new PosUV(0, 0);
                    vertBUV = new PosUV(1, 0);
                    vertCUV = new PosUV(1, 1);
                    vertDUV = new PosUV(0, 1);
                } else {
                    vertAUV = new PosUV(bounds.min().x(), bounds.min().z());
                    vertBUV = new PosUV(bounds.max().x(), bounds.min().z());
                    vertCUV = new PosUV(bounds.max().x(), bounds.max().z());
                    vertDUV = new PosUV(bounds.min().x(), bounds.max().z());
                }
                break;
            case UP:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.max().z());
                if (squeezeUV) {
                    vertAUV = new PosUV(0, 0);
                    vertBUV = new PosUV(1, 0);
                    vertCUV = new PosUV(1, 1);
                    vertDUV = new PosUV(0, 1);
                } else {
                    vertAUV = new PosUV(bounds.min().x(), bounds.min().z());
                    vertBUV = new PosUV(bounds.max().x(), bounds.min().z());
                    vertCUV = new PosUV(bounds.max().x(), bounds.max().z());
                    vertDUV = new PosUV(bounds.min().x(), bounds.max().z());
                }
                isFlipped = true;
                break;
            case NORTH:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
                if (squeezeUV) {
                    vertAUV = new PosUV(1, 0);
                    vertBUV = new PosUV(0, 0);
                    vertCUV = new PosUV(0, 1);
                    vertDUV = new PosUV(1, 1);
                } else {
                    vertAUV = new PosUV(1 - bounds.min().x(), 1 - bounds.max().y());
                    vertBUV = new PosUV(1 - bounds.max().x(), 1 - bounds.max().y());
                    vertCUV = new PosUV(1 - bounds.max().x(), 1 - bounds.min().y());
                    vertDUV = new PosUV(1 - bounds.min().x(), 1 - bounds.min().y());
                }
                break;
            case SOUTH:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.max().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
                if (squeezeUV) {
                    vertAUV = new PosUV(0, 0);
                    vertBUV = new PosUV(1, 0);
                    vertCUV = new PosUV(1, 1);
                    vertDUV = new PosUV(0, 1);
                } else {
                    vertAUV = new PosUV(bounds.min().x(), 1 - bounds.max().y());
                    vertBUV = new PosUV(bounds.max().x(), 1 - bounds.max().y());
                    vertCUV = new PosUV(bounds.max().x(), 1 - bounds.min().y());
                    vertDUV = new PosUV(bounds.min().x(), 1 - bounds.min().y());
                }
                isFlipped = true;
                break;
            case WEST:
                vertAPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
                if (squeezeUV) {
                    vertAUV = new PosUV(1, 0);
                    vertBUV = new PosUV(0, 0);
                    vertCUV = new PosUV(0, 1);
                    vertDUV = new PosUV(1, 1);
                } else {
                    vertCUV = new PosUV(1 - bounds.max().z(), 1 - bounds.min().y());
                    vertDUV = new PosUV(1 - bounds.min().z(), 1 - bounds.min().y());
                    vertAUV = new PosUV(1 - bounds.min().z(), 1 - bounds.max().y());
                    vertBUV = new PosUV(1 - bounds.max().z(), 1 - bounds.max().y());
                }
                break;
            case EAST:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.max().z());
                vertCPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
                if (squeezeUV) {
                    vertAUV = new PosUV(0, 0);
                    vertBUV = new PosUV(1, 0);
                    vertCUV = new PosUV(1, 1);
                    vertDUV = new PosUV(0, 1);
                } else {
                    vertDUV = new PosUV(bounds.min().z(), 1 - bounds.min().y());
                    vertCUV = new PosUV(bounds.max().z(), 1 - bounds.min().y());
                    vertBUV = new PosUV(bounds.max().z(), 1 - bounds.max().y());
                    vertAUV = new PosUV(bounds.min().z(), 1 - bounds.max().y());
                }
                isFlipped = true;
                break;
            default:
                return;
        }

        //fixme make this Manifold
        vertAPos = vertAPos.add(posXYZ);
        vertBPos = vertBPos.add(posXYZ);
        vertCPos = vertCPos.add(posXYZ);
        vertDPos = vertDPos.add(posXYZ);

        for (BlendableIcon layer : layers) {
            layer.applyBlending();
            Quad.quadBuilder()
                    .vertA(new Vertex(vertAPos, PosUV.builder().icon(layer).posUV(vertAUV).build()))
                    .vertB(new Vertex(vertBPos, PosUV.builder().icon(layer).posUV(vertBUV).build()))
                    .vertC(new Vertex(vertCPos, PosUV.builder().icon(layer).posUV(vertCUV).build()))
                    .vertD(new Vertex(vertDPos, PosUV.builder().icon(layer).posUV(vertDUV).build()))
                    .reversed(isFlipped)
                    .tessellate();
        }
    }
}
