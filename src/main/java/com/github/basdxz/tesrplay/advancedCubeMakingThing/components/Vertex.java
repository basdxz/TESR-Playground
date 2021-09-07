package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Arrays;
import java.util.List;

import static com.github.basdxz.tesrplay.TESRPlayground.*;
import static com.github.basdxz.tesrplay.TESRPlayground.ABCD;
import static com.github.basdxz.tesrplay.advancedCubeMakingThing.components.CuboidBounds.CuboidBoundGetters.*;
import static com.github.basdxz.tesrplay.advancedCubeMakingThing.components.CuboidBounds.CuboidBoundGetters.MAX_Y_COMP;

@AllArgsConstructor
@NoArgsConstructor
public class Vertex {
    private final PosXYZ posXYZ = new PosXYZ();
    private final PosUV posUV = new PosUV();
    private int brightness;
    private ColorRGBA colorRGBA;
    private ForgeDirection direction;

    public void tessellate() {
        Tessellator.instance.setBrightness(brightness);
        Tessellator.instance.setColorRGBA_F(colorRGBA.r(), colorRGBA.g(), colorRGBA.b(), colorRGBA.a());
        //Tessellator.instance.setNormal(direction.offsetX, direction.offsetY, direction.offsetZ);
        Tessellator.instance.addVertexWithUV(posXYZ.x(), posXYZ.y(), posXYZ.z(), posUV.u(), posUV.v());
    }

    public static class Vertex4D {
        private static final CuboidBounds.CuboidBoundGetters[][][] vertPosMatrix = new CuboidBounds.CuboidBoundGetters[][][]{
                {{MAX_X, MIN_Y, MAX_Z}, {MIN_X, MIN_Y, MAX_Z}, {MIN_X, MIN_Y, MIN_Z}, {MAX_X, MIN_Y, MIN_Z}},
                {{MIN_X, MAX_Y, MAX_Z}, {MAX_X, MAX_Y, MAX_Z}, {MAX_X, MAX_Y, MIN_Z}, {MIN_X, MAX_Y, MIN_Z}},
                {{MAX_X, MIN_Y, MIN_Z}, {MIN_X, MIN_Y, MIN_Z}, {MIN_X, MAX_Y, MIN_Z}, {MAX_X, MAX_Y, MIN_Z}},
                {{MIN_X, MIN_Y, MAX_Z}, {MAX_X, MIN_Y, MAX_Z}, {MAX_X, MAX_Y, MAX_Z}, {MIN_X, MAX_Y, MAX_Z}},
                {{MIN_X, MIN_Y, MIN_Z}, {MIN_X, MIN_Y, MAX_Z}, {MIN_X, MAX_Y, MAX_Z}, {MIN_X, MAX_Y, MIN_Z}},
                {{MAX_X, MIN_Y, MAX_Z}, {MAX_X, MIN_Y, MIN_Z}, {MAX_X, MAX_Y, MIN_Z}, {MAX_X, MAX_Y, MAX_Z}},
        };

        private static final CuboidBounds.CuboidBoundGetters[][][] vertUVMatrix = new CuboidBounds.CuboidBoundGetters[][][]{
                {{MAX_X_COMP, MAX_Z}, {MIN_X_COMP, MAX_Z}, {MIN_X_COMP, MIN_Z}, {MAX_X_COMP, MIN_Z}},
                {{MIN_X, MAX_Z}, {MAX_X, MAX_Z}, {MAX_X, MIN_Z}, {MIN_X, MIN_Z}},
                {{MAX_X_COMP, MIN_Y_COMP}, {MIN_X_COMP, MIN_Y_COMP}, {MIN_X_COMP, MAX_Y_COMP}, {MAX_X_COMP, MAX_Y_COMP}},
                {{MIN_X, MIN_Y_COMP}, {MAX_X, MIN_Y_COMP}, {MAX_X, MAX_Y_COMP}, {MIN_X, MAX_Y_COMP}},
                {{MIN_Z, MIN_Y_COMP}, {MAX_Z, MIN_Y_COMP}, {MAX_Z, MAX_Y_COMP}, {MIN_Z, MAX_Y_COMP}},
                {{MAX_Z_COMP, MIN_Y_COMP}, {MIN_Z_COMP, MIN_Y_COMP}, {MIN_Z_COMP, MAX_Y_COMP}, {MAX_Z_COMP, MAX_Y_COMP}},
        };

