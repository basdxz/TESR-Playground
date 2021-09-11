package com.github.basdxz.tesrplay.shipping;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemCloth;

public abstract class BaseBlock extends Block {
    private final int renderType;

    public BaseBlock(Material material, int renderType) {
        super(material);
        this.renderType = renderType;
        GameRegistry.registerBlock(this, ItemCloth.class, getUnlocalizedName());
    }

    @Override
    public int getRenderType() {
        return renderType;
    }
}
