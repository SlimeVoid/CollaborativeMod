package slimevoid.projectbench.core;

import slimevoid.projectbench.core.lib.CoreLib;
import slimevoidlib.core.SlimevoidCore;

public class PBInit {
	
	private static boolean initialized;
	
	public static void initialize() {
		if (initialized)
			return;
		initialized = true;
		ProjectBench.proxy.preInit();
		load();
	}
	
	public static void load() {
		ProjectBench.proxy.registerConfigurationProperties();
		
		ProjectBench.proxy.preInit();
		
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering names...");
		PBCore.registerNames();
		
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering blocks...");
		PBCore.registerBlocks();

		SlimevoidCore.console(CoreLib.MOD_ID, "Registering items...");
		PBCore.registerItems();
		
		ProjectBench.proxy.registerRenderInformation();
	}

}
