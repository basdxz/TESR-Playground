package com.github.basdxz.tesrplay.newRender.cuboid;

import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Arrays;

public class SameSideAllAround implements LayeredIcon {
    private final Iterable<BlendableIcon> layers;

    public SameSideAllAround(BlendableIcon... layer) {
        this.layers = setLayers(layer);
    }

    private static Iterable<BlendableIcon> setLayers(BlendableIcon... layer) {
        return new ArrayList<>(Arrays.asList(layer));
    }

    @Override
    public Iterable<BlendableIcon> getBlendableIconLayers(ForgeDirection side) {
        return layers;
    }
}
