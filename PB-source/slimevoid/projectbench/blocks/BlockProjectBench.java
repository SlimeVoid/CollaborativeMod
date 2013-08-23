package slimevoid.projectbench.blocks;

import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockProjectBench extends BlockContainer {

	protected BlockProjectBench(int blockID, Material material) {
		super(blockID, material);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityProjectBench();
	}

}
