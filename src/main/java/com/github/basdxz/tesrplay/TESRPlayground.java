package com.github.basdxz.tesrplay;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = TESRPlayground.MODID, name = TESRPlayground.NAME, version = TESRPlayground.VERSION)
public class TESRPlayground {
    public static final String MODID = "tesrplay";
    public static final String NAME = "TESR Playground";
    public static final String VERSION = "@version@";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //Some example code
        //System.out.println("DIRT BLOCK 2 >> " + Blocks.dirt.getUnlocalizedName());

        //System.out.println(MODID);
        //System.out.println(MODID);
        //System.out.println(MODID);
    }
}
