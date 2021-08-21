package com.github.basdxz.tesrplay;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public abstract class BlockItemSpecialRenderer {
    private static final String ASSET_FILE_DIRECTORY = TESRPlayground.MODID + ":textures/models/";

    private final String name;
    private final ResourceLocation texture;
    private final IModelCustom model;

    public BlockItemSpecialRenderer(String name) {
        this.name = name;
        texture = generateTexure();
        model = generateModel();
    }

    private ResourceLocation generateTexure() {
        return new ResourceLocation(ASSET_FILE_DIRECTORY + name + ".png");
    }

    private IModelCustom generateModel() {
        return AdvancedModelLoader.loadModel(new ResourceLocation(ASSET_FILE_DIRECTORY + name + ".obj"));
    }

    public abstract void renderBlock(int posX, int posY, int posZ);

    public abstract void renderItem(RenderBlocks renderer);

    protected void bindTexture() {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }

    protected void renderModel() {
        model.renderAll();
    }

    protected static void safeRender(Runnable runnable) {
        TesselationHijacker.pauseDraw();
        GL11.glPushMatrix();
        runnable.run();
        GL11.glPopMatrix();
        TesselationHijacker.resumeDraw();
    }
}
