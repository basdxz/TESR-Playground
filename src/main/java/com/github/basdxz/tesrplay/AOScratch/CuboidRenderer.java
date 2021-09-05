package com.github.basdxz.tesrplay.AOScratch;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.StraightGLUtil;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.Quad;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.*;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.CuboidBounds.CuboidBoundGetters;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import static com.github.basdxz.tesrplay.advancedCubeMakingThing.components.CuboidBounds.CuboidBoundGetters.*;
import static net.minecraftforge.common.util.ForgeDirection.*;

//TODO: Relocate
public class CuboidRenderer {
    private static final CuboidBoundGetters[][][] vertPosMatrix = new CuboidBoundGetters[][][]{
            {{MAX_X, MIN_Y, MAX_Z}, {MIN_X, MIN_Y, MAX_Z}, {MIN_X, MIN_Y, MIN_Z}, {MAX_X, MIN_Y, MIN_Z}},
            {{MIN_X, MAX_Y, MAX_Z}, {MAX_X, MAX_Y, MAX_Z}, {MAX_X, MAX_Y, MIN_Z}, {MIN_X, MAX_Y, MIN_Z}},
            {{MAX_X, MIN_Y, MIN_Z}, {MIN_X, MIN_Y, MIN_Z}, {MIN_X, MAX_Y, MIN_Z}, {MAX_X, MAX_Y, MIN_Z}},
            {{MIN_X, MIN_Y, MAX_Z}, {MAX_X, MIN_Y, MAX_Z}, {MAX_X, MAX_Y, MAX_Z}, {MIN_X, MAX_Y, MAX_Z}},
            {{MIN_X, MIN_Y, MIN_Z}, {MIN_X, MIN_Y, MAX_Z}, {MIN_X, MAX_Y, MAX_Z}, {MIN_X, MAX_Y, MIN_Z}},
            {{MAX_X, MIN_Y, MAX_Z}, {MAX_X, MIN_Y, MIN_Z}, {MAX_X, MAX_Y, MIN_Z}, {MAX_X, MAX_Y, MAX_Z}},
    };

    private static final CuboidBoundGetters[][][] vertUVMatrix = new CuboidBoundGetters[][][]{
            {{MAX_X_COMP, MAX_Z}, {MIN_X_COMP, MAX_Z}, {MIN_X_COMP, MIN_Z}, {MAX_X_COMP, MIN_Z}},
            {{MIN_X, MAX_Z}, {MAX_X, MAX_Z}, {MAX_X, MIN_Z}, {MIN_X, MIN_Z}},
            {{MAX_X_COMP, MIN_Y_COMP}, {MIN_X_COMP, MIN_Y_COMP}, {MIN_X_COMP, MAX_Y_COMP}, {MAX_X_COMP, MAX_Y_COMP}},
            {{MIN_X, MIN_Y_COMP}, {MAX_X, MIN_Y_COMP}, {MAX_X, MAX_Y_COMP}, {MIN_X, MAX_Y_COMP}},
            {{MIN_Z, MIN_Y_COMP}, {MAX_Z, MIN_Y_COMP}, {MAX_Z, MAX_Y_COMP}, {MIN_Z, MAX_Y_COMP}},
            {{MAX_Z_COMP, MIN_Y_COMP}, {MIN_Z_COMP, MIN_Y_COMP}, {MIN_Z_COMP, MAX_Y_COMP}, {MAX_Z_COMP, MAX_Y_COMP}},
    };

    private final Block block;
    private final PosXYZ posXYZ;
    private final CuboidBounds bounds;
    private final LayeredIcon layeredIcon;
    private final IBlockAccess blockAccess;
    private final boolean ambientOcclusionEnabled;
    private final int mixedBrightnessSelf;

    private ForgeDirection faceDirection;

    private final PosXYZ[] vertPos = new PosXYZ[]{new PosXYZ(), new PosXYZ(), new PosXYZ(), new PosXYZ()};

    private BlendableIcon layer;

    private final PosUV[] vertUV = new PosUV[4];

    private int mixedBrightnessABCD;

    private int mixedBrightnessAB;
    private int mixedBrightnessBC;
    private int mixedBrightnessCD;
    private int mixedBrightnessAD;

    private int mixedBrightnessA;
    private int mixedBrightnessB;
    private int mixedBrightnessC;
    private int mixedBrightnessD;

