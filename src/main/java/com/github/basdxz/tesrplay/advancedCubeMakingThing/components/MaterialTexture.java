package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

//TODO: Blending should be provided as a Runnable
@Accessors(fluent = true)
@Getter
@SuperBuilder
public class MaterialTexture extends IIconContainer implements BlendableIcon {
    private final Runnable blendingFunction;
    private final boolean hasAlpha;
    private final boolean doStretch;
    private final boolean skipScale;
    private final double rotation;
    @NonNull
    private final ColorRGBA colorRGBA;
    private final boolean flatTint;

    @Override
    public void applyBlending() {
        if (noDraw()) return;
        blendingFunction.run();
    }

    @Override
    public boolean noDraw() {
        return blendingFunction == null;
    }
}
