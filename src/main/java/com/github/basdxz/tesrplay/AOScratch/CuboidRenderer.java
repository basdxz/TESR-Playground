package com.github.basdxz.tesrplay.AOScratch;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.*;
import lombok.experimental.UtilityClass;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import static net.minecraftforge.common.util.ForgeDirection.VALID_DIRECTIONS;

//TODO: Relocate
@UtilityClass
public class CuboidRenderer {
    private final Vertex.Vertex4D quadVertices = new Vertex.Vertex4D();

    private Block block;
    private CuboidBounds bounds;
    private PosXYZ posXYZ;
    private LayeredIcon layeredIcon;
    private IBlockAccess blockAccess;

    public void render(Block block, PosXYZ posXYZ, CuboidBounds bounds, LayeredIcon layeredIcon) {
        init(block, posXYZ, bounds, layeredIcon);
        for (ForgeDirection direction : VALID_DIRECTIONS) {
            if (shouldSideBeSkipped(direction)) continue;
            quadVertices.preRender(direction);
            for (BlendableIcon layer : CuboidRenderer.layeredIcon.getBlendableIconLayers(direction))
                quadVertices.render(layer);
        }
    }

    private void init(Block aBlock, PosXYZ aPosXYZ, CuboidBounds aBounds, LayeredIcon aLayeredIcon) {
        block = aBlock;
        bounds = aBounds;
        posXYZ = aPosXYZ;
        layeredIcon = aLayeredIcon;
        blockAccess = getWorldAccess();
        setupQuadVertices();
    }

    private IBlockAccess getWorldAccess() {
        return Minecraft.getMinecraft().theWorld;
    }

    private void setupQuadVertices() {
        quadVertices.setBlock(blockAccess, block, bounds, posXYZ);
    }

    private boolean shouldSideBeSkipped(ForgeDirection direction) {
        return !block.shouldSideBeRendered(blockAccess,
                (int) posXYZ.x() + direction.offsetX,
                (int) posXYZ.y() + direction.offsetY,
                (int) posXYZ.z() + direction.offsetZ, direction.ordinal());
    }
}
