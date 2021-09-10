package com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.PosXYZ;
import lombok.experimental.UtilityClass;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

import static com.github.basdxz.tesrplay.TESRPlayground.*;

//TODO Rename something more sensible and move this class
//TODO Clean up the GL helper packages as a whole
@UtilityClass
public class StraightGLUtil {
    public final PosXYZ BLOCK_CENTER_OFFSET = new PosXYZ(0.5D,0.5D,0.5D);

    //public void drawAndSettingWrapper(Runnable runnable, boolean noDraw, boolean isAlphaPass) {
    //    drawAndUnDraw(noDraw);
    //    runnable.run();
    //    drawAndUnDraw(noDraw);
    //    restoreDefaults(noDraw, isAlphaPass);
    //}
//
    //public void drawAndUnDraw(boolean noDraw) {
    //    if (noDraw) return;
    //    Tessellator.instance.draw();
    //    Tessellator.instance.startDrawingQuads();
//
    //    Tessellator.instance.setBrightness(0);
    //    Tessellator.instance.setColorRGBA_F(1,1,1,1);
    //}
//
    //public void restoreDefaults(boolean noDraw, boolean isAlphaPass) {
    //    if (noDraw) return;
    //    if (isAlphaPass) {
    //        GLToggles.ALPHA_TEST.disable();
    //        GLToggles.BLEND.enable();
    //    } else {
    //        GLToggles.ALPHA_TEST.enable();
    //        GLToggles.BLEND.disable();
    //    }
    //    GLToggles.LIGHTING.enable();
    //    GLToggles.CULL_FACE.enable();
//
    //    GLBlendFuncs.applyDefault();
    //    GLBlendEquations.applyDefault();
//
    //    glFrontFace(GL_CCW);
    //}

    //TODO get blending and alpha mode clean up
    //TODO make safe render mode-depentent, knowing when it doesn't need pause/resume or push/pop etc
    public void safeRender(Runnable render) {
        Tessellator.instance.pauseDraw();
        GL11.glPushMatrix();
        render.run();
        GL11.glPopMatrix();
        Tessellator.instance.resumeDraw();
    }

    public void glColor(double... color) {
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

    public void glTranslate(double... offset) {
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

    public void glXRotate(double rotX) {
        glXRotate(rotX, BLOCK_CENTER_OFFSET.y(), BLOCK_CENTER_OFFSET.z());
    }

    public void glXRotate(double rotX, double centerY, double centerZ) {
        GL11.glTranslated(0.0F, centerY, centerZ);
        GL11.glRotated(rotX, 1.0F, 0.0F, 0.0F);
        GL11.glTranslated(0.0F, -centerY, -centerZ);
    }

    public void glYRotate(double rotY) {
        glYRotate(rotY, BLOCK_CENTER_OFFSET.x(), BLOCK_CENTER_OFFSET.z());
    }

    public void glYRotate(double rotY, double centerX, double centerZ) {
        GL11.glTranslated(centerX, 0.0F, centerZ);
        GL11.glRotated(rotY, 0.0F, 1.0F, 0.0F);
        GL11.glTranslated(-centerX, 0.0F, -centerZ);
    }

    public void glZRotate(double rotZ) {
        glZRotate(rotZ, BLOCK_CENTER_OFFSET.x(), BLOCK_CENTER_OFFSET.y());
    }

    public void glZRotate(double rotZ, double centerX, double centerY) {
        GL11.glTranslated(centerX, centerY, 0.0D);
        GL11.glRotated(rotZ, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(-centerX, -centerY, 0.0D);
    }

    public void glScale(double... scale) {
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
