package com.github.basdxz.tesrplay.newRender;

import com.github.basdxz.tesrplay.TESRPlayground;
import cpw.mods.fml.relauncher.SideOnly;

import static cpw.mods.fml.relauncher.Side.CLIENT;

@SideOnly(CLIENT)
public enum Models implements Model {
    PRISM;

    private Model model;

    @Override
    public void registerModel() {
        model = new WavefrontModel(TESRPlayground.MODID, name());
        model.registerModel();
    }

    @Override
    public void renderModel() {
        model.renderModel();
    }

    public static void load() {
        for (Models model : Models.values()) {
            model.registerModel();
        }
    }
}
