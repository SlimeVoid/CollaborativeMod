package slimevoid.collaborative.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimevoid.collaborative.blocks.BlockCollaborativeBase;
import slimevoid.collaborative.core.lib.BlockLib;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.core.lib.IconLib;
import slimevoid.collaborative.core.lib.LocaleLib;
import slimevoid.collaborative.items.ItemPlan;
import slimevoid.collaborative.tileentity.TileEntityWorkBench;
import slimevoid.collaborative.tileentity.TileEntityWorkChestBase;
import slimevoidlib.items.ItemBlockBase;
import cpw.mods.fml.common.registry.GameRegistry;

public class CMCore {
	public static void registerNames() {
		LocaleLib.registerLanguages();
	}

	public static void registerBlocks() {
		ConfigurationLib.blockCollaborativeBase = new BlockCollaborativeBase(ConfigurationLib.blockCollaborativeBaseID);

		GameRegistry.registerBlock(	ConfigurationLib.blockCollaborativeBase,
									ItemBlockBase.class,
									BlockLib.BLOCK_COLLABORATIVE_BASE);

		/**
		 * WORK BENCH
		 */
		GameRegistry.registerTileEntity(TileEntityWorkBench.class,
										BlockLib.BLOCK_WORK_BENCH);

		ConfigurationLib.blockCollaborativeBase.addTileEntityMapping(	BlockLib.BLOCK_WORK_BENCH_ID,
																		TileEntityWorkBench.class);

		ConfigurationLib.blockCollaborativeBase.setItemName(BlockLib.BLOCK_WORK_BENCH_ID,
															BlockLib.BLOCK_WORK_BENCH);

		GameRegistry.addRecipe(	new ItemStack(ConfigurationLib.blockCollaborativeBase, 1, BlockLib.BLOCK_WORK_BENCH_ID),
								new Object[] {
										"SBS",
										"SCS",
										"WWW",
										Character.valueOf('S'),
										Block.cobblestone,
										Character.valueOf('B'),
										Block.workbench,
										Character.valueOf('C'),
										Block.chest,
										Character.valueOf('W'),
										Block.woodSingleSlab });

		/**
		 * WORK CHEST
		 */
		GameRegistry.registerTileEntity(TileEntityWorkChestBase.class,
										BlockLib.BLOCK_WORK_CHEST);

		ConfigurationLib.blockCollaborativeBase.addTileEntityMapping(	BlockLib.BLOCK_WORK_CHEST_ID,
																		TileEntityWorkChestBase.class);

		ConfigurationLib.blockCollaborativeBase.setItemName(BlockLib.BLOCK_WORK_CHEST_ID,
															BlockLib.BLOCK_WORK_CHEST);

		/**
		 * WOODEN WORK CHEST
		 */
		ItemStack woodChest = new ItemStack(ConfigurationLib.blockCollaborativeBase, 1, BlockLib.BLOCK_WORK_CHEST_ID);
		NBTTagCompound nbtTag = new NBTTagCompound();
		nbtTag.setInteger(	"Type",
							BlockLib.WORK_CHEST_WOOD_ID);
		woodChest.setTagCompound(nbtTag);
		GameRegistry.addRecipe(	woodChest,
								new Object[] {
										"WWW",
										"WCW",
										"SSS",
										Character.valueOf('W'),
										Block.woodSingleSlab,
										Character.valueOf('C'),
										Block.chest,
										Character.valueOf('S'),
										Block.cobblestone });

		/**
		 * STONE WORK CHEST
		 */
		ItemStack stoneChest = new ItemStack(ConfigurationLib.blockCollaborativeBase, 1, BlockLib.BLOCK_WORK_CHEST_ID);
		nbtTag = new NBTTagCompound();
		nbtTag.setInteger(	"Type",
							BlockLib.WORK_CHEST_STONE_ID);
		stoneChest.setTagCompound(nbtTag);
		GameRegistry.addRecipe(	stoneChest,
								new Object[] {
										" W ",
										"WCW",
										" W ",
										Character.valueOf('W'),
										Block.cobblestone,
										Character.valueOf('C'),
										woodChest });
	}

	public static void registerItems() {
		ConfigurationLib.itemPlanBlank = new Item(ConfigurationLib.itemPlanBlankID).setUnlocalizedName(IconLib.PLAN_BLANK);
		ConfigurationLib.itemPlanBlank.setCreativeTab(ConfigurationLib.customTab);
		ConfigurationLib.itemPlanFull = new ItemPlan(ConfigurationLib.itemPlanFullID);
		ConfigurationLib.itemPlanFull.setCreativeTab(ConfigurationLib.customTab);
		GameRegistry.addRecipe(	new ItemStack(ConfigurationLib.itemPlanBlank, 5, 0),
								new Object[] {
										"P P",
										" P ",
										"P P",
										Character.valueOf('P'),
										Item.paper });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.paper),
										new ItemStack(ConfigurationLib.itemPlanBlank));
	}
}
