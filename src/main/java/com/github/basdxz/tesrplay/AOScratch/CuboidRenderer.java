package com.github.basdxz.tesrplay.AOScratch;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import static net.minecraftforge.common.util.ForgeDirection.VALID_DIRECTIONS;

//TODO: Relocate
public class CuboidRenderer {
    private final Block block;
    private final PosXYZ posXYZ;
    private final CuboidBounds bounds;
    private final LayeredIcon layeredIcon;
    private final IBlockAccess blockAccess;
    private final boolean ambientOcclusionEnabled;

    private ForgeDirection faceDirection;
    private final Vertex.Vertex4D quadVertices = new Vertex.Vertex4D();


    public CuboidRenderer(Block block, PosXYZ posXYZ, CuboidBounds bounds, LayeredIcon layeredIcon) {
        this.block = block;
        this.posXYZ = posXYZ;
        this.bounds = bounds;
        this.layeredIcon = layeredIcon;
        blockAccess = getWorldAccess();
        ambientOcclusionEnabled = getAmbientOcclusionState();
        setupQuadVertices();
    }

    private static IBlockAccess getWorldAccess() {
        return Minecraft.getMinecraft().theWorld;
    }

    private static boolean getAmbientOcclusionState() {
        return Minecraft.isAmbientOcclusionEnabled();
    }

    //todo clean this
    private void setupQuadVertices() {
        quadVertices.setBounds(bounds);
        quadVertices.setPosXYZ(posXYZ);
        quadVertices.setBlock(block);
        quadVertices.setAmbientOcclusionEnabled(ambientOcclusionEnabled);
        quadVertices.setBlockAccess(blockAccess);
    }

    /*
        -AO on the other 5 sides
        -Scale Normal Facing by bounds
        -Tinting per layer with 0-1.0 tining, where 0 means none and 1.0 means maxed out.
     */
    public void render() {
        for (ForgeDirection direction : VALID_DIRECTIONS) {
            setFaceDirection(direction);
            if (!shouldSideBeRendered()) continue;
            setVertPosXYZ();
            setVertBrightness();
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
        quadVertices.setFace(side);
    }

    private boolean shouldSideBeRendered() {
        return block.shouldSideBeRendered(blockAccess,
                (int) posXYZ.x() + faceDirection.offsetX,
                (int) posXYZ.y() + faceDirection.offsetY,
                (int) posXYZ.z() + faceDirection.offsetZ, faceDirection.ordinal());
    }

    private void setVertPosXYZ() {
        quadVertices.setVertPosXYZ();
    }

    private void setVertBrightness() {
        quadVertices.setBrightness();
    }

    private void setLayer(BlendableIcon layer) {
        quadVertices.setIcon(layer);
    }

    private void setVertUV() {
        quadVertices.setColorRGBA();
    }

    private void setVertColorRGBA() {
        quadVertices.setVertPosUV();
    }

    private void drawQuad() {
        quadVertices.tessellate();
    }
}
