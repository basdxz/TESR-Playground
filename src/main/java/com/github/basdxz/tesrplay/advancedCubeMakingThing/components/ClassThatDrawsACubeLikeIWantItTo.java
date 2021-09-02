package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.Quad;
import lombok.experimental.UtilityClass;
import net.minecraftforge.common.util.ForgeDirection;

@UtilityClass
public class ClassThatDrawsACubeLikeIWantItTo {
    public void makeCube(CuboidBounds bounds, LayeredSidedBlendableIcon shading) {
        for (ForgeDirection validDirection : ForgeDirection.VALID_DIRECTIONS)
            drawSide(bounds, shading.getBlendableIconLayers(validDirection), validDirection);
    }

    private void drawSide(CuboidBounds bounds, Iterable<BlendableIcon> layers, ForgeDirection side) {
        PosXYZ vertAPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
        PosXYZ vertBPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
        PosXYZ vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
        PosXYZ vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
        boolean isFlipped = false;

        PosUV vertAUV = new PosUV(0, 0);
        PosUV vertBUV = new PosUV(0, 0);
        PosUV vertCUV = new PosUV(0, 0);
        PosUV vertDUV = new PosUV(0, 0);

        //fixme add a scary default that crashes the game lmao
        switch (side) {
            case DOWN:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
                vertAUV = new PosUV(0, 0);
                vertBUV = new PosUV(1, 0);
                vertCUV = new PosUV(1, 1);
                vertDUV = new PosUV(0, 1);
                break;
            case UP:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.max().z());
                vertCUV = new PosUV(1, 1);
                vertBUV = new PosUV(1, 0);
                vertAUV = new PosUV(0, 0);
                vertDUV = new PosUV(0, 1);
                isFlipped = true;
                break;
            case NORTH:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
                vertAUV = new PosUV(1, 0);
                vertBUV = new PosUV(0, 0);
                vertCUV = new PosUV(0, 1);
                vertDUV = new PosUV(1, 1);
                break;
            case SOUTH:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.max().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
                vertAUV = new PosUV(0, 0);
                vertBUV = new PosUV(1, 0);
                vertCUV = new PosUV(1, 1);
                vertDUV = new PosUV(0, 1);
                isFlipped = true;
                break;
            case WEST:
                vertAPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
                vertAUV = new PosUV(1, 0);
                vertBUV = new PosUV(0, 0);
                vertCUV = new PosUV(0, 1);
                vertDUV = new PosUV(1, 1);
                break;
            case EAST:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.max().z());
                vertCPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
                vertAUV = new PosUV(0, 0);
                vertBUV = new PosUV(1, 0);
                vertCUV = new PosUV(1, 1);
                vertDUV = new PosUV(0, 1);
                isFlipped = true;
                break;
        }

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
