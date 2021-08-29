package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Quad {
    @Builder(builderClassName = "QuadBuilder", builderMethodName = "quadBuilder", buildMethodName = "render")
    public static void render(@NonNull Vertex bottomLeft, @NonNull Vertex bottomRight, @NonNull Vertex topLeft, @NonNull Vertex topRight) {
        bottomLeft.addToTesselator();
        bottomRight.addToTesselator();
        topLeft.addToTesselator();
        topRight.addToTesselator();
    }
}
