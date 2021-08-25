package com.github.basdxz.tesrplay;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public abstract class BlockItemSpecialRendererr {
    private static final String ASSET_FILE_DIRECTORY = ":textures/models/";

    private final String name;
    private final ResourceLocation texture;
    private final IModelCustom model;

    public BlockItemSpecialRendererr(String modid, String name) {
        this.name = name;
        texture = generateTexure(modid);
        model = generateModel(modid);
    }

    private ResourceLocation generateTexure(String modid) {
        return new ResourceLocation(modid + ASSET_FILE_DIRECTORY + name + ".png");
    }

    private IModelCustom generateModel(String modid) {
        return AdvancedModelLoader.loadModel(
                new ResourceLocation(modid + ASSET_FILE_DIRECTORY + name + ".obj"));
    }

    public abstract void renderBlock(int posX, int posY, int posZ);

    public abstract void renderItem();

    protected void bindTexture() {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }

    protected void renderModel() {
        model.renderAll();
    }

    protected static void safeRender(Runnable runnable) {
        Tessellator.instance.pauseDraw();
        GL11.glPushMatrix();
        runnable.run();
        GL11.glPopMatrix();
        Tessellator.instance.resumeDraw();
    }
}
