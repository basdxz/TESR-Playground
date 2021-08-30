package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import lombok.experimental.UtilityClass;
import lombok.val;
import net.minecraft.util.IIcon;

@UtilityClass
public class RenderMessAround {
    private static final double renderMinX = 0.0D;
    private static final double renderMinY = 0.0D;
    private static final double renderMinZ = 0.0D;
    private static final double renderMaxX = 1.0D;
    private static final double renderMaxY = 1.0D;
    private static final double renderMaxZ = 1.0D;

    public void renderFaceYPos(IIcon icon, double posX, double posY, double posZ) {
        val quadMinX = posX + renderMinX;
        val quadMaxX = posX + renderMaxX;
        val quadMaxY = posY + renderMaxY;
        val quadMinZ = posZ + renderMinZ;
        val quadMaxZ = posZ + renderMaxZ;

        Quad.quadBuilder()
                .bottomLeft(new Vertex(quadMaxX, quadMaxY, quadMaxZ,
                        PosUV.builder().icon(icon).posU(renderMaxX).posV(renderMaxZ).build()))
                .bottomRight(new Vertex(quadMaxX, quadMaxY, quadMinZ,
                        PosUV.builder().icon(icon).posU(renderMaxX).posV(renderMinZ).build()))
                .topLeft(new Vertex(quadMinX, quadMaxY, quadMinZ,
                        PosUV.builder().icon(icon).posU(renderMinX).posV(renderMinZ).build()))
                .topRight(new Vertex(quadMinX, quadMaxY, quadMaxZ,
                        PosUV.builder().icon(icon).posU(renderMinX).posV(renderMaxZ).build()))
                .tessellate();
    }
}
