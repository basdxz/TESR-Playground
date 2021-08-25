package com.github.basdxz.tesrplay;

import com.github.basdxz.tesrplay.newRender.MetaBlockRenderer;
import com.github.basdxz.tesrplay.newRender.Models;
import com.github.basdxz.tesrplay.newRender.TestBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.val;

@Mod(modid = TESRPlayground.MODID, name = TESRPlayground.NAME, version = TESRPlayground.VERSION)
public class TESRPlayground {
    public static final String MODID = "tesrplay";
    public static final String NAME = "TESR Playground";
    public static final String VERSION = "@version@";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Models.load();
        MetaBlockRenderer.load();
        val testBlock = new TestBlock(MetaBlockRenderer.getInstanceRenderId());
        GameRegistry.registerBlock(testBlock, "blocktestblock");
    }
}
