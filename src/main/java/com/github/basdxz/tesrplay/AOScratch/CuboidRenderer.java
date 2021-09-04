package com.github.basdxz.tesrplay.AOScratch;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.Quad;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.*;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import static net.minecraftforge.common.util.ForgeDirection.UP;


//TODO: Relocate
public class CuboidRenderer {
    private final IBlockAccess blockAccess;
    private final Block block;
    private final PosXYZ posXYZ;
    private final CuboidBounds bounds;
    private final LayeredIcon layeredIcon;

    private PosXYZ vertAPos;
    private PosXYZ vertBPos;
    private PosXYZ vertCPos;
    private PosXYZ vertDPos;
    private boolean isQuadFlipped;
    private BlendableIcon layer;
    private PosUV vertAUV;
    private PosUV vertBUV;
    private PosUV vertCUV;
    private PosUV vertDUV;

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

    private ColorRGBA vertAColorRGBA;
    private ColorRGBA vertBColorRGBA;
    private ColorRGBA vertCColorRGBA;
    private ColorRGBA vertDColorRGBA;

    public CuboidRenderer(Block block, PosXYZ posXYZ, CuboidBounds bounds, LayeredIcon layeredIcon) {
        blockAccess = getWorldAccess();
        this.block = block;
        this.posXYZ = posXYZ;
        this.bounds = bounds;
        this.layeredIcon = layeredIcon;
    }

    private static IBlockAccess getWorldAccess() {
        return Minecraft.getMinecraft().theWorld;
    }

