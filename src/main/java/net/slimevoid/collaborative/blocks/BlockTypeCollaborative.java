package net.slimevoid.collaborative.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slimevoid.collaborative.core.lib.BlockLib;
import net.slimevoid.collaborative.tileentity.TileEntityWorkBench;
import net.slimevoid.collaborative.tileentity.TileEntityWorkChestWood;
import net.slimevoid.library.blocks.IBlockEnumType;
import net.slimevoid.library.tileentity.TileEntityBase;

public enum BlockTypeCollaborative implements IBlockEnumType {
	
	WORKBENCH(BlockLib.BLOCK_WORK_BENCH_ID, BlockLib.BLOCK_WORK_BENCH, TileEntityWorkBench.class),
	WORKCHEST_WOOD(BlockLib.WORK_CHEST_WOOD_ID, BlockLib.BLOCK_WORK_CHEST + ".wood", TileEntityWorkChestWood.class);
	
	int meta;
	String name;
	Class<? extends TileEntityBase> tile;
	
	BlockTypeCollaborative(int meta, String name, Class tile) {
		this.meta = meta;
		this.name = name;
		this.setTileData(tile);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setTileData(Class<? extends TileEntityBase> tileEntityClass) {
		this.tile = tileEntityClass;
		GameRegistry.registerTileEntity(tileEntityClass, this.getName());
	}

	@Override
	public int getMeta() {
		return meta;
	}

	@Override
	public Class<? extends TileEntityBase> getTileEntityClass() {
		return tile;
	}

	@Override
	public TileEntity createTileEntity() {
		try {
			return tile.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

}
