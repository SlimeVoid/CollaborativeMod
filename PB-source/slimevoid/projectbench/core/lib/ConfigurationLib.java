package slimevoid.projectbench.core.lib;

import slimevoid.projectbench.core.PBCore;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ConfigurationLib {
	
	@SideOnly(Side.CLIENT)
	public static void ClientConfig() {
		CommonConfig();
	}
	
	public static void CommonConfig() {
		PBCore.configuration.load();
		
		PBCore.blockProjectBaseID = PBCore.configuration.get(
				Configuration.CATEGORY_BLOCK,
				"projectBaseID",
				1890).getInt();
		RenderLib.BLOCK_BASE = RenderingRegistry.getNextAvailableRenderId();
		PBCore.itemPlanBlankID = PBCore.configuration.get(
				Configuration.CATEGORY_BLOCK,
				"planBlankID",
				18900).getInt();
		PBCore.itemPlanFullID = PBCore.configuration.get(
				Configuration.CATEGORY_BLOCK,
				"planFullID",
				18901).getInt();
		PBCore.configuration.save();
	}

}
