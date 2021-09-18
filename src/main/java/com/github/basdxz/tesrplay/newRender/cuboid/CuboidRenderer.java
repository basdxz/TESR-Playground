package com.github.basdxz.tesrplay.newRender.cuboid;

import lombok.experimental.UtilityClass;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

import static com.github.basdxz.tesrplay.newRender.commonGL.GLUtils.*;
import static net.minecraftforge.common.util.ForgeDirection.VALID_DIRECTIONS;

//TODO Implement safe rendering
//TODO Copy the pattern from model renderer w.r.t. builders
@UtilityClass
public class CuboidRenderer {
    private final Vertex.Vertex4D quadVertices = new Vertex.Vertex4D();
    private final PosXYZ posXYZ = new PosXYZ();

    private IBlockAccess blockAccess;
    private Block block;
    private LayeredIcon layeredIcon;
    private boolean renderingInventoryBlock;

    public void renderWorldBlock(IBlockAccess world, int posX, int posY, int posZ,
                                 Block block, CuboidBounds bounds, LayeredIcon layeredIcon) {
        initWorldBlock(world, posX, posY, posZ, block, bounds, layeredIcon);
        render();
    }

    public void renderInventoryBlock(Block block, CuboidBounds bounds, LayeredIcon layeredIcon) {
        initInventoryBlock(block, bounds, layeredIcon);
        GL11.glPushMatrix();
        glColor();
        glTranslate(-0.5D);
        glYRotate(90D);
        render();
        GL11.glPopMatrix();
    }

    private void initWorldBlock(IBlockAccess world, int posX, int posY, int posZ,
                                Block block, CuboidBounds bounds, LayeredIcon layeredIcon) {
        renderingInventoryBlock = false;//todo should this even exist?
        initBlock(world, posX, posY, posZ, block, bounds, layeredIcon);
    }

    private void initInventoryBlock(Block block, CuboidBounds bounds, LayeredIcon layeredIcon) {
        renderingInventoryBlock = true;
        initBlock(null, 0, 0, 0, block, bounds, layeredIcon);
    }

    private void initBlock(IBlockAccess blockAccess, int posX, int posY, int posZ, Block block, CuboidBounds bounds,
                           LayeredIcon layeredIcon) {
        CuboidRenderer.blockAccess = blockAccess;
        CuboidRenderer.posXYZ.set(posX, posY, posZ);
        CuboidRenderer.block = block;
        CuboidRenderer.layeredIcon = layeredIcon;
        quadVertices.setBlock(blockAccess, block, bounds, posXYZ);
    }

    private void render() {
        for (ForgeDirection direction : VALID_DIRECTIONS) {
            if (shouldSideBeSkipped(direction)) continue;
            quadVertices.preRender(direction);
            for (BlendableIcon layer : CuboidRenderer.layeredIcon.getBlendableIconLayers(direction))
                quadVertices.render(layer);
        }
    }

    private boolean shouldSideBeSkipped(ForgeDirection direction) {
        return !(renderingInventoryBlock || block.shouldSideBeRendered(blockAccess,
                (int) posXYZ.x() + direction.offsetX,
                (int) posXYZ.y() + direction.offsetY,
                (int) posXYZ.z() + direction.offsetZ, direction.ordinal()));
    }
}
