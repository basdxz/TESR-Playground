package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import lombok.experimental.UtilityClass;
import lombok.val;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

@UtilityClass
public class RenderMessAround {
    private static final double renderMinX = 0.0D;
    private static final double renderMinY = 0.0D;
    private static final double renderMinZ = 0.0D;
    private static final double renderMaxX = 1.0D;
    private static final double renderMaxY = 1.0D;
    private static final double renderMaxZ = 1.0D;

    public void renderFaceYPos(IIcon icon, double posX, double posY, double posZ) {
        Tessellator.instance.pauseDraw();
        Tessellator.instance.startDrawingQuads();

        //1: Turn on the blending
        glEnable(GL_BLEND);
        //glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glBlendFunc(GL_DST_COLOR, GL_ONE_MINUS_SRC_COLOR);
        //2: Use glColor4f() to set the Alpha component
        //glColor4f(0F, 0F, 1.0F, 0.501F);

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

        Tessellator.instance.draw();
        glDisable(GL_BLEND);
        Tessellator.instance.resumeDraw();
    }
}
