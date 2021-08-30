package com.github.basdxz.tesrplay.newRender;

import com.github.basdxz.tesrplay.newRender.providers.CubeRenderProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import static com.github.basdxz.tesrplay.TESRPlayground.MODID;

//public class TestBlock extends Block implements ModelRenderProvider {
public class TestBlock extends Block implements CubeRenderProvider {
    public static IIcon RED_TINT;
    public static IIcon GREEN_TINT;
    public static IIcon BLUE_TINT;
    public static IIcon BLUE_FADE;
    public static IIcon TEST_PATTERN;
    public static IIcon BOTTOM_GLASS;

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
        this.blockIcon = TEST_PATTERN;
    }

    @Override
    public int getRenderBlockPass() {
        return 1; //alpha
    }

    //TODO make getIcon reference to texture
    //@Override
    //public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
    //    return super.getIcon(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_, p_149673_5_);
    //}

    //@Override
    //public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
    //    return super.getIcon(p_149691_1_, p_149691_2_);
    //}


    @Override
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return renderType;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }


    //@Override
    //public Model getModel() {
    //    return Models.PRISM;
    //}
}
