package slimevoid.projectbench.core;

import java.io.File;

import slimevoid.projectbench.core.lib.CoreLib;
import slimevoidlib.core.SlimevoidCore;

import net.minecraftforge.common.Configuration;

public class PBInit {
	
	private static boolean initialized;
	
	public static void initialize() {
		if (initialized)
			return;
		initialized = true;
		ProjectBench.proxy.preInit();
		PBCore.configuration = new Configuration(new File(
				ProjectBench.proxy.getMinecraftDir(),
				"config/ProjectBench.cfg"));
		load();
	}
	
	public static void load() {
		ProjectBench.proxy.registerConfigurationProperties();
		
		ProjectBench.proxy.preInit();
		
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering names...");
		PBCore.registerNames();
		
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering blocks...");
		PBCore.registerBlocks();
		
		ProjectBench.proxy.registerRenderInformation();
	}

}
