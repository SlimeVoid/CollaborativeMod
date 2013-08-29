package slimevoid.collaborative.core;

import net.minecraftforge.common.Configuration;
import slimevoid.collaborative.client.network.ClientPacketHandler;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.core.lib.CoreLib;
import slimevoid.collaborative.network.CommonPacketHandler;
import slimevoid.collaborative.network.ConnectionHandler;
import slimevoidlib.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
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
public class CollaborativeMod {
	@SidedProxy(
			clientSide = CoreLib.CLIENT_PROXY,
			serverSide = CoreLib.COMMON_PROXY)
	public static ICommonProxy proxy;
	
	@Instance(CoreLib.MOD_ID)
	public static CollaborativeMod instance;

	@EventHandler
	public void CollaborativePreInit(FMLPreInitializationEvent event) {
		ConfigurationLib.configuration = new Configuration(event.getSuggestedConfigurationFile());
		// loading the configuration from its file
		ConfigurationLib.configuration.load();
		// saving the configuration to its file
		ConfigurationLib.configuration.save();
	}

	@EventHandler
	public void CollaborativeInit(FMLInitializationEvent event) {
	}

	@EventHandler
	public void CollaborativePostInit(FMLPostInitializationEvent event) {
		CMInit.initialize();
	}
}