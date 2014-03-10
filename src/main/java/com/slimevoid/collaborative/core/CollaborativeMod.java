package com.slimevoid.collaborative.core;

import com.slimevoid.collaborative.core.lib.CoreLib;
import com.slimevoid.collaborative.core.lib.PacketLib;
import com.slimevoid.library.ICommonProxy;
import com.slimevoid.library.util.helpers.PacketHelper;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = CoreLib.MOD_ID,
        name = CoreLib.MOD_NAME,
        version = CoreLib.MOD_VERSION,
        dependencies = CoreLib.MOD_DEPENDENCIES)
public class CollaborativeMod {
    @SidedProxy(
            clientSide = CoreLib.CLIENT_PROXY,
            serverSide = CoreLib.COMMON_PROXY)
    public static ICommonProxy     proxy;

    @Instance(CoreLib.MOD_ID)
    public static CollaborativeMod instance;

    @EventHandler
    public void CollaborativePreInit(FMLPreInitializationEvent event) {
        proxy.registerConfigurationProperties(event.getSuggestedConfigurationFile());
        proxy.preInit();
        CMInit.preInitialize();
    }

    @EventHandler
    public void CollaborativeInit(FMLInitializationEvent event) {
        proxy.init();
        PacketHelper.registerHandler(CoreLib.MOD_CHANNEL,
                                     PacketLib.handler);
    }

    @EventHandler
    public void CollaborativePostInit(FMLPostInitializationEvent event) {
        proxy.postInit();
        CMInit.postInitialize();
    }
}