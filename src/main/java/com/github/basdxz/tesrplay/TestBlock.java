package com.github.basdxz.tesrplay;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TestBlock extends Block {
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
}
