package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import lombok.experimental.UtilityClass;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

@UtilityClass
public class RenderMessAround {
    private static final double renderMinX = 0.0D;
    private static final double renderMinY = 0.0D;
    private static final double renderMinZ = 0.0D;
    private static final double renderMaxX = 1.0D;
    private static final double renderMaxY = 1.0D;
    private static final double renderMaxZ = 1.0D;

    public void renderFaceYPos(Block block, double posX, double posY, double posZ, IIcon icon) {
        double quadMinX = posX + renderMinX;
        double quadMaxX = posX + renderMaxX;
        double quadMaxY = posY + renderMaxY;
        double quadMinZ = posZ + renderMinZ;
        double quadMaxZ = posZ + renderMaxZ;

        val uvPosBottomLeft = new PosUV(icon, renderMaxX, renderMaxZ, 0);
        val uvPosBottomRight = new PosUV(icon, renderMaxX, renderMinZ, 0);
        val uvPosTopLeft = new PosUV(icon, renderMinX, renderMinZ, 0);
        val uvPosTopRight = new PosUV(icon, renderMinX, renderMaxZ, 0);

        Quad.quadBuilder()
                .bottomLeft(new Vertex(quadMaxX, quadMaxY, quadMaxZ, uvPosBottomLeft))
                .bottomRight(new Vertex(quadMaxX, quadMaxY, quadMinZ, uvPosBottomRight))
                .topLeft(new Vertex(quadMinX, quadMaxY, quadMinZ, uvPosTopLeft))
                .topRight(new Vertex(quadMinX, quadMaxY, quadMaxZ, uvPosTopRight))
                .tessellate();
    }
}
