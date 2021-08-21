package com.github.basdxz.tesrplay;

import lombok.Builder;
import net.minecraft.client.renderer.RenderBlocks;
import org.lwjgl.opengl.GL11;

public class ObamaRenderer extends BlockItemSpecialRenderer {
    private static final float CHUNK_TO_BLOCK_SCALE = 0.0625F;
    private static final int BLOCK_TO_CHUNK_SCALE = 16;
    private static final float HALF = 0.5F;

    public ObamaRenderer(String name) {
        super(TESRPlayground.MODID, name);
    }

    @Builder(builderMethodName = "renderBuilder", buildMethodName = "run")
    protected void render(int blockPosX, int blockPosY, int blockPosZ, float offsetX, float offsetY, float offsetZ,
                          float rotX, float rotY, float rotZ) {
        safeRender(() -> {
            bindTexture();
            updateColorTint();
            translateObject(blockPosX, blockPosY, blockPosZ, offsetX, offsetY, offsetZ);
            rotateObject(blockPosX, blockPosY, blockPosZ, rotX, rotY, rotZ);
            scaleFromChunkToBlock();
            renderModel();
        });
    }

    private static void updateColorTint() {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private static void translateObject(int blockPosX, int blockPosY, int blockPosZ, float offsetX, float offsetY,
                                        float offsetZ) {
        GL11.glTranslatef(
                blockPosToChunkPos(blockPosX) + blockPosToBlockInChunkPos(blockPosX) + offsetX,
                blockPosY + offsetY,
                blockPosToChunkPos(blockPosZ) + blockPosToBlockInChunkPos(blockPosZ) + offsetZ);
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

    public void renderBlock(int posX, int posY, int posZ) {
        renderBuilder().blockPosX(posX).blockPosY(posY).blockPosZ(posZ).run();
    }

    public void renderItem() {
        renderBuilder().offsetX(-0.5F).offsetY(-0.5F).offsetZ(-0.5F).rotY(90).run();
    }
}
