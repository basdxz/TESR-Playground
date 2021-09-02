package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RenderMessAround {
    public void cubeDrawingButRoundAboutWay(PosXYZ posXYZ, CuboidBounds bounds, LayeredIcon shading) {
        CuboidRenderer.renderCube(posXYZ, bounds, shading);
    }
}
