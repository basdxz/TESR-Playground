package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import net.minecraft.util.IIcon;

@Accessors(fluent = true)
@Getter
public class UVPos {
    private static final double UV_INTERPOLATION_RATIO = 16.0D;
    private static final double RENDER_U_MIN_BOUND = 0.0D;
    private static final double RENDER_U_MAX_BOUND = 1.0D;
    private static final double RENDER_V_MIN_BOUND = 0.0D;
    private static final double RENDER_V_MAX_BOUND = 1.0D;

    private final double minU;
    private final double maxU;
    private final double minV;
    private final double maxV;

    @Builder
    public UVPos(@NonNull IIcon texture, double renderMinU, double renderMaxU, double renderMinV, double renderMaxV) {
        minU = texture.getInterpolatedU(Math.max(renderMinU, RENDER_U_MIN_BOUND) * UV_INTERPOLATION_RATIO);
        maxU = texture.getInterpolatedU(Math.min(renderMaxU, RENDER_U_MAX_BOUND) * UV_INTERPOLATION_RATIO);
        minV = texture.getInterpolatedV(Math.max(renderMinV, RENDER_V_MIN_BOUND) * UV_INTERPOLATION_RATIO);
        maxV = texture.getInterpolatedV(Math.min(renderMaxV, RENDER_V_MAX_BOUND) * UV_INTERPOLATION_RATIO);
    }
}
