package com.github.basdxz.tesrplay.newRender.cuboid;

import lombok.NoArgsConstructor;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.github.basdxz.tesrplay.Reference.*;
import static com.github.basdxz.tesrplay.newRender.commonGL.GLUtils.blendAndTessellate;
import static com.github.basdxz.tesrplay.newRender.cuboid.CuboidBounds.CuboidBoundGetters.*;

@NoArgsConstructor
public class Vertex {
    private boolean hasBrightness;
    private int brightness;
    private final ColorRGBA colorRGBA = new ColorRGBA();
    private final PosXYZ posXYZ = new PosXYZ();
    private final PosXYZ posNormal = new PosXYZ();
    private final PosUV posUV = new PosUV();

    private void tessellate() {
        if (hasBrightness) Tessellator.instance.setBrightness(brightness);
        Tessellator.instance.setColorRGBA_F(colorRGBA.r(), colorRGBA.g(), colorRGBA.b(), colorRGBA.a());
        //Tessellator.instance.setNormal((float) posNormal.x(), (float) posNormal.y(), (float) posNormal.z());
        Tessellator.instance.addVertexWithUV(posXYZ.x(), posXYZ.y(), posXYZ.z(), posUV.u(), posUV.v());
    }

    @NoArgsConstructor
    public static class Vertex4D {
        private static final CuboidBounds.CuboidBoundGetters[][][] vertPosMatrix = new CuboidBounds.CuboidBoundGetters[][][]{
                {{MAX_X, MIN_Y, MAX_Z}, {MIN_X, MIN_Y, MAX_Z}, {MIN_X, MIN_Y, MIN_Z}, {MAX_X, MIN_Y, MIN_Z}},
                {{MIN_X, MAX_Y, MAX_Z}, {MAX_X, MAX_Y, MAX_Z}, {MAX_X, MAX_Y, MIN_Z}, {MIN_X, MAX_Y, MIN_Z}},
                {{MAX_X, MIN_Y, MIN_Z}, {MIN_X, MIN_Y, MIN_Z}, {MIN_X, MAX_Y, MIN_Z}, {MAX_X, MAX_Y, MIN_Z}},
                {{MIN_X, MIN_Y, MAX_Z}, {MAX_X, MIN_Y, MAX_Z}, {MAX_X, MAX_Y, MAX_Z}, {MIN_X, MAX_Y, MAX_Z}},
                {{MIN_X, MIN_Y, MIN_Z}, {MIN_X, MIN_Y, MAX_Z}, {MIN_X, MAX_Y, MAX_Z}, {MIN_X, MAX_Y, MIN_Z}},
                {{MAX_X, MIN_Y, MAX_Z}, {MAX_X, MIN_Y, MIN_Z}, {MAX_X, MAX_Y, MIN_Z}, {MAX_X, MAX_Y, MAX_Z}}
        };

        private static final CuboidBounds.CuboidBoundGetters[][][] vertUVMatrix = new CuboidBounds.CuboidBoundGetters[][][]{
                {{MAX_X_COMP, MAX_Z}, {MIN_X_COMP, MAX_Z}, {MIN_X_COMP, MIN_Z}, {MAX_X_COMP, MIN_Z}},
                {{MIN_X, MAX_Z}, {MAX_X, MAX_Z}, {MAX_X, MIN_Z}, {MIN_X, MIN_Z}},
                {{MAX_X_COMP, MIN_Y_COMP}, {MIN_X_COMP, MIN_Y_COMP}, {MIN_X_COMP, MAX_Y_COMP}, {MAX_X_COMP, MAX_Y_COMP}},
                {{MIN_X, MIN_Y_COMP}, {MAX_X, MIN_Y_COMP}, {MAX_X, MAX_Y_COMP}, {MIN_X, MAX_Y_COMP}},
                {{MIN_Z, MIN_Y_COMP}, {MAX_Z, MIN_Y_COMP}, {MAX_Z, MAX_Y_COMP}, {MIN_Z, MAX_Y_COMP}},
                {{MAX_Z_COMP, MIN_Y_COMP}, {MIN_Z_COMP, MIN_Y_COMP}, {MIN_Z_COMP, MAX_Y_COMP}, {MAX_Z_COMP, MAX_Y_COMP}}
        };

