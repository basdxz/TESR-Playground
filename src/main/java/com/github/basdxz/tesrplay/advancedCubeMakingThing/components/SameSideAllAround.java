package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendEquations;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.boxesWithStuff.GLBlendFuncs;
import com.github.basdxz.tesrplay.newRender.TestBlock;
import lombok.*;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

import static com.github.basdxz.tesrplay.newRender.TestBlock.*;

public class SameSideAllAround implements LayeredSidedBlendableIcon{
    @Override
    public Iterable<BlendableIcon> getBlendableIconLayers(ForgeDirection side) {
        val list = new ArrayList<BlendableIcon>();
        list.add(MaterialTexture.builder()
                //.icon(TEST_2)
                .icon(Blocks.iron_ore.getIcon(0,0))
                //.glBlendEquation(GLBlendEquations.MIN)
                //.glBlendFunc(GLBlendFuncs.ADDITIVE)
                .build());
        return list;
    }
}
