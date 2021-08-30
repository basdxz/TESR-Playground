package com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp;

import static org.lwjgl.opengl.GL11.*;

public enum BlendFunctions implements Runnable {
    DEFAULT(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA),
    ALPHA(GL_ONE, GL_SRC_ALPHA),
    PREALPHA(GL_ONE, GL_ONE_MINUS_SRC_ALPHA),
    MULTIPLY(GL_DST_COLOR, GL_ONE_MINUS_SRC_ALPHA),
    ADDITIVE(GL_ONE, GL_ONE),
    ADDITIVEDARK(GL_ONE, GL_ONE_MINUS_SRC_COLOR),
    OVERLAYDARK(GL_SRC_COLOR, GL_ONE),
    ADDITIVE2(GL_SRC_ALPHA, GL_ONE),
    INVERTEDADD(GL_ONE_MINUS_DST_COLOR, GL_ONE_MINUS_SRC_COLOR),// Could be used to make multiblock lcd

    LENS_TRY_THIS(GL_ONE_MINUS_SRC_COLOR, GL_ONE);

    public final int sourceFactor;
    public final int destinationFactor;

    BlendFunctions(int sourceFactor, int destinationFactor) {
        this.sourceFactor = sourceFactor;
        this.destinationFactor = destinationFactor;
    }

    @Override
    public void run() {
        glBlendFunc(sourceFactor, destinationFactor);
    }
}
