package com.github.basdxz.tesrplay.newRender;

import com.github.basdxz.tesrplay.newRender.model.Model;
import com.github.basdxz.tesrplay.newRender.providers.ModelRenderProvider;
import com.github.basdxz.tesrplay.newRender.model.Models;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TestBlock extends Block implements ModelRenderProvider {
    private final int renderType;

    public TestBlock(int renderType) {
        super(Material.rock);
        this.renderType = renderType;
        setBlockName("TestBlock");
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
    public Model getModel() {
        return Models.PRISM;
    }
}
