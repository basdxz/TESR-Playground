package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import lombok.AllArgsConstructor;
import net.minecraft.client.renderer.Tessellator;

@AllArgsConstructor
public class Vertex {
    private final double posX;
    private final double posY;
    private final double posZ;
    private final double posU;
    private final double posV;
    //private final redAO;
    //private final greenAO;
    //private final blueAO;
    //private final brightnessAO;

    public void addToTesselator() {
        Tessellator.instance.addVertexWithUV(posX, posY, posZ, posU, posV);
    }
}
