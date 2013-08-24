package slimevoid.projectbench.blocks;

import slimevoid.projectbench.core.lib.IconLib;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockProjectBench extends BlockProjectBase {
	
	protected Icon[] iconList;

	protected BlockProjectBench(int blockID, Material material, Class tileentityclass) {
		super(blockID, material, tileentityclass);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		iconList = new Icon[6];
		iconList[0] = iconRegister.registerIcon(IconLib.PROJECT_BENCH_BOTTOM);
		iconList[1] = iconRegister.registerIcon(IconLib.PROJECT_BENCH_TOP);
		iconList[2] = iconRegister.registerIcon(IconLib.PROJECT_BENCH_SIDE);
		iconList[3] = iconRegister.registerIcon(IconLib.PROJECT_BENCH_FRONT);
		iconList[4] = iconRegister.registerIcon(IconLib.PROJECT_BENCH_SIDE);
		iconList[5] = iconRegister.registerIcon(IconLib.PROJECT_BENCH_SIDE);
	}
}
