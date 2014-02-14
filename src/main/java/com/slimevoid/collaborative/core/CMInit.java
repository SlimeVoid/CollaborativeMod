package com.slimevoid.collaborative.core;

import com.slimevoid.collaborative.core.lib.CoreLib;
import com.slimevoid.library.core.SlimevoidCore;

public class CMInit {

    private static boolean initialized;

    public static void postInitialize() {
        if (initialized) {
            return;
        }
        initialized = true;
    }

    public static void preInitialize() {
        SlimevoidCore.console(CoreLib.MOD_ID,
                              "Registering blocks...");
        CMCore.registerBlocks();

        SlimevoidCore.console(CoreLib.MOD_ID,
                              "Registering items...");
        CMCore.registerItems();

        CollaborativeMod.proxy.registerRenderInformation();
    }

}
