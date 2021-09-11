package com.github.basdxz.tesrplay.newRender.commonGL.instance;

import com.github.basdxz.tesrplay.newRender.commonGL.defenition.GLBlendFunc;

import static org.lwjgl.opengl.GL11.*;

// Blending coefficients
public enum GLBlendFuncs implements GLBlendFunc {
    DEFAULT(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA),
    ALPHA(GL_ONE, GL_SRC_ALPHA),
    PRE_ALPHA(GL_ONE, GL_ONE_MINUS_SRC_ALPHA),
    MULTIPLY(GL_DST_COLOR, GL_ONE_MINUS_SRC_ALPHA),
    ADDITIVE(GL_ONE, GL_ONE),
    ADDITIVE_DARK(GL_ONE, GL_ONE_MINUS_SRC_COLOR),
    OVERLAY_DARK(GL_SRC_COLOR, GL_ONE),
    ADDITIVE2(GL_SRC_ALPHA, GL_ONE),
    INVERTED_ADD(GL_ONE_MINUS_DST_COLOR, GL_ONE_MINUS_SRC_COLOR),
    LENS_FILTER(GL_ONE_MINUS_SRC_COLOR, GL_DST_COLOR);

    public final int sourceFactor;
    public final int destinationFactor;

    GLBlendFuncs(int sourceFactor, int destinationFactor) {
        this.sourceFactor = sourceFactor;
        this.destinationFactor = destinationFactor;
    }

    @Override
    public void apply() {
        glBlendFunc(sourceFactor, destinationFactor);
    }

    public static void applyDefault() {
        getDefault().apply();
    }

    public static GLBlendFunc getDefault() {
        return DEFAULT;
    }
}
