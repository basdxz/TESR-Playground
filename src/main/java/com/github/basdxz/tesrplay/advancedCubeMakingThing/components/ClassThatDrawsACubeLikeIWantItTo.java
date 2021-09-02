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
        PosXYZ vertAPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
        PosXYZ vertBPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
        PosXYZ vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
        PosXYZ vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
        boolean isFlipped = false;
        boolean doIRender = false;

        double vertAU = 0;
        double vertBU = 0;
        double vertCU = 0;
        double vertDU = 0;

        double vertAV = 0;
        double vertBV = 0;
        double vertCV = 0;
        double vertDV = 0;

        //fixme add a scary default that crashes the game lmao
        switch(side) {
            case DOWN:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
                vertAU = 0;
                vertAV = 0;
                vertBPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
                vertBU = 1;
                vertBV = 0;
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
                vertCU = 1;
                vertCV = 1;
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
                vertDU = 1;
                vertDV = 0;
                doIRender = true;
                break;
            case UP:
                return;
            case NORTH:
                vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
                vertAU = 1;
                vertAV = 0;
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
                vertBU = 0;
                vertBV = 0;
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
                vertCU = 0;
                vertCV = 1;
                vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
                vertDU = 1;
                vertDV = 1;
                doIRender = true;
                break;
            case SOUTH:
                return;
            case WEST:
                vertAPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
                vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
                vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
                vertDPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
                //doIRender = true;
                break;
            case EAST:
                return;
        }

        if (!doIRender) return;

        for (BlendableIcon layer : layers) {
            layer.applyBlending();
            Quad.quadBuilder()
                    .vertA(new Vertex(vertAPos.x(), vertAPos.y(), vertAPos.z(),
                            PosUV.builder().icon(layer).posU(vertAU).posV(vertAV).build()))
                    .vertB(new Vertex(vertBPos.x(), vertBPos.y(), vertBPos.z(),
                            PosUV.builder().icon(layer).posU(vertBU).posV(vertBV).build()))
                    .vertC(new Vertex(vertCPos.x(), vertCPos.y(), vertCPos.z(),
                            PosUV.builder().icon(layer).posU(vertCU).posV(vertCV).build()))
                    .vertD(new Vertex(vertDPos.x(), vertDPos.y(), vertDPos.z(),
                            PosUV.builder().icon(layer).posU(vertDU).posV(vertDV).build()))
                    .reversed(isFlipped)
                    .tessellate();
        }
    }
}
