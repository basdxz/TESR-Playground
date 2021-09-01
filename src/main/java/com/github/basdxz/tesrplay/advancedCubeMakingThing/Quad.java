package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.Vertex;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Quad {
    @Builder(builderClassName = "QuadBuilder", builderMethodName = "quadBuilder", buildMethodName = "tessellate")
    public static void render(@NonNull Vertex vertA,
                              @NonNull Vertex vertB,
                              @NonNull Vertex vertC,
                              @NonNull Vertex vertD,
                              boolean reversed) {
        if (reversed) {
            vertD.tessellate();
            vertC.tessellate();
            vertB.tessellate();
            vertA.tessellate();
        } else {
            vertA.tessellate();
            vertB.tessellate();
            vertC.tessellate();
            vertD.tessellate();
        }
    }
}
