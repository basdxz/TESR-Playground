package com.github.basdxz.tesrplay.newRender;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendEquations;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendFuncs;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.LayeredIcon;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.MaterialTexture;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.SameSideAllAround;
import com.github.basdxz.tesrplay.newRender.providers.CuboidRenderProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

import static com.github.basdxz.tesrplay.TESRPlayground.MODID;

//public class TestBlock extends Block implements ModelRenderProvider {
public class TestBlock extends Block implements CuboidRenderProvider {
    public static IIcon RED_TINT;
    public static IIcon GREEN_TINT;
    public static IIcon BLUE_TINT;
    public static IIcon BLUE_FADE;
    public static IIcon TEST_PATTERN;
    public static IIcon BOTTOM_GLASS;
    public static IIcon DARK_RED;
    public static IIcon TEST_2;

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

    //Epic
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int posX, int posY, int posZ, int side) {
        Block block = blockAccess.getBlock(posX, posY, posZ);
        if (block == this) return false;
        return super.shouldSideBeRendered(blockAccess, posX, posY, posZ, side);
    }

    //FIXME Start here.
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    //@Override
    //public int getRenderType() {
    //    return renderType;
    //}

    @Override
    public Block getBlock() {
        return this;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public LayeredIcon getTextureLayers() {
        Random rand = new Random();

        return new SameSideAllAround(MaterialTexture.builder()
                .icon(RED_TINT)
                //.glBlendEquation(GLBlendEquations.REVERSE_SUBTRACT)
                //.glBlendFunc(GLBlendFuncs.ALPHA)
                .hasAlpha(true)
                //.noDraw(true)
                .rotation(rand.nextDouble() * Math.PI)
                .build());
    }

    //@Override
    //public Model getModel() {
    //    return Models.PRISM;
    //}
}
