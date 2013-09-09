package slimevoid.collaborative.core.lib;

import java.io.File;
import java.util.HashMap;

import slimevoid.collaborative.blocks.BlockCollaborativeBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ConfigurationLib {
	
	public static int blockCollaborativeBaseID;
	public static int itemPlanBlankID;
	public static int itemPlanFullID;
	public static BlockCollaborativeBase blockCollaborativeBase;
	public static Configuration configuration;
	public static Item itemPlanBlank;
	public static Item itemPlanFull;
	public static boolean playerInventoryLocked;
	public static HashMap<EntityPlayer, Boolean> playersInventoryLocked = new HashMap<EntityPlayer, Boolean>();
	public static CreativeTabs customTab;
	public static boolean debug;

	
	@SideOnly(Side.CLIENT)
	public static void ClientConfig(File configFile) {
		CommonConfig(configFile);
	}
	
	public static void CommonConfig(File configFile) {
		configuration = new Configuration(configFile);
		
		configuration.load();
		
		blockCollaborativeBaseID = configuration.get(
				Configuration.CATEGORY_BLOCK,
				"collaborativeBaseID",
				1890).getInt();
		RenderLib.BLOCK_BASE = RenderingRegistry.getNextAvailableRenderId();
		itemPlanBlankID = configuration.get(
				Configuration.CATEGORY_BLOCK,
				"planBlankID",
				18900).getInt();
		itemPlanFullID = configuration.get(
				Configuration.CATEGORY_BLOCK,
				"planFullID",
				18901).getInt();
		playerInventoryLocked = configuration.get(
				Configuration.CATEGORY_GENERAL,
				"playerInventoryLocked",
				true).getBoolean(true);
		debug = configuration.get(
				Configuration.CATEGORY_GENERAL,
				"debug",
				false).getBoolean(true);
		configuration.save();

		customTab = new CreativeTabs(CoreLib.MOD_RESOURCES) {
			public ItemStack getIconItemStack() {
				return new ItemStack(blockCollaborativeBase, 1, 0);
			}

		};
	}
	
	public static void updateplayerInventoryLocked(boolean inventoryMode) {
		playerInventoryLocked = inventoryMode;
		configuration.get(Configuration.CATEGORY_GENERAL, "playerInventoryLocked", false).set(inventoryMode);
		configuration.save();
	}

	public static void updatePlayersInventoryLocked(EntityPlayer entityplayer, boolean inventoryMode) {
		if (playersInventoryLocked.containsKey(entityplayer)) {
			playersInventoryLocked.remove(entityplayer);
		}
		playersInventoryLocked.put(entityplayer, inventoryMode);
	}

	public static boolean isPlayerInventoryLocked(EntityPlayer entityplayer) {
		boolean flag = !playersInventoryLocked.containsKey(entityplayer) || playersInventoryLocked.get(entityplayer);
		return flag;
	}

}
