package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.StraightGLUtil;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.PosUV;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.Vertex;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.BlendableIcon;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class RenderMessAround {
    private static final double renderMinX = 0.0D;
    private static final double renderMinY = 0.0D;
    private static final double renderMinZ = 0.0D;
    private static final double renderMaxX = 1.0D;
    private static final double renderMaxY = 1.0D;
    private static final double renderMaxZ = 1.0D;

    public void renderFaceYPos(BlendableIcon icon, double posX, double posY, double posZ) {
        StraightGLUtil.drawAndSettingWrapper(() -> {
            icon.applyBlending();

            val quadMinX = posX + renderMinX;
            val quadMaxX = posX + renderMaxX;
            val quadMinY = posY + renderMinY;
            val quadMaxY = posY + renderMaxY;
            val quadMinZ = posZ + renderMinZ;
            val quadMaxZ = posZ + renderMaxZ;

            top(icon, quadMinX, quadMaxX, quadMaxY, quadMinZ, quadMaxZ);
            bottom(icon, quadMinX, quadMaxX, quadMinY, quadMinZ, quadMaxZ);


        }, true);

    }

    private void top(BlendableIcon icon, double quadMinX, double quadMaxX, double quadMaxY, double quadMinZ, double quadMaxZ) {
        Quad.quadBuilder()
                .vertA(new Vertex(quadMinX, quadMaxY, quadMaxZ,
                        PosUV.builder().icon(icon).posU(renderMaxX).posV(renderMaxZ).build()))
                .vertB(new Vertex(quadMaxX, quadMaxY, quadMaxZ,
                        PosUV.builder().icon(icon).posU(renderMaxX).posV(renderMinZ).build()))
                .vertC(new Vertex(quadMaxX, quadMaxY, quadMinZ,
                        PosUV.builder().icon(icon).posU(renderMinX).posV(renderMinZ).build()))
                .vertD(new Vertex(quadMinX, quadMaxY, quadMinZ,
                        PosUV.builder().icon(icon).posU(renderMinX).posV(renderMaxZ).build()))
                .reversed(false)
                .tessellate();
    }

    private void bottom(BlendableIcon icon, double quadMinX, double quadMaxX, double quadMinY, double quadMinZ, double quadMaxZ) {
        Quad.quadBuilder()
                .vertA(new Vertex(quadMinX, quadMinY, quadMaxZ,
                        PosUV.builder().icon(icon).posU(renderMaxX).posV(renderMaxZ).build()))
                .vertB(new Vertex(quadMaxX, quadMinY, quadMaxZ,
                        PosUV.builder().icon(icon).posU(renderMaxX).posV(renderMinZ).build()))
                .vertC(new Vertex(quadMaxX, quadMinY, quadMinZ,
                        PosUV.builder().icon(icon).posU(renderMinX).posV(renderMinZ).build()))
                .vertD(new Vertex(quadMinX, quadMinY, quadMinZ,
                        PosUV.builder().icon(icon).posU(renderMinX).posV(renderMaxZ).build()))
                .reversed(true)
                .tessellate();
    }

}
