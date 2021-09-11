package com.github.basdxz.tesrplay.shipping;

import com.github.basdxz.tesrplay.newRender.cuboid.ColorRGBA;
import com.github.basdxz.tesrplay.newRender.cuboid.LayeredIcon;
import com.github.basdxz.tesrplay.newRender.cuboid.MaterialTexture;
import com.github.basdxz.tesrplay.newRender.cuboid.SameSideAllAround;
import com.github.basdxz.tesrplay.newRender.providers.CuboidRenderProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

import static com.github.basdxz.tesrplay.TESRPlayground.MODID;
import static com.github.basdxz.tesrplay.newRender.cuboid.ColorRGBA.NO_COLOR;

/*
    Creates 16 variants using the minecraft pallet.
    Transparent model using more realistic lensing with a set number of hues
    Has a second layer with a frame and connecting textures
 */
public class LaserGlass extends BaseBlock implements CuboidRenderProvider {
    public static IIcon GLASS_FRAME;

    public LaserGlass(int renderType) {
        super(Material.glass, renderType);
        setBlockName("LaserGlass");
        setBlockTextureName(MODID + ":laser_glass");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        GLASS_FRAME = iconRegister.registerIcon(MODID + ":laser_glass_frame");
        super.registerBlockIcons(iconRegister);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    //This is for glass stuff
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int posX, int posY, int posZ, int side) {
        Block block = blockAccess.getBlock(posX, posY, posZ);
        if (block == this) return false;
        return super.shouldSideBeRendered(blockAccess, posX, posY, posZ, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube() {
        return false;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public LayeredIcon getTextureLayers(int metadata) {
        Random rand = new Random();

        return new SameSideAllAround(
                MaterialTexture.builder()
                        .icon(getIcon(0, 0))
                        .colorRGBA(new ColorRGBA(1.0F, 0.0F, 0.0F, 1.0F))
                        .build(),
                MaterialTexture.builder()
                        .icon(GLASS_FRAME)
                        .colorRGBA(NO_COLOR)
                        .build());
    }
}
