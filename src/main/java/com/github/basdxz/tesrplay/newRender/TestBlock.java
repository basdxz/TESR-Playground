package com.github.basdxz.tesrplay.newRender;

import com.github.basdxz.tesrplay.newRender.model.Model;
import com.github.basdxz.tesrplay.newRender.providers.CubeRenderProvider;
import com.github.basdxz.tesrplay.newRender.model.Models;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

//public class TestBlock extends Block implements ModelRenderProvider {
public class TestBlock extends Block implements CubeRenderProvider {
    private final int renderType;

    public TestBlock(int renderType) {
        super(Material.rock);
        this.renderType = renderType;
        setBlockName("TestBlock");
    }

    //TODO make getIcon reference to texture
    //@Override
    //public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
    //    return super.getIcon(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_, p_149673_5_);
    //}

    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return super.getIcon(p_149691_1_, p_149691_2_);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return renderType;
    }

    //@Override
    //public Model getModel() {
    //    return Models.PRISM;
    //}
}
