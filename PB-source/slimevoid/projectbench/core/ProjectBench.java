package slimevoid.projectbench.core;

import net.minecraftforge.common.Configuration;
import slimevoid.projectbench.client.network.ClientPacketHandler;
import slimevoid.projectbench.core.lib.ConfigurationLib;
import slimevoid.projectbench.core.lib.CoreLib;
import slimevoid.projectbench.network.CommonPacketHandler;
import slimevoid.projectbench.network.ConnectionHandler;
import slimevoidlib.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

@Mod(
		modid = CoreLib.MOD_ID,
		name = CoreLib.MOD_NAME,
		version = CoreLib.MOD_VERSION,
		dependencies = CoreLib.MOD_DEPENDENCIES)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		clientPacketHandlerSpec = @SidedPacketHandler(
				channels = { CoreLib.MOD_CHANNEL },
				packetHandler = ClientPacketHandler.class),
		serverPacketHandlerSpec = @SidedPacketHandler(
				channels = { CoreLib.MOD_CHANNEL },
				packetHandler = CommonPacketHandler.class),
		connectionHandler = ConnectionHandler.class
)
public class ProjectBench {
	@SidedProxy(
			clientSide = CoreLib.CLIENT_PROXY,
			serverSide = CoreLib.COMMON_PROXY)
	public static ICommonProxy proxy;
	
	@Instance(CoreLib.MOD_ID)
	public static ProjectBench instance;

	@PreInit
	public void ProjectBenchPreInit(FMLPreInitializationEvent event) {
		ConfigurationLib.configuration = new Configuration(event.getSuggestedConfigurationFile());
		// loading the configuration from its file
		ConfigurationLib.configuration.load();
		// saving the configuration to its file
		ConfigurationLib.configuration.save();
	}

	@Init
	public void ProjectBenchInit(FMLInitializationEvent event) {
	}

	@PostInit
	public void ProjectBenchPostInit(FMLPostInitializationEvent event) {
		PBInit.initialize();
	}
}