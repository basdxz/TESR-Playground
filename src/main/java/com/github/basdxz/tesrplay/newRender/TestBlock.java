package com.github.basdxz.tesrplay.newRender;

import com.github.basdxz.tesrplay.newRender.Model;
import com.github.basdxz.tesrplay.newRender.Models;
import com.github.basdxz.tesrplay.newRender.interfacesForUsers.ModelRenderProvider;
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
