package com.github.basdxz.tesrplay.newRender.model;

import lombok.*;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

//TODO Compact and make it depend on the GL Util class
//TODO add rotation capability in some meaninful way, perhaps have the interface feed a direction?
//TODO When rotation is handled, make sure all three axis' are involved so that it looks it's best.
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ModelRenderer {
    private static final int CHUNK_LENGTH = 16;
    private static final float CHUNK_RATIO = 1.0F / CHUNK_LENGTH;

    public static void renderWorldBlock(@NonNull Model model, ForgeDirection direction, int posX, int posY, int posZ) {
        renderBuilder(model).blockPosX(posX).blockPosY(posY).blockPosZ(posZ).run();
    }

    public static void renderInventoryBlock(@NonNull Model model) {
        renderBuilder(model).offsetX(-0.5F).offsetY(-0.5F).offsetZ(-0.5F).rotY(90).run();
    }

    public static RenderBuilder renderBuilder(@NonNull Model model) {
        return hiddenRenderBuilder().model(model);
    }

    @Builder(builderClassName = "RenderBuilder", builderMethodName = "hiddenRenderBuilder", buildMethodName = "run",
            access = AccessLevel.PRIVATE)
    private static void render(Model model,
                               int blockPosX, int blockPosY, int blockPosZ,
                               float offsetX, float offsetY, float offsetZ,
                               float rotX, float rotY, float rotZ) {
        safeRender(() -> {
            updateColorTint();
            translateObject(blockPosX, blockPosY, blockPosZ, offsetX, offsetY, offsetZ);
            rotateObject(blockPosX, blockPosZ, rotX, rotY, rotZ);
            scaleFromChunkToBlock();
            renderModel(model);
        });
    }

    private static void safeRender(Runnable render) {
        Tessellator.instance.pauseDraw();
        GL11.glPushMatrix();
        render.run();
        GL11.glPopMatrix();
        Tessellator.instance.resumeDraw();
    }

    //TODO make double and add disable/enable for alpha blend depending on need.
    private static void updateColorTint() {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private static void translateObject(int blockPosX, int blockPosY, int blockPosZ,
                                        float offsetX, float offsetY, float offsetZ) {
        GL11.glTranslatef(
                blockPosToChunkPos(blockPosX) + blockPosToBlockInChunkPos(blockPosX) + offsetX,
                blockPosY + offsetY,
                blockPosToChunkPos(blockPosZ) + blockPosToBlockInChunkPos(blockPosZ) + offsetZ);
    }

    private static void rotateObject(int posX, int posZ, double rotX, double rotY, double rotZ) {
        val chunkPosX = blockPosToChunkPos(posX);
        val chunkPosZ = blockPosToChunkPos(posZ);
        rotateObjectXAxis(chunkPosZ, rotX);
        rotateObjectYAxis(chunkPosX, chunkPosZ, rotY);
        rotateObjectZAxis(chunkPosX, rotZ);
    }

    private static void rotateObjectXAxis(int chunkPosZ, double rotX) {
        val centerY = 0.5F;
        val centerZ = 0.5F - chunkPosZ;
        GL11.glTranslated(0.0F, centerY, centerZ);
        GL11.glRotated(rotX, 1.0F, 0.0F, 0.0F);
        GL11.glTranslated(0.0F, -centerY, -centerZ);
    }

    private static void rotateObjectYAxis(int chunkPosX, int chunkPosZ, double rotY) {
        val centerX = 0.5F - chunkPosX;
        val centerZ = 0.5F - chunkPosZ;
        GL11.glTranslated(centerX, 0.0F, centerZ);
        GL11.glRotated(rotY, 0.0F, 1.0F, 0.0F);
        GL11.glTranslated(-centerX, 0.0F, -centerZ);
    }

    private static void rotateObjectZAxis(int chunkPosX, double rotZ) {
        val centerX = 0.5F - chunkPosX;
        val centerY = 0.5F;
        GL11.glTranslated(centerX, centerY, 0.0F);
        GL11.glRotated(rotZ, 0.0F, 0.0F, 1.0F);
        GL11.glTranslated(-centerX, -centerY, 0.0F);
    }

    private static int blockPosToChunkPos(int blockPos) {
        return blockPos >= 0 ? blockPos / CHUNK_LENGTH : (blockPos + 1) / CHUNK_LENGTH - 1;
    }

    private static int blockPosToBlockInChunkPos(int blockPos) {
        return blockPos >= 0 ? blockPos % CHUNK_LENGTH : (blockPos + 1) % CHUNK_LENGTH + CHUNK_LENGTH - 1;
    }

    private static void scaleFromChunkToBlock() {
        GL11.glScalef(CHUNK_RATIO, CHUNK_RATIO, CHUNK_RATIO);
    }

    private static void renderModel(Model model) {
        model.renderModel();
    }
}
