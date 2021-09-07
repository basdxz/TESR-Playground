package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.defs.GLBlendEquation;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.defs.GLBlendFunc;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

//TODO: Blending should be provided as a Runnable
@Accessors(fluent = true)
@SuperBuilder
public class MaterialTexture extends IIconContainer implements BlendableIcon {
    private final GLBlendEquation glBlendEquation;
    private final GLBlendFunc glBlendFunc;
    @Getter
    private final boolean hasAlpha;
    @Getter
    private final boolean noDraw;
    @Getter
    private final boolean doStretch;
    @Getter
    private final boolean skipScale;
    @Getter
    private final double rotation;
    @Getter
    @NonNull
    private final ColorRGBA colorRGBA;

    @Override
    public void applyBlending(boolean noDraw) {
        if (noDraw) return;
        if (glBlendEquation != null)
            glBlendEquation.apply();
        if (glBlendEquation != null)
            glBlendFunc.apply();
    }
}