        private static final int[][][] vertBrightnessAndAOScratchMatrix = new int[][][]{
                {{0, -1, 0}, {1, -1, -1}, {0, -1, -1}, {-1, -1, -1}, {-1, -1, 0}, {-1, -1, 1}, {0, -1, 1}, {1, -1, 1}, {1, -1, 0}},
                {{0, 1, 0}, {-1, 1, 1}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}, {1, 1, -1}, {0, 1, -1}, {-1, 1, -1}, {-1, 1, 0}},
                {{0, 0, -1}, {1, -1, -1}, {0, -1, -1}, {-1, -1, -1}, {-1, 0, -1}, {-1, 1, -1}, {0, 1, -1}, {1, 1, -1}, {1, 0, -1}},
                {{0, 0, 1}, {-1, -1, 1}, {0, -1, 1}, {1, -1, 1}, {1, 0, 1}, {1, 1, 1}, {0, 1, 1}, {-1, 1, 1}, {-1, 0, 1}},
                {{-1, 0, 0}, {-1, -1, -1}, {-1, -1, 0}, {-1, -1, 1}, {-1, 0, 1}, {-1, 1, 1}, {-1, 1, 0}, {-1, 1, -1}, {-1, 0, -1}},
                {{1, 0, 0}, {1, -1, 1}, {1, -1, 0}, {1, -1, -1}, {1, 0, -1}, {1, 1, -1}, {1, 1, 0}, {1, 1, 1}, {1, 0, 1}}
        };

        private static final int[][] vertBrightnessAndAOMatrix = new int[][]{
                {A, AB, AD, ABCD}, {B, AB, BC, ABCD}, {C, BC, CD, ABCD}, {D, CD, AD, ABCD}
        };

        private static final float[] vertTintMatrix = new float[]{0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F};

        private static final CuboidBounds.CuboidBoundGetters[][] vertNormalMatrix = new CuboidBounds.CuboidBoundGetters[][]{
                {ZERO, MIN_Y_INV, ZERO}, {ZERO, MAX_Y, ZERO}, {ZERO, ZERO, MIN_Z_INV}, {ZERO, ZERO, MAX_Z}, {MIN_X_INV, ZERO, ZERO}, {MAX_X, ZERO, ZERO}};

        private final Vertex[] vertices = new Vertex[]{new Vertex(), new Vertex(), new Vertex(), new Vertex()};

        private IBlockAccess blockAccess;
        private boolean renderingAsItem;

        private Block block;
        private CuboidBounds bounds;
        private PosXYZ posXYZ;

        private ForgeDirection faceDirection;
        private BlendableIcon layer;

        private final int[] mixedBrightnessScratch = new int[9];
        private final float[] ambientOcclusionLightScratch = new float[9];
        private final float[] vertAmbientOcclusionFactor = new float[4];

        public void setBlock(IBlockAccess blockAccess, Block block, CuboidBounds bounds, PosXYZ posXYZ) {
            renderingAsItem = blockAccess == null;
            this.block = block;
            this.bounds = bounds;
            this.blockAccess = blockAccess;
            this.posXYZ = posXYZ;
        }

        private boolean skipAmbientOcclusion() {
            return renderingAsItem || !Minecraft.isAmbientOcclusionEnabled();
        }

        public void preRender(ForgeDirection faceDirection) {
            setFace(faceDirection);
            setMixedBrightness();
            setVertBrightness();
            setAmbientOcclusionLight();
            setVertAmbientOcclusionFactors();
        }

        private void setFace(ForgeDirection faceDirection) {
            this.faceDirection = faceDirection;
        }

        private void setMixedBrightness() {
            if (skipAmbientOcclusion()) return;
            IntStream.range(0, mixedBrightnessScratch.length).forEach(i -> mixedBrightnessScratch[i]
                    = getMixedBrightnessForBlock(vertBrightnessAndAOScratchMatrix[faceDirection.ordinal()][i]));
        }

        private void setVertBrightness() {
            IntStream.range(0, vertices.length).forEach(i -> {
                vertices[i].hasBrightness = !renderingAsItem;
                if (renderingAsItem) return;
                vertices[i].brightness = skipAmbientOcclusion()
                        ? getMixedBrightnessForBlock(vertBrightnessAndAOScratchMatrix[faceDirection.ordinal()][ABCD])
                        : mixBrightness(vertBrightnessAndAOMatrix[i]);
            });
        }

