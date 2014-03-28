package com.slimevoid.collaborative.core;

import net.slimevoid.library.core.SlimevoidCore;

import com.slimevoid.collaborative.core.lib.CoreLib;

public class CMInit {

    private static boolean initialized;

    public static void preInitialize() {
        SlimevoidCore.console(CoreLib.MOD_ID,
                              "Registering blocks...");
        CMCore.registerBlocks();

        SlimevoidCore.console(CoreLib.MOD_ID,
                              "Registering items...");
        CMCore.registerItems();

        CollaborativeMod.proxy.registerRenderInformation();
    }

    public static void postInitialize() {
        if (initialized) {
            return;
        }
        initialized = true;
    }

}
