package com.github.basdxz.tesrplay.newRender.model;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class WavefrontModel implements Model {
    private static final String RESOURCE_DIRECTORY = "textures/models/";

    private final String resourcePath;
    protected Texture texture;
    protected IModelCustom model;

    public WavefrontModel(String modid, String name) {
        resourcePath = modid + ":" + RESOURCE_DIRECTORY + name;
    }

    @Override
    public void registerModel() {
        texture = loadTexture(resourcePath);
        model = loadModel(resourcePath);
    }

    private Texture loadTexture(String resourcePath) {
        return new Texture(new ResourceLocation(resourcePath + ".png"));
    }

    private IModelCustom loadModel(String resourcePath) {
        return AdvancedModelLoader.loadModel(new ResourceLocation(resourcePath + ".obj"));
    }

    @Override
    public void renderModel() {
        texture.bind();
        model.renderAll();
    }
}
