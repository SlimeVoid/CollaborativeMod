package slimevoid.projectbench.core;

import cpw.mods.fml.common.registry.GameRegistry;
import slimevoid.projectbench.blocks.BlockProjectBase;
import slimevoid.projectbench.core.lib.BlockLib;
import slimevoid.projectbench.items.ItemBase;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public class PBCore {
	
	public static int blockProjectBaseID;
	public static BlockProjectBase blockProjectBase;
	public static Configuration configuration;

	public static void registerNames() {
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

}