        private static final int[][][] vertBrightnessAndAOScratchMatrix = new int[][][]{
                {{0, 1, 0}, {-1, 1, 1}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}, {1, 1, -1}, {0, 1, -1}, {-1, 1, -1}, {-1, 1, 0}},
                {{0, 1, 0}, {-1, 1, 1}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}, {1, 1, -1}, {0, 1, -1}, {-1, 1, -1}, {-1, 1, 0}},
                {{0, 1, 0}, {-1, 1, 1}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}, {1, 1, -1}, {0, 1, -1}, {-1, 1, -1}, {-1, 1, 0}},
                {{0, 1, 0}, {-1, 1, 1}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}, {1, 1, -1}, {0, 1, -1}, {-1, 1, -1}, {-1, 1, 0}},
                {{0, 1, 0}, {-1, 1, 1}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}, {1, 1, -1}, {0, 1, -1}, {-1, 1, -1}, {-1, 1, 0}},
                {{0, 1, 0}, {-1, 1, 1}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}, {1, 1, -1}, {0, 1, -1}, {-1, 1, -1}, {-1, 1, 0}},
        };

        private static final int[][] vertBrightnessAndAOMatrix = new int[][]{
                {A, AB, AD, ABCD}, {B, AB, BC, ABCD}, {C, BC, CD, ABCD}, {D, CD, AD, ABCD}
        };

        private final List<Vertex> vertices = Arrays.asList(new Vertex(), new Vertex(), new Vertex(), new Vertex());
        private CuboidBounds bounds;
        private PosXYZ posXYZ;
        private IBlockAccess blockAccess;
        private boolean ambientOcclusionEnabled;
        private Block block;
        private ForgeDirection faceDirection;
        private BlendableIcon icon;

        private final int[] mixedBrightnessScratch = new int[9];
        private final float[] ambientOcclusionLightScratch = new float[9];
        private final float[] vertAmbientOcclusionFactor = new float[4];

        public void setBlockAccess(IBlockAccess blockAccess) {
            this.blockAccess = blockAccess;
        }

        public void setAmbientOcclusionEnabled(boolean ambientOcclusionEnabled) {
            this.ambientOcclusionEnabled = ambientOcclusionEnabled;
        }

        public void setBlock(Block block) {
            this.block = block;
        }

        public void setBounds(CuboidBounds bounds) {
            this.bounds = bounds;
        }

        public void setPosXYZ(PosXYZ posXYZ) {
            this.posXYZ = posXYZ;
        }

        public void setFace(ForgeDirection faceDirection) {
            this.faceDirection = faceDirection;
        }

        public void setVertPosXYZ() {
            for (int i = 0; i < vertices.size(); i++)
                vertices.get(i).posXYZ.set(bounds.getPos(vertPosMatrix[faceDirection.ordinal()][i])).add(posXYZ);
        }

        public void setIcon(BlendableIcon icon) {
            this.icon = icon;
        }

        public void setVertPosUV() {
            val uvBounds = icon.doStretch() ? CuboidBounds.CUBE_BOUNDS : bounds;
            for (int i = 0; i < vertices.size(); i++)
                vertices.get(i).posUV
                        .set(uvBounds.getPos(vertUVMatrix[faceDirection.ordinal()][i]))
                        .rotate(icon.rotation(), icon.skipScale())
                        .mapToAtlas(icon);
        }

        public void setBrightness() {
            setMixedBrightness();
            setVertBrightness();
            setAmbientOcclusionLight();
            setVertAmbientOcclusionFactors();
        }

        private void setMixedBrightness() {
            for (int i = 0; i < mixedBrightnessScratch.length; i++)
                mixedBrightnessScratch[i] = getMixedBrightnessForBlock(vertBrightnessAndAOScratchMatrix[faceDirection.ordinal()][i]);
        }

        private int getMixedBrightnessForBlock(int... posOffset) {
            if (posOffset.length != 3)
                throw new IllegalArgumentException("Method expects array length 3.");
            return block.getMixedBrightnessForBlock(blockAccess,
                    (int) (posXYZ.x() + posOffset[X]),
                    (int) (posXYZ.y() + posOffset[Y]),
                    (int) (posXYZ.z() + posOffset[Z]));
        }

        private void setVertBrightness() {
            for (int i = 0; i < vertices.size(); i++)
                vertices.get(i).brightness = mixBrightness(vertBrightnessAndAOMatrix[i]);
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
            for (int i = 0; i < mixedBrightnessScratch.length; i++)
                ambientOcclusionLightScratch[i] = AmbientOcclusionLight(vertBrightnessAndAOScratchMatrix[faceDirection.ordinal()][i]);
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
            for (int i = 0; i < vertAmbientOcclusionFactor.length; i++)
                vertAmbientOcclusionFactor[i] = averageAmbientOcclusionLight(vertBrightnessAndAOMatrix[i]);
        }

        private float averageAmbientOcclusionLight(int... aoLightPointer) {
            if (aoLightPointer.length != 4)
                throw new IllegalArgumentException("Method expects array length 4.");
            return (float) Arrays.stream(aoLightPointer)
                    .mapToDouble(i -> ambientOcclusionLightScratch[i]).average().orElse(0);
        }

        public void setColorRGBA() {
            vertices.forEach(vertex -> vertex.colorRGBA = icon.colorRGBA());

            if (!ambientOcclusionEnabled)
                return;

            for (int i = 0; i < vertices.size(); i++)
                vertices.get(i).colorRGBA = vertices.get(i).colorRGBA.mult(vertAmbientOcclusionFactor[i]);
        }

        public void setNormalDirection() {
        }

        public void tessellate() {
            vertices.forEach(Vertex::tessellate);
        }
    }
}
