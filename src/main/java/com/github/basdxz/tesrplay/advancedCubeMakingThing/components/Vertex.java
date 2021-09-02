package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.AllArgsConstructor;
import net.minecraft.client.renderer.Tessellator;

@AllArgsConstructor
public class Vertex {
    private final PosXYZ posXYZ;
    private final PosUV posUV;
    //private final redAO;
    //private final greenAO;
    //private final blueAO;
    //private final brightnessAO;

    public void tessellate() {
        Tessellator.instance.addVertexWithUV(posXYZ.x(), posXYZ.y(), posXYZ.z(), posUV.u(), posUV.v());
    }
}
