package net.slimevoid.collaborative.core;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.slimevoid.collaborative.core.lib.CoreLib;
import net.slimevoid.collaborative.core.lib.PacketLib;
import net.slimevoid.library.ICommonProxy;
import net.slimevoid.library.util.helpers.PacketHelper;

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
        CMInit.initialize();
    }

    @EventHandler
    public void CollaborativePostInit(FMLPostInitializationEvent event) {
        proxy.postInit();
        CMInit.postInitialize();
    }
}