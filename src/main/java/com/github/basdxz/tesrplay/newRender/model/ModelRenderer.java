package com.github.basdxz.tesrplay.newRender.model;

import lombok.*;
import net.minecraftforge.common.util.ForgeDirection;

import static com.github.basdxz.tesrplay.newRender.commonGL.GLUtils.*;

//TODO Copy the pattern from cuboid renderer w.r.t. variables
//TODO add rotation capability in some meaninful way, perhaps have the interface feed a direction?
//TODO When rotation is handled, make sure all three axis' are involved so that it looks it's best.
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ModelRenderer {
    private final static int CHUNK_LENGTH = 16;
    private final static float CHUNK_RATIO = 1.0F / CHUNK_LENGTH;

    public static void renderWorldBlock(@NonNull Model model, ForgeDirection direction, int posX, int posY, int posZ) {
        renderBuilder(model).blockPosX(posX).blockPosY(posY).blockPosZ(posZ).run();
    }

    public static void renderInventoryBlock(@NonNull Model model) {
        renderBuilder(model).offsetX(-BLOCK_CENTER_OFFSET.x()).offsetY(-0.5F).offsetZ(-0.5F).rotY(90).run();
    }

    public static RenderBuilderr renderBuilder(@NonNull Model model) {
        return hiddenRenderBuilder().model(model);
    }

    @Builder(builderClassName = "RenderBuilderr", builderMethodName = "hiddenRenderBuilder", buildMethodName = "run",
            access = AccessLevel.PRIVATE)
    private static void render(Model model,
                        int blockPosX, int blockPosY, int blockPosZ,
                        double offsetX, double offsetY, double offsetZ,
                        double rotX, double rotY, double rotZ) {
        val chunkPosX = blockPosToChunkPos(blockPosX);
        val chunkPosZ = blockPosToChunkPos(blockPosZ);
        safeRender(() -> {
            glColor();
            translateObject(chunkPosX, chunkPosZ, blockPosX, blockPosY, blockPosZ, offsetX, offsetY, offsetZ);
            rotateObject(chunkPosX, chunkPosZ, rotX, rotY, rotZ);
            scaleFromChunkToBlock();
            renderModel(model);
        });
    }

    private static int blockPosToChunkPos(int blockPos) {
        return blockPos >= 0 ? blockPos / CHUNK_LENGTH : (blockPos + 1) / CHUNK_LENGTH - 1;
    }

    private static void translateObject(int chunkPosX, int chunkPosZ,
                                 int blockPosX, int blockPosY, int blockPosZ,
                                 double offsetX, double offsetY, double offsetZ) {
        glTranslate(
                chunkPosX + blockPosToBlockInChunkPos(blockPosX) + offsetX,
                blockPosY + offsetY,
                chunkPosZ + blockPosToBlockInChunkPos(blockPosZ) + offsetZ);
    }

    private static void rotateObject(int chunkPosX, int chunkPosZ, double rotX, double rotY, double rotZ) {
        glXRotate(rotX, BLOCK_CENTER_OFFSET.y(), BLOCK_CENTER_OFFSET.z() - chunkPosZ);
        glYRotate(rotY, BLOCK_CENTER_OFFSET.x() - chunkPosX, BLOCK_CENTER_OFFSET.z() - chunkPosZ);
        glZRotate(rotZ, BLOCK_CENTER_OFFSET.x() - chunkPosX, BLOCK_CENTER_OFFSET.y());
    }

    private static int blockPosToBlockInChunkPos(int blockPos) {
        return blockPos >= 0 ? blockPos % CHUNK_LENGTH : (blockPos + 1) % CHUNK_LENGTH + CHUNK_LENGTH - 1;
    }

    private static void scaleFromChunkToBlock() {
        glScale(CHUNK_RATIO);
    }

    private static void renderModel(Model model) {
        model.renderModel();
    }
}
