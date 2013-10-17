package slimevoid.collaborative.core;

import slimevoid.collaborative.core.lib.CoreLib;
import slimevoidlib.core.SlimevoidCore;

public class CMInit {

	private static boolean	initialized;

	public static void initialize() {
		if (initialized) {
			return;
		}
		initialized = true;
		load();
	}

	public static void load() {
		SlimevoidCore.console(	CoreLib.MOD_ID,
								"Registering names...");
		CMCore.registerNames();

		SlimevoidCore.console(	CoreLib.MOD_ID,
								"Registering blocks...");
		CMCore.registerBlocks();

		SlimevoidCore.console(	CoreLib.MOD_ID,
								"Registering items...");
		CMCore.registerItems();

		CollaborativeMod.proxy.registerRenderInformation();
	}

}
