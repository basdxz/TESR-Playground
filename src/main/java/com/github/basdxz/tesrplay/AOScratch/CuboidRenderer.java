package com.github.basdxz.tesrplay.AOScratch;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import static net.minecraftforge.common.util.ForgeDirection.VALID_DIRECTIONS;

//TODO: Relocate and make static
public class CuboidRenderer {
    private final Block block;
    private final CuboidBounds bounds;
    private final PosXYZ posXYZ;
    private final LayeredIcon layeredIcon;
    private final IBlockAccess blockAccess;

    private final Vertex.Vertex4D quadVertices = new Vertex.Vertex4D();

    public CuboidRenderer(Block block, PosXYZ posXYZ, CuboidBounds bounds, LayeredIcon layeredIcon) {
        this.block = block;
        this.bounds = bounds;
        this.posXYZ = posXYZ;
        this.layeredIcon = layeredIcon;
        blockAccess = getWorldAccess();
        setupQuadVertices();
    }

    private static IBlockAccess getWorldAccess() {
        return Minecraft.getMinecraft().theWorld;
    }

    private void setupQuadVertices() {
        quadVertices.setBlock(blockAccess, block, bounds, posXYZ);
    }

    /*
        -Scale Normal Facing by bounds
        -Flipped textures on the bottom (vanilla render mimic) check in 1.16 if issue still exists
     */
    public void render() {
        for (ForgeDirection direction : VALID_DIRECTIONS) {
            if (shouldSideBeSkipped(direction)) continue;
            quadVertices.preRender(direction);
            for (BlendableIcon layer : layeredIcon.getBlendableIconLayers(direction))
                quadVertices.render(layer);
        }
    }

    private boolean shouldSideBeSkipped(ForgeDirection direction) {
        return !block.shouldSideBeRendered(blockAccess,
                (int) posXYZ.x() + direction.offsetX,
                (int) posXYZ.y() + direction.offsetY,
                (int) posXYZ.z() + direction.offsetZ, direction.ordinal());
    }
}
