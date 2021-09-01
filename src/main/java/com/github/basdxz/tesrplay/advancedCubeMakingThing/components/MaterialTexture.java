package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendEquations;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendFuncs;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.defs.GLBlendEquation;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.defs.GLBlendFunc;
import lombok.Builder;
import net.minecraft.util.IIcon;

public class MaterialTexture extends IIconContainer implements BlendableIcon {
    private final boolean hasAlpha;
    private final GLBlendEquation glBlendEquation;
    private final GLBlendFunc glBlendFunc;

    @Builder
    protected MaterialTexture(IIcon icon, boolean hasAlpha, GLBlendEquation glBlendEquation, GLBlendFunc glBlendFunc) {
        super(icon);
        this.hasAlpha = hasAlpha;
        this.glBlendEquation = getGlBlendEquationOrDefault(glBlendEquation);
        this.glBlendFunc = getGlBlendEquationOrDefault(glBlendFunc);
    }

    private GLBlendEquation getGlBlendEquationOrDefault(GLBlendEquation glBlendEquation) {
        if (glBlendEquation == null) return GLBlendEquations.getDefault();
        return glBlendEquation;
    }

    private GLBlendFunc getGlBlendEquationOrDefault(GLBlendFunc glBlendFunc) {
        if (glBlendFunc == null) return GLBlendFuncs.getDefault();
        return glBlendFunc;
    }

    @Override
    public void applyBlending() {
        glBlendEquation.apply();
        glBlendFunc.apply();
    }
}
