package slimevoid.projectbench.core;

import cpw.mods.fml.common.registry.GameRegistry;
import slimevoid.projectbench.blocks.BlockProjectBase;
import slimevoid.projectbench.core.lib.BlockLib;
import slimevoid.projectbench.core.lib.IconLib;
import slimevoid.projectbench.core.lib.ItemLib;
import slimevoid.projectbench.core.lib.LocaleLib;
import slimevoid.projectbench.items.ItemBase;
import slimevoid.projectbench.items.ItemPlan;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public class PBCore {
	
	public static int blockProjectBaseID;
	public static int itemPlanBlankID;
	public static int itemPlanFullID;
	public static BlockProjectBase blockProjectBase;
	public static Configuration configuration;
	public static Item itemPlanBlank;
	public static Item itemPlanFull;

	public static void registerNames() {
		LocaleLib.registerLanguages();
	}
	
	public static void registerBlocks() {
		blockProjectBase = new BlockProjectBase(blockProjectBaseID);
		GameRegistry.registerBlock(blockProjectBase, ItemBase.class, BlockLib.BLOCK_PROJECT_BASE);
		GameRegistry.registerTileEntity(TileEntityProjectBench.class, BlockLib.BLOCK_PROJECT_BENCH);
		blockProjectBase.addTileEntityMapping(BlockLib.BLOCK_PROJECT_BENCH_ID, TileEntityProjectBench.class);
		blockProjectBase.setItemName(BlockLib.BLOCK_PROJECT_BENCH_ID, BlockLib.BLOCK_PROJECT_BENCH);
		GameRegistry.addRecipe(new ItemStack(blockProjectBase, 1, BlockLib.BLOCK_PROJECT_BENCH_ID),
				new Object[] {
					"SBS",
					"SCS",
					"WSW",
					Character.valueOf('S'), Block.stone,
					Character.valueOf('B'), Block.workbench,
					Character.valueOf('C'), Block.chest,
					Character.valueOf('W'), Block.woodSingleSlab
		});
	}
	
	public static void registerItems() {
		itemPlanBlank = new Item(itemPlanBlankID).func_111206_d(IconLib.PROJECT_PLAN_BLANK).setUnlocalizedName(ItemLib.PROJECT_PLAN_BLANK);
		itemPlanBlank.setCreativeTab(CreativeTabs.tabMisc);
		itemPlanFull = new ItemPlan(itemPlanFullID);
		itemPlanFull.setCreativeTab(CreativeTabs.tabMisc);
	}
}