    private int vertABrightness;
    private int vertBBrightness;
    private int vertCBrightness;
    private int vertDBrightness;

    private float ambientOcclusionLightABCD;

    private float ambientOcclusionLightAB;
    private float ambientOcclusionLightBC;
    private float ambientOcclusionLightCD;
    private float ambientOcclusionLightAD;

    private float ambientOcclusionLightA;
    private float ambientOcclusionLightB;
    private float ambientOcclusionLightC;
    private float ambientOcclusionLightD;

    private float vertAAmbientOcclusionFactor;
    private float vertBAmbientOcclusionFactor;
    private float vertCAmbientOcclusionFactor;
    private float vertDAmbientOcclusionFactor;

    private ColorRGBA vertAColorRGBA;//TODO Make final /w modifier
    private ColorRGBA vertBColorRGBA;//TODO Make final /w modifier
    private ColorRGBA vertCColorRGBA;//TODO Make final /w modifier
    private ColorRGBA vertDColorRGBA;//TODO Make final /w modifier

    public CuboidRenderer(Block block, PosXYZ posXYZ, CuboidBounds bounds, LayeredIcon layeredIcon) {
        this.block = block;
        this.posXYZ = posXYZ;
        this.bounds = bounds;
        this.layeredIcon = layeredIcon;
        blockAccess = getWorldAccess();
        ambientOcclusionEnabled = getAmbientOcclusionState();
        mixedBrightnessSelf = getMixedBrightnessSelf();
    }

    private static IBlockAccess getWorldAccess() {
        return Minecraft.getMinecraft().theWorld;
    }

    private static boolean getAmbientOcclusionState() {
        return Minecraft.isAmbientOcclusionEnabled();
    }

    private int getMixedBrightnessSelf() {
        return getMixedBrightnessForBlock(0, 0, 0);
    }

