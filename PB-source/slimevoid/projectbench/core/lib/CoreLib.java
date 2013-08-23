package slimevoid.projectbench.core.lib;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CoreLib {
	
	public static final String MOD_ID = "ProjectBench";
	public static final String MOD_RESOURCES = "projectbench";
	public static final String MOD_NAME = "Project Bench";
	public static final String MOD_VERSION = "0.0.0.1";
	public static final String MOD_DEPENDENCIES = "required-after:SlimevoidLib";
	public static final String MOD_CHANNEL = "PROJECTBENCH";
	public static final String CLIENT_PROXY = "slimevoid.projectbench.client.proxy.ClientProxy";
	public static final String COMMON_PROXY = "slimevoid.projectbench.proxy.CommonProxy";
	@SideOnly(Side.CLIENT) 
	public static boolean OPTIFINE_INSTALLED = FMLClientHandler.instance().hasOptifine();;
}
