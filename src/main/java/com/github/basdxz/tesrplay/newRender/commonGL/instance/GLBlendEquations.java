package com.github.basdxz.tesrplay.newRender.commonGL.instance;

import com.github.basdxz.tesrplay.newRender.commonGL.defenition.GLBlendEquation;

import static org.lwjgl.opengl.GL14.*;

public enum GLBlendEquations implements GLBlendEquation {
    ADD(GL_FUNC_ADD),
    SUBTRACT(GL_FUNC_SUBTRACT),
    REVERSE_SUBTRACT(GL_FUNC_REVERSE_SUBTRACT),
    MIN(GL_MIN),
    MAX(GL_MAX);

    private final int mode;

    GLBlendEquations(int mode) {
        this.mode = mode;
    }

    @Override
    public void apply() {
        glBlendEquation(mode);
    }

    public static void applyDefault() {
        getDefault().apply();
    }

    public static GLBlendEquation getDefault() {
        return ADD;
    }
}
