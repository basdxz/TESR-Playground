package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.experimental.UtilityClass;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

import static net.minecraftforge.common.util.ForgeDirection.VALID_DIRECTIONS;

//TODO Implement safe rendering
@UtilityClass
public class CuboidRenderer {
    private final Vertex.Vertex4D quadVertices = new Vertex.Vertex4D();
    private final PosXYZ posXYZ = new PosXYZ();

    private IBlockAccess blockAccess;
    private Block block;
    private LayeredIcon layeredIcon;
    private boolean renderingAsItem;

    public void renderWorldBlock(IBlockAccess world, int posX, int posY, int posZ,
                                 Block block, CuboidBounds bounds, LayeredIcon layeredIcon) {
        initWorldBlock(world, posX, posY, posZ, block, bounds, layeredIcon);
        render();
    }

    public void renderInventoryBlock(Block block, CuboidBounds bounds, LayeredIcon layeredIcon) {
        initInventoryBlock(block, bounds, layeredIcon);
        //Tessellator.instance.pauseDraw();
        Tessellator.instance.startDrawingQuads();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        GL11.glTranslated(0.5F, 0.0F, 0.5F);
        GL11.glRotated(90, 0.0F, 1.0F, 0.0F);
        GL11.glTranslated(-0.5F, 0.0F, -0.5F);
        render();
        Tessellator.instance.draw();
        //Tessellator.instance.resumeDraw();
    }

    private void initWorldBlock(IBlockAccess world, int posX, int posY, int posZ,
                                Block block, CuboidBounds bounds, LayeredIcon layeredIcon) {
        renderingAsItem = false;
        initBlock(world, posX, posY, posZ, block, bounds, layeredIcon);
    }

    private void initInventoryBlock(Block block, CuboidBounds bounds, LayeredIcon layeredIcon) {
        renderingAsItem = true;
        initBlock(null, 0, 0, 0, block, bounds, layeredIcon);
    }

    private void initBlock(IBlockAccess blockAccess, int posX, int posY, int posZ, Block block, CuboidBounds bounds,
                           LayeredIcon layeredIcon) {
        CuboidRenderer.blockAccess = blockAccess;
        posXYZ.set(posX, posY, posZ);
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
        return !(renderingAsItem || block.shouldSideBeRendered(blockAccess,
                (int) posXYZ.x() + direction.offsetX,
                (int) posXYZ.y() + direction.offsetY,
                (int) posXYZ.z() + direction.offsetZ, direction.ordinal()));
    }
}
