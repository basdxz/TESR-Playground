package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import lombok.val;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public class SameSideAllAround implements LayeredIcon {
    private final Iterable<BlendableIcon> layers;

    public SameSideAllAround(BlendableIcon layer) {
        this.layers = getLayers(layer);
    }

    private static Iterable<BlendableIcon> getLayers(BlendableIcon layer) {
        val layers = new ArrayList<BlendableIcon>();
        layers.add(layer);
        return layers;
    }

    @Override
    public Iterable<BlendableIcon> getBlendableIconLayers(ForgeDirection side) {
        return layers;
    }
}
