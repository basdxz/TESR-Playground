package com.github.basdxz.tesrplay;

import com.github.basdxz.tesrplay.newRender.BlockItemSpecialRenderer;
import com.github.basdxz.tesrplay.newRender.model.Models;
import com.github.basdxz.tesrplay.newRender.TestBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.val;
import net.minecraft.util.ResourceLocation;

@Mod(modid = TESRPlayground.MODID, name = TESRPlayground.NAME, version = TESRPlayground.VERSION)
public class TESRPlayground {
    public static final String MODID = "tesrplay";
    public static final String NAME = "TESR Playground";
    public static final String VERSION = "@version@";

    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;

    public static final int U = 0;
    public static final int V = 1;

    public static final int ABCD = 0;
    public static final int A = 1;
    public static final int AB = 2;
    public static final int B = 3;
    public static final int BC = 4;
    public static final int C = 5;
    public static final int CD = 6;
    public static final int D = 7;
    public static final int AD = 8;

    public static final int AO0 = 0;
    public static final int AO1 = 1;
    public static final int A02 = 2;
    public static final int AO3 = 3;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Models.load();
        BlockItemSpecialRenderer.load();
        val testBlock = new TestBlock(BlockItemSpecialRenderer.getInstanceRenderId());
        GameRegistry.registerBlock(testBlock, "blocktestblock");
    }
}
