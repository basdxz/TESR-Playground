package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.AllArgsConstructor;
import net.minecraft.client.renderer.Tessellator;

@AllArgsConstructor
public class Vertex {
    private final double posX;
    private final double posY;
    private final double posZ;
    private final PosUV posUV;
    //private final redAO;
    //private final greenAO;
    //private final blueAO;
    //private final brightnessAO;

    public void tessellate() {
        Tessellator.instance.addVertexWithUV(posX, posY, posZ, posUV.posU(), posUV.posV());
    }
}
