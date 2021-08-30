package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.BlendFunctions;
import com.github.basdxz.tesrplay.newRender.TestBlock;
import lombok.experimental.UtilityClass;
import lombok.val;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL14.glBlendEquation;

@UtilityClass
public class RenderMessAround {
    private static final double renderMinX = 0.0D;
    private static final double renderMinY = 0.0D;
    private static final double renderMinZ = 0.0D;
    private static final double renderMaxX = 1.0D;
    private static final double renderMaxY = 1.0D;
    private static final double renderMaxZ = 1.0D;

    public void renderFaceYPos(IIcon icon, double posX, double posY, double posZ) {
        icon = TestBlock.BOTTOM_GLASS;
        glDisable(GL_ALPHA_TEST);
        glEnable(GL_BLEND);

        glBlendEquation(GL_MIN);
        glBlendFunc(GL_ONE, GL_ONE);

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

        //Tessellator.instance.draw();
        //Tessellator.instance.startDrawingQuads();

        //glBlendEquation(GL_FUNC_ADD);
        //glEnable(GL_ALPHA_TEST);
        //glDisable(GL_BLEND);
    }

    //Almost lensing, but does not delete the light I don't like.
    private void blendModeOne() {
        //1: Turn on the blending
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        //2: Use glColor4f() to set the Alpha component
        glColor4f(0F, 0F, 1.0F, 0.501F);
    }

    //Kinda looks like it would be awesome for special gemstones
    private void blendModeTwo() {
        //1: Turn on the blending
        glBlendFunc(GL_DST_COLOR, GL_SRC_COLOR);
        //2: Use glColor4f() to set the Alpha component
        glColor4f(0F, 0F, 1.0F, 0.501F);
    }

    //Same as mode one, is it not blending because my source-dest alpha is zero? since color is set
    //by glcolor and not a transclucent texture? I'll try that next
    private void blendModeThree() {
        //1: Turn on the blending
        glBlendFunc(GL_SRC_ALPHA, GL_SRC_ALPHA);
        //2: Use glColor4f() to set the Alpha component
        glColor4f(0F, 0F, 1.0F, 0.501F);
    }
}
