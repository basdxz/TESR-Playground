package com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendEquations;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendFuncs;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLToggles;
import lombok.experimental.UtilityClass;
import net.minecraft.client.renderer.Tessellator;

import static org.lwjgl.opengl.GL11.*;

@UtilityClass
public class StraightGLUtil {
    public void drawAndSettingWrapper(Runnable runnable, boolean noDraw, boolean isAlphaPass) {
        drawAndUnDraw(noDraw);
        runnable.run();
        drawAndUnDraw(noDraw);
        restoreDefaults(noDraw, isAlphaPass);
    }

    public void drawAndUnDraw(boolean noDraw) {
        if (noDraw) return;
        Tessellator.instance.draw();
        Tessellator.instance.startDrawingQuads();
    }

    public void restoreDefaults(boolean noDraw, boolean isAlphaPass) {
        if (noDraw) return;
        if (isAlphaPass) {
            GLToggles.ALPHA_TEST.disable();
            GLToggles.BLEND.enable();
        } else {
            GLToggles.ALPHA_TEST.enable();
            GLToggles.BLEND.disable();
        }
        GLToggles.LIGHTING.enable();
        GLToggles.CULL_FACE.enable();

        GLBlendFuncs.applyDefault();
        GLBlendEquations.applyDefault();

        glFrontFace(GL_CCW);
    }
}
