package com.github.basdxz.tesrplay.shipping;

import com.github.basdxz.tesrplay.newRender.commonGL.instance.GLBlendEquations;
import com.github.basdxz.tesrplay.newRender.commonGL.instance.GLBlendFuncs;
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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;
import java.util.stream.IntStream;

import static com.github.basdxz.tesrplay.TESRPlayground.MODID;

/*
    Creates 16 variants using the minecraft pallet.
    Transparent model using more realistic lensing with a set number of hues
    Has a second layer with a frame and connecting textures
 */
public class LaserGlass extends BaseBlock implements CuboidRenderProvider {
    public static IIcon GLASS_FRAME;

    public LaserGlass(int renderType) {
        super(Material.glass, renderType);
        setBlockName(MODID + ".laser_glass");
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
        return new SameSideAllAround(
                MaterialTexture.builder()
                        .icon(getIcon(0, 0))
                        .colorRGBA(ColorRGBA.Colors.values()[metadata].getColor())
                        .blendingFunction(() -> {
                            GLBlendEquations.REVERSE_SUBTRACT.apply();
                            GLBlendFuncs.ALPHA.apply();
                        })
                        .build());
        //MaterialTexture.builder()
        //        .icon(GLASS_FRAME)
        //        .colorRGBA(NO_COLOR)
        //        .build()
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item items, CreativeTabs creativeTabs, List list) {
        IntStream.range(0, ColorRGBA.Colors.values().length).mapToObj(
                i -> new ItemStack(items, 1, i)).forEach(list::add);
    }

    public int damageDropped(int p_149692_1_) {
        return p_149692_1_;
    }
}
