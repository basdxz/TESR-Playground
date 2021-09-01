package com.github.basdxz.tesrplay.advancedCubeMakingThing.components;

import net.minecraft.util.IIcon;

public abstract class IIconContainer implements IIcon {
    private final IIcon icon;
    protected IIconContainer(IIcon icon) {
        this.icon = icon;
    }

    @Override
    public int getIconWidth() {
        return icon.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return icon.getIconHeight();
    }

    @Override
    public float getMinU() {
        return icon.getMinU();
    }

    @Override
    public float getMaxU() {
        return icon.getMaxU();
    }

    @Override
    public float getInterpolatedU(double p_94214_1_) {
        return icon.getInterpolatedU(p_94214_1_);
    }

    @Override
    public float getMinV() {
        return icon.getMinV();
    }

    @Override
    public float getMaxV() {
        return icon.getMaxV();
    }

    @Override
    public float getInterpolatedV(double p_94207_1_) {
        return icon.getInterpolatedV(p_94207_1_);
    }

    @Override
    public String getIconName() {
        return icon.getIconName();
    }
}