        private int getMixedBrightnessForBlock(int... posOffset) {
            if (posOffset.length != 3)
                throw new IllegalArgumentException("Method expects array length 3.");
            return block.getMixedBrightnessForBlock(blockAccess,
                    (int) (posXYZ.x() + posOffset[X]),
                    (int) (posXYZ.y() + posOffset[Y]),
                    (int) (posXYZ.z() + posOffset[Z]));
        }

        private int mixBrightness(int... ao) {
            if (ao.length != 4)
                throw new IllegalArgumentException("Method expects array length 4.");
            val ao3 = mixedBrightnessScratch[ao[AO3]];
            val ao0 = mixedBrightnessScratch[ao[AO0]] != 0 ? mixedBrightnessScratch[ao[AO0]] : ao3;
            val ao1 = mixedBrightnessScratch[ao[AO1]] != 0 ? mixedBrightnessScratch[ao[AO1]] : ao3;
            val ao2 = mixedBrightnessScratch[ao[A02]] != 0 ? mixedBrightnessScratch[ao[A02]] : ao3;
            return (ao0 + ao1 + ao2 + ao3) / 4 & 0xFF00FF;
        }

        private void setAmbientOcclusionLight() {
            if (skipAmbientOcclusion()) return;
            IntStream.range(0, ambientOcclusionLightScratch.length).forEach(i -> ambientOcclusionLightScratch[i]
                    = AmbientOcclusionLight(vertBrightnessAndAOScratchMatrix[faceDirection.ordinal()][i]));
        }

        private float AmbientOcclusionLight(int... posOffset) {
            return getBlockWithOffset(posOffset).getAmbientOcclusionLightValue();
        }

        private Block getBlockWithOffset(int... posOffset) {
            if (posOffset.length != 3)
                throw new IllegalArgumentException("Method expects array length 3.");
            return blockAccess.getBlock(
                    (int) (posXYZ.x() + posOffset[X]),
                    (int) (posXYZ.y() + posOffset[Y]),
                    (int) (posXYZ.z() + posOffset[Z]));
        }

        private void setVertAmbientOcclusionFactors() {
            if (skipAmbientOcclusion()) return;
            for (int i = 0; i < vertAmbientOcclusionFactor.length; i++)
                vertAmbientOcclusionFactor[i] = averageAmbientOcclusionLight(vertBrightnessAndAOMatrix[i]);
        }

        private float averageAmbientOcclusionLight(int... aoLightPointer) {
            if (aoLightPointer.length != 4)
                throw new IllegalArgumentException("Method expects array length 4.");
            return (float) Arrays.stream(aoLightPointer)
                    .mapToDouble(i -> ambientOcclusionLightScratch[i]).average().orElse(0);
        }

        public void render(BlendableIcon layer) {
            setLayer(layer);
            setVertPosXYZ();
            setColorRGBA();
            setVertPosUV();
            setNormalDirection();
            tessellate();
        }

        private void setLayer(BlendableIcon layer) {
            this.layer = layer;
        }

        private void setVertPosXYZ() {
            IntStream.range(0, vertices.length).forEach(i ->
                    vertices[i].posXYZ.set(bounds.getPos(vertPosMatrix[faceDirection.ordinal()][i])).add(posXYZ));
        }

        private void setColorRGBA() {
            IntStream.range(0, vertices.length).forEach(i -> vertices[i].colorRGBA.set(layer.colorRGBA())
                    .mult((layer.flatTint() || renderingAsItem ? 1.0F : vertTintMatrix[faceDirection.ordinal()])
                            * (skipAmbientOcclusion() ? 1.0F : vertAmbientOcclusionFactor[i])));
        }

        private void setVertPosUV() {
            val uvBounds = layer.doStretch() ? CuboidBounds.CUBE_BOUNDS : bounds;
            IntStream.range(0, vertices.length).forEach(i -> vertices[i].posUV
                    .set(uvBounds.getPos(Vertex4D.vertUVMatrix[faceDirection.ordinal()][i]))
                    .rotate(layer.rotation(), layer.skipScale())
                    .mapToAtlas(layer));
        }

        private void setNormalDirection() {
            IntStream.range(0, vertices.length).forEach(i -> vertices[i].posNormal.set(
                    bounds.getPos(vertNormalMatrix[faceDirection.ordinal()])));
        }

        private void tessellate() {
            blendAndTessellate(
                    layer,
                    () -> IntStream.range(0, vertices.length).forEach(i -> vertices[i].tessellate()),
                    renderingAsItem);
        }
    }
}
