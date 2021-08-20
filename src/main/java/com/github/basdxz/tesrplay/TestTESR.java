package com.github.basdxz.tesrplay;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class TestTESR extends TileEntitySpecialRenderer {
    private static final float HALF = 0.5F;
    private static final float SCALE_1_TO_1 = 0.0625F;

    private static final ResourceLocation TEXTURE;
    private static final ResourceLocation OBJ_MODEL_LOCATION;
    private static final IModelCustom MODEL;

    static {
        TEXTURE = new ResourceLocation(TESRPlayground.MODID + ":textures/models/baracc.png");
        OBJ_MODEL_LOCATION = new ResourceLocation(TESRPlayground.MODID + ":textures/models/prism.obj");
        MODEL = AdvancedModelLoader.loadModel(OBJ_MODEL_LOCATION);
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float timeSinceLastTick) {
        bindTexture(TEXTURE);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslated(posX + HALF, posY, posZ + HALF);
        GL11.glTranslated(HALF, 0, HALF);
        GL11.glScalef(SCALE_1_TO_1, SCALE_1_TO_1, SCALE_1_TO_1);
        GL11.glPushMatrix();
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
        MODEL.renderAll();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
