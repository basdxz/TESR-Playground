package com.github.basdxz.tesrplay.shipping;

import com.github.basdxz.tesrplay.newRender.cuboid.*;
import com.github.basdxz.tesrplay.newRender.providers.CuboidRenderProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import static com.github.basdxz.tesrplay.TESRPlayground.MODID;

public class TestBlock extends Block implements CuboidRenderProvider {
    public static IIcon RED_TINT;
    public static IIcon GREEN_TINT;
    public static IIcon BLUE_TINT;
    public static IIcon BLUE_FADE;
    public static IIcon TEST_PATTERN;
    public static IIcon BOTTOM_GLASS;
    public static IIcon DARK_RED;
    public static IIcon TEST_2;
    public static IIcon TEST_3;

    private final int renderType;

    public TestBlock(int renderType) {
        super(Material.rock);
        this.renderType = renderType;
        setBlockName("TestBlock");
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        RED_TINT = iconRegister.registerIcon(MODID + ":red");
        GREEN_TINT = iconRegister.registerIcon(MODID + ":green");
        BLUE_TINT = iconRegister.registerIcon(MODID + ":blue");
        BLUE_FADE = iconRegister.registerIcon(MODID + ":blue_fade");
        TEST_PATTERN = iconRegister.registerIcon(MODID + ":pattern");
        BOTTOM_GLASS = iconRegister.registerIcon(MODID + ":bottom_glass");
        DARK_RED = iconRegister.registerIcon(MODID + ":dark_red");
        TEST_2 = iconRegister.registerIcon(MODID + ":test_wonderful");
        TEST_3 = iconRegister.registerIcon(MODID + ":test_tasteful");
        this.blockIcon = BOTTOM_GLASS;
    }

    //This is for glass stuff
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int posX, int posY, int posZ, int side) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public int getRenderType() {
        return renderType;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }

    @Override
    public LayeredIcon getTextureLayers(int metadata) {
        return new SameSideAllAround(MaterialTexture.builder()
                .icon(Blocks.cobblestone.getIcon(0, 0))
                .colorRGBA(new ColorRGBA(1.0F, 1.0F, 1.0F, 1.0F))
                .build());
    }

    @Override
    public CuboidBounds getBounds() {
        return new CuboidBounds(new PosXYZ(-0.5, -0.5, -0.5), new PosXYZ(1.5, 1.5, 1.5));
    }
}
