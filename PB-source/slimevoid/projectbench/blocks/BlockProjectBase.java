package slimevoid.projectbench.blocks;

import slimevoid.projectbench.core.lib.BlockLib;
import slimevoid.projectbench.tileentity.TileEntityProjectBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;

public class BlockProjectBase extends BlockBase {

	protected BlockProjectBase(int blockID, Material material,
			Class tileEntityClass) {
		super(blockID, material, tileEntityClass);
		this.setHardness(2.0F);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public int getLightValue(IBlockAccess iblockaccess, int x, int y, int z) {
		TileEntityProjectBase tileentityprojectbase = (TileEntityProjectBase) BlockLib.getTileEntity(iblockaccess, x, y, z, TileEntityProjectBase.class);
		if (tileentityprojectbase == null)
			return super.getLightValue(iblockaccess, x, y, z);
		else
			return tileentityprojectbase.getLightValue();
	}
	
	@Override
	public boolean isOpaqueCube() {
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}
	
	public boolean isCube() {
		return true;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

}
