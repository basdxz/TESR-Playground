package com.github.basdxz.tesrplay.ultimate;

import com.github.basdxz.tesrplay.TESRPlayground;
import cpw.mods.fml.client.FMLClientHandler;
import lombok.Builder;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class WavefrontBlockRenderer {
    private static final String ASSET_FILE_DIRECTORY = TESRPlayground.MODID + ":textures/models/";
    private static final float CHUNK_TO_BLOCK_SCALE = 0.0625F;
    private static final int BLOCK_TO_CHUNK_SCALE = 16;
    private static final float HALF = 0.5F;

    private final String name;
    private final ResourceLocation texture;
    private final IModelCustom model;

    public WavefrontBlockRenderer(String name) {
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

    @Builder(builderMethodName = "renderBuilder", buildMethodName = "run")
    private void render(int blockPosX, int blockPosY, int blockPosZ, float offsetX, float offsetY, float offsetZ,
                        float rotX, float rotY, float rotZ) {
        TesselationHijacker.pauseDraw();
        GL11.glPushMatrix(); //Start Rendering
        bindTexture();
        updateColorTint();
        translateObject(blockPosX, blockPosY, blockPosZ, offsetX, offsetY, offsetZ);
        rotateObject(blockPosX, blockPosY, blockPosZ, rotX, rotY, rotZ);
        scaleFromChunkToBlock();
        renderModel();
        GL11.glPopMatrix(); //Done Rendering
        TesselationHijacker.resumeDraw();
    }

    private void bindTexture() {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }

    private static void updateColorTint() {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private static void translateObject(int blockPosX, int blockPosY, int blockPosZ, float offsetX, float offsetY,
                                        float offsetZ) {
        GL11.glTranslatef(
                blockPosToChunkPos(blockPosX) + blockPosToBlockInChunkPos(blockPosX),
                blockPosY,
                blockPosToChunkPos(blockPosZ) + blockPosToBlockInChunkPos(blockPosZ));
    }

    private static void rotateObject(int posX, int posY, int posZ, double rotX, double rotY, double rotZ) {
        int chunkPosX = blockPosToChunkPos(posX);
        int chunkPosZ = blockPosToChunkPos(posZ);
        rotateObjectXAxis(0, chunkPosZ, rotX);
        rotateObjectYAxis(chunkPosX, chunkPosZ, rotY);
        rotateObjectZAxis(chunkPosX, 0, rotZ);
    }

    private static void rotateObjectXAxis(int chunkPosY, int chunkPosZ, double rotX) {
        float centerY = HALF - chunkPosY;
        float centerZ = HALF - chunkPosZ;
        GL11.glTranslated(0.0F, centerY, centerZ);
        GL11.glRotated(rotX, 1.0F, 0.0F, 0.0F);
        GL11.glTranslated(0.0F, -centerY, -centerZ);
    }

    private static void rotateObjectYAxis(int chunkPosX, int chunkPosZ, double rotY) {
        float centerX = HALF - chunkPosX;
        float centerZ = HALF - chunkPosZ;
        GL11.glTranslated(centerX, 0.0F, centerZ);
        GL11.glRotated(rotY, 0.0F, 1.0F, 0.0F);
        GL11.glTranslated(-centerX, 0.0F, -centerZ);
    }

    private static void rotateObjectZAxis(int chunkPosX, int chunkPosY, double rotZ) {
        float centerX = HALF - chunkPosX;
        float centerY = HALF - chunkPosY;
        GL11.glTranslated(centerX, centerY, 0.0F);
        GL11.glRotated(rotZ, 0.0F, 0.0F, 1.0F);
        GL11.glTranslated(-centerX, -centerY, 0.0F);
    }

    private static int blockPosToChunkPos(int blockPos) {
        if (blockPos >= 0) {
            return blockPos / BLOCK_TO_CHUNK_SCALE;
        } else {
            return (blockPos + 1) / BLOCK_TO_CHUNK_SCALE - 1;
        }
    }

    private static int blockPosToBlockInChunkPos(int blockPos) {
        if (blockPos >= 0) {
            return blockPos % BLOCK_TO_CHUNK_SCALE;
        } else {
            return (blockPos + 1) % BLOCK_TO_CHUNK_SCALE + BLOCK_TO_CHUNK_SCALE - 1;
        }
    }

    private static void scaleFromChunkToBlock() {
        GL11.glScalef(CHUNK_TO_BLOCK_SCALE, CHUNK_TO_BLOCK_SCALE, CHUNK_TO_BLOCK_SCALE);
    }

    private void renderModel() {
        model.renderAll();
    }

    public void renderBlock(int posX, int posY, int posZ) {
        renderBuilder().blockPosX(posX).blockPosY(posY).blockPosZ(posZ).run();
    }

    public void renderItem(RenderBlocks renderer) {
        renderBuilder().run();
    }
}