    /*
        -Optimise blocks being placed over facings blocking light and running without AO
        -AO on the other 5 sides
        -Scale Normal Facing by bounds
        -Tinting per layer with 0-1.0 tining, where 0 means none and 1.0 means maxed out.
     */
    public void render() {
        setFaceDirection(DOWN);
        if (shouldSideBeRendered()) {
            setVertPos();
            setMixedBrightnessTop();
            setVertBrightness();
            setAmbientOcclusionLightTop();
            setVertAmbientOcclusionFactors();
            for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(faceDirection)) {
                setLayer(layer);
                setVertColorRGBA();
                setVertUV();
                drawQuad();
            }
        }
        setFaceDirection(UP);
        if (shouldSideBeRendered()) {
            setVertPos();
            setMixedBrightnessTop();
            setVertBrightness();
            setAmbientOcclusionLightTop();
            setVertAmbientOcclusionFactors();
            for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(faceDirection)) {
                setLayer(layer);
                setVertColorRGBA();
                setVertUV();
                drawQuad();
            }
        }
        setFaceDirection(NORTH);
        if (shouldSideBeRendered()) {
            setVertPos();
            setMixedBrightnessTop();
            setVertBrightness();
            setAmbientOcclusionLightTop();
            setVertAmbientOcclusionFactors();
            for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(faceDirection)) {
                setLayer(layer);
                setVertColorRGBA();
                setVertUV();
                drawQuad();
            }
        }
        setFaceDirection(SOUTH);
        if (shouldSideBeRendered()) {
            setVertPos();
            setMixedBrightnessTop();
            setVertBrightness();
            setAmbientOcclusionLightTop();
            setVertAmbientOcclusionFactors();
            for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(faceDirection)) {
                setLayer(layer);
                setVertColorRGBA();
                setVertUV();
                drawQuad();
            }
        }
        setFaceDirection(WEST);
        if (shouldSideBeRendered()) {
            setVertPos();
            setMixedBrightnessTop();
            setVertBrightness();
            setAmbientOcclusionLightTop();
            setVertAmbientOcclusionFactors();
            for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(faceDirection)) {
                setLayer(layer);
                setVertColorRGBA();
                setVertUV();
                drawQuad();
            }
        }
        setFaceDirection(EAST);
        if (shouldSideBeRendered()) {
            setVertPos();
            setMixedBrightnessTop();
            setVertBrightness();
            setAmbientOcclusionLightTop();
            setVertAmbientOcclusionFactors();
            for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(faceDirection)) {
                setLayer(layer);
                setVertColorRGBA();
                setVertUV();
                drawQuad();
            }
        }
    }

    private void setFaceDirection(ForgeDirection side) {
        faceDirection = side;
    }

    private boolean shouldSideBeRendered() {
        return block.shouldSideBeRendered(blockAccess,
                (int) posXYZ.x() + faceDirection.offsetX,
                (int) posXYZ.y() + faceDirection.offsetY,
                (int) posXYZ.z() + faceDirection.offsetZ, faceDirection.ordinal());
    }

    private void setVertPos() {
        for (int i = 0; i < vertPos.length; i++) {
            vertPos[i].set(
                    bounds.getPos(vertPosMatrix[faceDirection.ordinal()][i][0]),
                    bounds.getPos(vertPosMatrix[faceDirection.ordinal()][i][1]),
                    bounds.getPos(vertPosMatrix[faceDirection.ordinal()][i][2]))
                    .add(posXYZ);
        }
    }

    private void setLayer(BlendableIcon layer) {
        this.layer = layer;
    }

    private void setVertUV() {
        val face = faceDirection.ordinal();
        for (int i = 0; i < vertUV.length; i++) {
            if (layer.doStretch()) {
                vertUV[i] = new PosUV(
                        CuboidBounds.CUBE_BOUNDS.getPos(vertUVMatrix[face][i][0]),
                        bounds.getPos(vertUVMatrix[face][i][1]));
            } else {
                vertUV[i] = new PosUV(
                        bounds.getPos(vertUVMatrix[face][i][0]),
                        bounds.getPos(vertUVMatrix[face][i][1]));
            }
        }
    }

    private void setMixedBrightnessTop() {
        if (bounds.max().y() > 1.0D || isOpaqueCube(0, 1, 0) && ambientOcclusionEnabled) {
            mixedBrightnessABCD = mixedBrightnessSelf;
        } else {
            mixedBrightnessABCD = getMixedBrightnessForBlock(0, 1, 0);
        }

        mixedBrightnessAB = getMixedBrightnessForBlock(0, 1, 1);
        mixedBrightnessBC = getMixedBrightnessForBlock(1, 1, 0);
        mixedBrightnessCD = getMixedBrightnessForBlock(0, 1, -1);
        mixedBrightnessAD = getMixedBrightnessForBlock(-1, 1, 0);

        mixedBrightnessA = getMixedBrightnessForBlock(-1, 1, 1);
        mixedBrightnessB = getMixedBrightnessForBlock(1, 1, 1);
        mixedBrightnessC = getMixedBrightnessForBlock(1, 1, -1);
        mixedBrightnessD = getMixedBrightnessForBlock(-1, 1, -1);
    }

    private boolean isOpaqueCube(int posXOffset, int posYOffset, int posZOffset) {
        return getBlockWithOffset(posXOffset, posYOffset, posZOffset).isOpaqueCube();
    }

    private int getMixedBrightnessForBlock(int posXOffset, int posYOffset, int posZOffset) {
        return block.getMixedBrightnessForBlock(blockAccess,
                (int) (posXYZ.x() + posXOffset),
                (int) (posXYZ.y() + posYOffset),
                (int) (posXYZ.z() + posZOffset));
    }

    private Block getBlockWithOffset(int posXOffset, int posYOffset, int posZOffset) {
        return blockAccess.getBlock(
                (int) (posXYZ.x() + posXOffset),
                (int) (posXYZ.y() + posYOffset),
                (int) (posXYZ.z() + posZOffset));
    }

    private void setVertBrightness() {
        vertABrightness = mixBrightness(mixedBrightnessA, mixedBrightnessAB, mixedBrightnessAD, mixedBrightnessABCD);
        vertBBrightness = mixBrightness(mixedBrightnessB, mixedBrightnessAB, mixedBrightnessBC, mixedBrightnessABCD);
        vertCBrightness = mixBrightness(mixedBrightnessC, mixedBrightnessBC, mixedBrightnessCD, mixedBrightnessABCD);
        vertDBrightness = mixBrightness(mixedBrightnessD, mixedBrightnessCD, mixedBrightnessAD, mixedBrightnessABCD);
    }

    public static int mixBrightness(int vert0Brightness, int vert1Brightness, int vert2Brightness, int quadBrightness) {
        if (vert0Brightness == 0)
            vert0Brightness = quadBrightness;
        if (vert1Brightness == 0)
            vert1Brightness = quadBrightness;
        if (vert2Brightness == 0)
            vert2Brightness = quadBrightness;
        return (vert0Brightness + vert1Brightness + vert2Brightness + quadBrightness) / 4 & 0xFF00FF;
    }

    private void setAmbientOcclusionLightTop() {
        if (!ambientOcclusionEnabled) return;

        ambientOcclusionLightABCD = AmbientOcclusionLight(0, 1, 0);

        ambientOcclusionLightAB = AmbientOcclusionLight(0, 1, 1);
        ambientOcclusionLightBC = AmbientOcclusionLight(1, 1, 0);
        ambientOcclusionLightCD = AmbientOcclusionLight(0, 1, -1);
        ambientOcclusionLightAD = AmbientOcclusionLight(-1, 1, 0);

        ambientOcclusionLightA = AmbientOcclusionLight(-1, 1, 1);
        ambientOcclusionLightB = AmbientOcclusionLight(1, 1, 1);
        ambientOcclusionLightC = AmbientOcclusionLight(1, 1, -1);
        ambientOcclusionLightD = AmbientOcclusionLight(-1, 1, -1);
    }

    private float AmbientOcclusionLight(int posXOffset, int posYOffset, int posZOffset) {
        return getBlockWithOffset(posXOffset, posYOffset, posZOffset).getAmbientOcclusionLightValue();
    }

    private void setVertAmbientOcclusionFactors() {
        if (!ambientOcclusionEnabled) return;

        vertAAmbientOcclusionFactor = (ambientOcclusionLightA + ambientOcclusionLightAB + ambientOcclusionLightAD
                + ambientOcclusionLightABCD) / 4.0F;
        vertBAmbientOcclusionFactor = (ambientOcclusionLightB + ambientOcclusionLightAB + ambientOcclusionLightBC
                + ambientOcclusionLightABCD) / 4.0F;
        vertCAmbientOcclusionFactor = (ambientOcclusionLightC + ambientOcclusionLightBC + ambientOcclusionLightCD
                + ambientOcclusionLightABCD) / 4.0F;
        vertDAmbientOcclusionFactor = (ambientOcclusionLightD + ambientOcclusionLightCD + ambientOcclusionLightAD
                + ambientOcclusionLightABCD) / 4.0F;
    }

    private void setVertColorRGBA() {
        vertAColorRGBA = layer.colorRGBA();
        vertBColorRGBA = layer.colorRGBA();
        vertCColorRGBA = layer.colorRGBA();
        vertDColorRGBA = layer.colorRGBA();
        if (!ambientOcclusionEnabled) return;

        vertAColorRGBA = vertAColorRGBA.mult(vertAAmbientOcclusionFactor);
        vertBColorRGBA = vertBColorRGBA.mult(vertBAmbientOcclusionFactor);
        vertCColorRGBA = vertCColorRGBA.mult(vertCAmbientOcclusionFactor);
        vertDColorRGBA = vertDColorRGBA.mult(vertDAmbientOcclusionFactor);
    }

    private void drawQuad() {
        //TODO Reset the tesselator lighting and colour each time
        StraightGLUtil.drawAndUnDraw(layer.noDraw());
        //layer.applyBlending(layer.noDraw());
        Quad.quadBuilder()
                .vertA(new Vertex(vertPos[0], new PosUV(layer, vertUV[0], layer.rotation()), vertABrightness, vertAColorRGBA, faceDirection))
                .vertB(new Vertex(vertPos[1], new PosUV(layer, vertUV[1], layer.rotation()), vertBBrightness, vertBColorRGBA, faceDirection))
                .vertC(new Vertex(vertPos[2], new PosUV(layer, vertUV[2], layer.rotation()), vertCBrightness, vertCColorRGBA, faceDirection))
                .vertD(new Vertex(vertPos[3], new PosUV(layer, vertUV[3], layer.rotation()), vertDBrightness, vertDColorRGBA, faceDirection))
                .tessellate();
        StraightGLUtil.drawAndUnDraw(layer.noDraw());
        //StraightGLUtil.restoreDefaults(layer.noDraw(), layer.hasAlpha());
    }
}
