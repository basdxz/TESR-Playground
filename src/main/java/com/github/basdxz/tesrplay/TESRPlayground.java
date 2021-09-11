package com.github.basdxz.tesrplay;

import com.github.basdxz.tesrplay.newRender.BlockItemSpecialRenderer;
import com.github.basdxz.tesrplay.newRender.model.Models;
import com.github.basdxz.tesrplay.shipping.LaserGlass;
import com.github.basdxz.tesrplay.shipping.TestBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.val;

//TODO missing proxy
@Mod(modid = TESRPlayground.MODID, name = TESRPlayground.NAME, version = TESRPlayground.VERSION)
public class TESRPlayground {
    public static final String MODID = "tesrplay";
    public static final String NAME = "TESR Playground";
    public static final String VERSION = "@version@";

    @EventHandler
    public void init(FMLInitializationEvent event) {


        Models.load();
        BlockItemSpecialRenderer.load();
        val testBlock = new TestBlock(BlockItemSpecialRenderer.getInstanceRenderId());
        GameRegistry.registerBlock(testBlock, "blocktestblock");

        val laz = new LaserGlass(BlockItemSpecialRenderer.getInstanceRenderId());
    }
}
