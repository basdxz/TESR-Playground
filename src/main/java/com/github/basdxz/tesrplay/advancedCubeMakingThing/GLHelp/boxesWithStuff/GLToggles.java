package com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.defs.GLToggle;

import static org.lwjgl.opengl.GL11.*;

public enum GLToggles implements GLToggle {
    ALPHA_TEST(GL_ALPHA_TEST),
    BLEND(GL_BLEND),
    CULL_FACE(GL_CULL_FACE),
    LIGHTING(GL_LIGHTING);

    public final int cap;

    GLToggles(int cap) {
        this.cap = cap;
    }

    @Override
    public void apply() {
        toggle();
    }

    public void toggle() {
        if (isEnabled()) {
            disable();
        } else {
            enable();
        }
    }

    public boolean isEnabled() {
        return glGetBoolean(cap);
    }

    public void enable() {
        glEnable(cap);
    }

    public void disable() {
        glDisable(cap);
    }
}
