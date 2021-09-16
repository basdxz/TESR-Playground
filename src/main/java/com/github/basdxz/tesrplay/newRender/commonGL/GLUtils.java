package com.github.basdxz.tesrplay.newRender.commonGL;

import com.github.basdxz.tesrplay.newRender.commonGL.instance.GLBlendEquations;
import com.github.basdxz.tesrplay.newRender.commonGL.instance.GLBlendFuncs;
import com.github.basdxz.tesrplay.newRender.commonGL.instance.GLToggles;
import com.github.basdxz.tesrplay.newRender.cuboid.BlendableIcon;
import com.github.basdxz.tesrplay.newRender.cuboid.PosXYZ;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

import static com.github.basdxz.tesrplay.Reference.*;

//TODO Rename something more sensible and move this class
//TODO Clean up the GL helper packages as a whole
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GLUtils {
    public final static PosXYZ BLOCK_CENTER_OFFSET = new PosXYZ(0.5D, 0.5D, 0.5D);

    public static void blendAndTessellate(BlendableIcon layer, Runnable tesselation, boolean renderingAsItem) {
        val noDraw = layer.noDraw() && !renderingAsItem;
        if (noDraw) {
            tesselation.run();
            return;
        }

        Tessellator.instance.pauseDraw();
        Tessellator.instance.startDrawingQuads();
        layer.applyBlending();
        tesselation.run();
        Tessellator.instance.draw();
        Tessellator.instance.resumeDraw();

        resetGL(layer.renderPass() == 1);
    }

    private static void resetGL(boolean isAlphaPass) {
        // Blending
        GLBlendFuncs.applyDefault();
        GLBlendEquations.applyDefault();

        // Transparency
        GLToggles.ALPHA_TEST.enable();
        if (isAlphaPass) {
            GLToggles.BLEND.enable();
        } else {
            GLToggles.BLEND.disable();
        }

        // Extras
       //GLToggles.LIGHTING.enable();
       //GLToggles.CULL_FACE.enable();
       //GL11.glFrontFace(GL11.GL_CCW);
    }

    //TODO get blending and alpha mode clean up
    //TODO make safe render mode-depentent, knowing when it doesn't need pause/resume or push/pop etc
    public static void safeRender(Runnable render) {
        Tessellator.instance.pauseDraw();
        GL11.glPushMatrix();
        render.run();
        GL11.glPopMatrix();
        Tessellator.instance.resumeDraw();
    }

    public static void glColor(double... color) {
        double red;
        double green;
        double blue;
        double alpha;

        switch (color.length) {
            case 0:
                red = 1.0D;
                green = 1.0D;
                blue = 1.0D;
                alpha = 1.0D;
                break;
            case 4:
                red = color[RED];
                green = color[GREEN];
                blue = color[BLUE];
                alpha = color[ALPHA];
                break;
            default:
                throw new IllegalArgumentException("Method expects array length 0 or 4.");
        }
        GL11.glColor4d(red, green, blue, alpha);
    }

    public static void glTranslate(double... offset) {
        double offsetX;
        double offsetY;
        double offsetZ;
        switch (offset.length) {
            case 1:
                offsetX = offset[0];
                offsetY = offset[0];
                offsetZ = offset[0];
                break;
            case 3:
                offsetX = offset[X];
                offsetY = offset[Y];
                offsetZ = offset[Z];
                break;
            default:
                throw new IllegalArgumentException("Method expects array length 1 or 3.");
        }
        GL11.glTranslated(offsetX, offsetY, offsetZ);
    }

    public static void glXRotate(double rotX) {
        glXRotate(rotX, BLOCK_CENTER_OFFSET.y(), BLOCK_CENTER_OFFSET.z());
    }

    public static void glXRotate(double rotX, double centerY, double centerZ) {
        GL11.glTranslated(0.0F, centerY, centerZ);
        GL11.glRotated(rotX, 1.0F, 0.0F, 0.0F);
        GL11.glTranslated(0.0F, -centerY, -centerZ);
    }

    public static void glYRotate(double rotY) {
        glYRotate(rotY, BLOCK_CENTER_OFFSET.x(), BLOCK_CENTER_OFFSET.z());
    }

    public static void glYRotate(double rotY, double centerX, double centerZ) {
        GL11.glTranslated(centerX, 0.0F, centerZ);
        GL11.glRotated(rotY, 0.0F, 1.0F, 0.0F);
        GL11.glTranslated(-centerX, 0.0F, -centerZ);
    }

    public static void glZRotate(double rotZ) {
        glZRotate(rotZ, BLOCK_CENTER_OFFSET.x(), BLOCK_CENTER_OFFSET.y());
    }

    public static void glZRotate(double rotZ, double centerX, double centerY) {
        GL11.glTranslated(centerX, centerY, 0.0D);
        GL11.glRotated(rotZ, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(-centerX, -centerY, 0.0D);
    }

    public static void glScale(double... scale) {
        double scaleX;
        double scaleY;
        double scaleZ;
        switch (scale.length) {
            case 1:
                scaleX = scale[0];
                scaleY = scale[0];
                scaleZ = scale[0];
                break;
            case 3:
                scaleX = scale[X];
                scaleY = scale[Y];
                scaleZ = scale[Z];
                break;
            default:
                throw new IllegalArgumentException("Method expects array length 1 or 3.");
        }
        GL11.glScaled(scaleX, scaleY, scaleZ);
    }
}
