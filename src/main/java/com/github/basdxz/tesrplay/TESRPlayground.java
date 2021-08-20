package com.github.basdxz.tesrplay;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = TESRPlayground.MODID, name = TESRPlayground.NAME, version = TESRPlayground.VERSION)
public class TESRPlayground {
    public static final String MODID = "tesrplay";
    public static final String NAME = "TESR Playground";
    public static final String VERSION = "@version@";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerBlock(new TestBlock(), "[YourBlock]");
        ClientRegistry.bindTileEntitySpecialRenderer(TestTile.class, new TestTESR());
    }
}
