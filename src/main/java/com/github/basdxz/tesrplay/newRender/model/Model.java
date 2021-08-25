package com.github.basdxz.tesrplay.newRender.model;

import cpw.mods.fml.relauncher.SideOnly;

import static cpw.mods.fml.relauncher.Side.CLIENT;

@SideOnly(CLIENT)
public interface Model {
    void registerModel();

    void renderModel();
}
