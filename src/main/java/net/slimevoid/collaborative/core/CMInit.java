package net.slimevoid.collaborative.core;

import net.slimevoid.collaborative.core.lib.CoreLib;
import net.slimevoid.library.core.SlimevoidCore;

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
