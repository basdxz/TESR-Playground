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

        val uvPos = UVPos.builder()
                .texture(icon)
                .renderMinU(renderMinX)
                .renderMaxU(renderMaxX)
                .renderMinV(renderMinZ)
                .renderMaxV(renderMaxZ)
                .build();

        Quad.quadBuilder()
                .bottomLeft(new Vertex(quadMaxX, quadMaxY, quadMaxZ, uvPos.maxU(), uvPos.maxV()))
                .bottomRight(new Vertex(quadMaxX, quadMaxY, quadMinZ, uvPos.maxU(), uvPos.minV()))
                .topLeft(new Vertex(quadMinX, quadMaxY, quadMinZ, uvPos.minU(), uvPos.minV()))
                .topRight(new Vertex(quadMinX, quadMaxY, quadMaxZ, uvPos.minU(), uvPos.maxV()))
                .render();
    }
}
