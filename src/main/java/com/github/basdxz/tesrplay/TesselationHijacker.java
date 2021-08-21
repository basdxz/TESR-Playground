package com.github.basdxz.tesrplay;

import cpw.mods.fml.client.FMLClientHandler;
import lombok.SneakyThrows;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;

import java.lang.reflect.Field;

public final class TesselationHijacker {
    private static Field isDrawingBoolean;
    private static Field drawModeInt;
    private static boolean suspendedDrawing;
    private static int suspendedDrawMode;

    static {
        init();
    }

    private TesselationHijacker() {
    }

    @SneakyThrows
    private static void init() {
        isDrawingBoolean = Tessellator.class.getDeclaredField("isDrawing");
        isDrawingBoolean.setAccessible(true);
        drawModeInt = Tessellator.class.getDeclaredField("drawMode");
        drawModeInt.setAccessible(true);
    }

    @SneakyThrows
    public static void pauseDraw() {
        suspendedDrawing = (boolean) isDrawingBoolean.get(Tessellator.instance);
        if (suspendedDrawing) {
            suspendedDrawMode = (int) drawModeInt.get(Tessellator.instance);
            Tessellator.instance.draw();
        }
    }

    public static void resumeDraw() {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        if (suspendedDrawing) {
            Tessellator.instance.startDrawing(suspendedDrawMode);
        }
    }
}