    /*
        -Brightness without AO
        -Normal Map tesselator setting
        -Tinting per layer
        -Remove the isQuadFlipped variable
        -Verify UV scaling still works
        -AO on the other 5 sides
        -Swap ColorRGBA to a Java color object
        -Swab PosXYZ to a java Pos3
     */
    public void render() {
        //if (shouldSideBeRendered(DOWN)) {
        //    setVertXYZDown();
        //    offsetVertXYZWithPosXYZ();
        //    for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(DOWN)) {
        //        setLayer(layer);
        //        setVertUVDown();
        //        drawQuad();
        //    }
        //}
        if (shouldSideBeRendered(UP)) {
            setVertXYZUp();
            offsetVertXYZWithPosXYZ();
            setMixedBrightnessTop();
            setVertBrightness();
            setAmbientOcclusionLightTop();
            setVertAmbientOcclusionFactors();
            for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(UP)) {
                setLayer(layer);
                setVertColorRGBAWithAmbientOcclusion();
                setVertUVUp();
                drawQuad();
            }
        }
        //if (shouldSideBeRendered(NORTH)) {
        //    setVertXYZNorth();
        //    offsetVertXYZWithPosXYZ();
        //    for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(NORTH)) {
        //        setLayer(layer);
        //        setVertUVNorth();
        //        drawQuad();
        //    }
        //}
        //if (shouldSideBeRendered(SOUTH)) {
        //    setVertXYZSouth();
        //    offsetVertXYZWithPosXYZ();
        //    for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(SOUTH)) {
        //        setLayer(layer);
        //        setVertUVSouth();
        //        drawQuad();
        //    }
        //}
        //if (shouldSideBeRendered(WEST)) {
        //    setVertXYZWest();
        //    offsetVertXYZWithPosXYZ();
        //    for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(WEST)) {
        //        setLayer(layer);
        //        setVertUVWest();
        //        drawQuad();
        //    }
        //}
        //if (shouldSideBeRendered(EAST)) {
        //    setVertXYZEast();
        //    offsetVertXYZWithPosXYZ();
        //    for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(EAST)) {
        //        setLayer(layer);
        //        setVertUVEast();
        //        drawQuad();
        //    }
        //}
    }

    private boolean shouldSideBeRendered(ForgeDirection side) {
        return block.shouldSideBeRendered(blockAccess,
                (int) posXYZ.x() + side.offsetX,
                (int) posXYZ.y() + side.offsetY,
                (int) posXYZ.z() + side.offsetZ, side.ordinal());
    }

    private void setVertXYZDown() {
        vertAPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
        vertBPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
        vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
        vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
        isQuadFlipped = false;
    }

    private void setVertXYZUp() {
        vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.max().z());
        vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
        vertCPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
        vertDPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
        isQuadFlipped = false;
    }

    private void setVertXYZNorth() {
        vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
        vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
        vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
        vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
        isQuadFlipped = false;
    }

    private void setVertXYZSouth() {
        vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.max().z());
        vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
        vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
        vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
        isQuadFlipped = true;
    }

    private void setVertXYZWest() {
        vertAPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.min().z());
        vertBPos = new PosXYZ(bounds.min().x(), bounds.max().y(), bounds.max().z());
        vertCPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.max().z());
        vertDPos = new PosXYZ(bounds.min().x(), bounds.min().y(), bounds.min().z());
        isQuadFlipped = true;
    }

    private void setVertXYZEast() {
        vertAPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.min().z());
        vertBPos = new PosXYZ(bounds.max().x(), bounds.max().y(), bounds.max().z());
        vertCPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.max().z());
        vertDPos = new PosXYZ(bounds.max().x(), bounds.min().y(), bounds.min().z());
        isQuadFlipped = false;
    }

    private void offsetVertXYZWithPosXYZ() {
        vertAPos = vertAPos.add(posXYZ);
        vertBPos = vertBPos.add(posXYZ);
        vertCPos = vertCPos.add(posXYZ);
        vertDPos = vertDPos.add(posXYZ);
    }

    private void setLayer(BlendableIcon layer) {
        this.layer = layer;
    }

    private void setVertUVDown() {
        if (layer.doStretch()) {
            vertAUV = new PosUV(0, 0);
            vertBUV = new PosUV(1, 0);
            vertCUV = new PosUV(1, 1);
            vertDUV = new PosUV(0, 1);
        } else {
            vertAUV = new PosUV(bounds.min().x(), bounds.min().z());
            vertBUV = new PosUV(bounds.max().x(), bounds.min().z());
            vertCUV = new PosUV(bounds.max().x(), bounds.max().z());
            vertDUV = new PosUV(bounds.min().x(), bounds.max().z());
        }
    }

    private void setVertUVUp() {
        vertAUV = new PosUV(0, 1);
        vertBUV = new PosUV(1, 1);
        vertCUV = new PosUV(1, 0);
        vertDUV = new PosUV(0, 0);
        //if (layer.doStretch()) {
        //    vertAUV = new PosUV(0, 0);
        //    vertBUV = new PosUV(1, 0);
        //    vertCUV = new PosUV(1, 1);
        //    vertDUV = new PosUV(0, 1);
        //} else {
        //    vertAUV = new PosUV(bounds.min().x(), bounds.min().z());
        //    vertBUV = new PosUV(bounds.max().x(), bounds.min().z());
        //    vertCUV = new PosUV(bounds.max().x(), bounds.max().z());
        //    vertDUV = new PosUV(bounds.min().x(), bounds.max().z());
        //}
    }

    private void setVertUVNorth() {
        if (layer.doStretch()) {
            vertAUV = new PosUV(1, 0);
            vertBUV = new PosUV(0, 0);
            vertCUV = new PosUV(0, 1);
            vertDUV = new PosUV(1, 1);
        } else {
            vertAUV = new PosUV(1 - bounds.min().x(), 1 - bounds.max().y());
            vertBUV = new PosUV(1 - bounds.max().x(), 1 - bounds.max().y());
            vertCUV = new PosUV(1 - bounds.max().x(), 1 - bounds.min().y());
            vertDUV = new PosUV(1 - bounds.min().x(), 1 - bounds.min().y());
        }
    }

    private void setVertUVSouth() {
        if (layer.doStretch()) {
            vertAUV = new PosUV(0, 0);
            vertBUV = new PosUV(1, 0);
            vertCUV = new PosUV(1, 1);
            vertDUV = new PosUV(0, 1);
        } else {
            vertAUV = new PosUV(bounds.min().x(), 1 - bounds.max().y());
            vertBUV = new PosUV(bounds.max().x(), 1 - bounds.max().y());
            vertCUV = new PosUV(bounds.max().x(), 1 - bounds.min().y());
            vertDUV = new PosUV(bounds.min().x(), 1 - bounds.min().y());
        }
    }

    private void setVertUVWest() {
        if (layer.doStretch()) {
            vertAUV = new PosUV(0, 0);
            vertBUV = new PosUV(1, 0);
            vertCUV = new PosUV(1, 1);
            vertDUV = new PosUV(0, 1);
        } else {
            vertDUV = new PosUV(bounds.min().z(), 1 - bounds.min().y());
            vertCUV = new PosUV(bounds.max().z(), 1 - bounds.min().y());
            vertBUV = new PosUV(bounds.max().z(), 1 - bounds.max().y());
            vertAUV = new PosUV(bounds.min().z(), 1 - bounds.max().y());
        }
    }

    private void setVertUVEast() {
        if (layer.doStretch()) {
            vertAUV = new PosUV(1, 0);
            vertBUV = new PosUV(0, 0);
            vertCUV = new PosUV(0, 1);
            vertDUV = new PosUV(1, 1);
        } else {
            vertCUV = new PosUV(1 - bounds.max().z(), 1 - bounds.min().y());
            vertDUV = new PosUV(1 - bounds.min().z(), 1 - bounds.min().y());
            vertAUV = new PosUV(1 - bounds.min().z(), 1 - bounds.max().y());
            vertBUV = new PosUV(1 - bounds.max().z(), 1 - bounds.max().y());
        }
    }

    private void setMixedBrightnessTop() {
        if (isOpaqueCube(0, 1, 0)) {
            mixedBrightnessABCD = getMixedBrightnessForBlock(0, 0, 0);
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
        vertABrightness = getBrightness(mixedBrightnessA, mixedBrightnessAB, mixedBrightnessAD, mixedBrightnessABCD);
        vertBBrightness = getBrightness(mixedBrightnessB, mixedBrightnessAB, mixedBrightnessBC, mixedBrightnessABCD);
        vertCBrightness = getBrightness(mixedBrightnessC, mixedBrightnessBC, mixedBrightnessCD, mixedBrightnessABCD);
        vertDBrightness = getBrightness(mixedBrightnessD, mixedBrightnessCD, mixedBrightnessAD, mixedBrightnessABCD);
    }

    //todo name stuff better
    public static int getBrightness(int ao1, int ao2, int ao3, int aoMin) {
        if (ao1 == 0)
            ao1 = aoMin;
        if (ao2 == 0)
            ao2 = aoMin;
        if (ao3 == 0)
            ao3 = aoMin;
        return (ao1 + ao2 + ao3 + aoMin) / 4 & 0b111111110000000011111111;
    }

    private void setAmbientOcclusionLightTop() {
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
        vertAAmbientOcclusionFactor = (ambientOcclusionLightA + ambientOcclusionLightAB + ambientOcclusionLightAD
                + ambientOcclusionLightABCD) / 4.0F;
        vertBAmbientOcclusionFactor = (ambientOcclusionLightB + ambientOcclusionLightAB + ambientOcclusionLightBC
                + ambientOcclusionLightABCD) / 4.0F;
        vertCAmbientOcclusionFactor = (ambientOcclusionLightC + ambientOcclusionLightBC + ambientOcclusionLightCD
                + ambientOcclusionLightABCD) / 4.0F;
        vertDAmbientOcclusionFactor = (ambientOcclusionLightD + ambientOcclusionLightCD + ambientOcclusionLightAD
                + ambientOcclusionLightABCD) / 4.0F;
    }

    private void setVertColorRGBAWithAmbientOcclusion() {
        ColorRGBA colore = new ColorRGBA(1.0F, 1.0F, 1.0F, 1.0F);
        vertAColorRGBA = colore.mult(vertAAmbientOcclusionFactor);
        vertBColorRGBA = colore.mult(vertBAmbientOcclusionFactor);
        vertCColorRGBA = colore.mult(vertCAmbientOcclusionFactor);
        vertDColorRGBA = colore.mult(vertDAmbientOcclusionFactor);
    }

    private void drawQuad() {
        //TODO Reset the tesselator lighting and colour each time
        //StraightGLUtil.drawAndUnDraw(layer.noDraw());
        //layer.applyBlending(layer.noDraw());
        Quad.quadBuilder()
                .vertA(new Vertex(vertAPos, new PosUV(layer, vertAUV, layer.rotation()), vertABrightness, vertAColorRGBA))
                .vertB(new Vertex(vertBPos, new PosUV(layer, vertBUV, layer.rotation()), vertBBrightness, vertBColorRGBA))
                .vertC(new Vertex(vertCPos, new PosUV(layer, vertCUV, layer.rotation()), vertCBrightness, vertCColorRGBA))
                .vertD(new Vertex(vertDPos, new PosUV(layer, vertDUV, layer.rotation()), vertDBrightness, vertDColorRGBA))
                .reversed(isQuadFlipped)
                .tessellate();
        //StraightGLUtil.drawAndUnDraw(layer.noDraw());
        //StraightGLUtil.restoreDefaults(layer.noDraw(), layer.hasAlpha());
    }
}
