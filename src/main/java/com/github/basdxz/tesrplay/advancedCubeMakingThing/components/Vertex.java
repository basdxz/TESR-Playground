package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.AllArgsConstructor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.common.util.ForgeDirection;

@AllArgsConstructor
public class Vertex {
    private final PosXYZ posXYZ;
    private final PosUV posUV;
    private final int brightness;
    private final ColorRGBA colorRGBA;
    private final ForgeDirection direction;

    public void tessellate() {
        Tessellator.instance.setBrightness(brightness);
        Tessellator.instance.setColorRGBA_F(colorRGBA.r(), colorRGBA.g(), colorRGBA.b(), colorRGBA.a());
        Tessellator.instance.setNormal(direction.offsetX, direction.offsetY, direction.offsetZ);
        Tessellator.instance.addVertexWithUV(posXYZ.x(), posXYZ.y(), posXYZ.z(), posUV.u(), posUV.v());
    }
}
