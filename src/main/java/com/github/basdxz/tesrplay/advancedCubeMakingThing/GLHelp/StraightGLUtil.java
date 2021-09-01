package com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendEquations;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendFuncs;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLToggles;
import lombok.experimental.UtilityClass;
import net.minecraft.client.renderer.Tessellator;

import static org.lwjgl.opengl.GL11.*;

@UtilityClass
public class StraightGLUtil {
    public void drawAndSettingWrapper(Runnable runnable, boolean isAlphaPass) {
        drawAndUnDraw();
        runnable.run();
        drawAndUnDraw();
        restoreDefaults(isAlphaPass);
    }

    public void drawAndUnDraw() {
        Tessellator.instance.draw();
        Tessellator.instance.startDrawingQuads();
    }

    public void restoreDefaults(boolean isAlphaPass) {
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
