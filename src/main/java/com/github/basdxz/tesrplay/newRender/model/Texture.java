package com.github.basdxz.tesrplay.newRender.model;

import cpw.mods.fml.client.FMLClientHandler;
import lombok.AllArgsConstructor;
import net.minecraft.util.ResourceLocation;

@AllArgsConstructor
public class Texture {
    private final ResourceLocation texture;

    public void bind() {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
    }
}
