package slimevoid.projectbench.blocks;

import slimevoid.projectbench.core.lib.BlockLib;
import slimevoid.projectbench.tileentity.TileEntityProjectBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockProjectBase extends BlockBase {
	
	protected Icon[][] iconList;
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		iconList = new Icon[BlockLib.BLOCK_BASE_MAX][6];
		iconList = BlockLib.registerIcons(iconRegister, iconList);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z, int side) {
		int metadata = iblockaccess.getBlockMetadata(x, y, z);
		TileEntityProjectBase tileentity = (TileEntityProjectBase) BlockLib.getTileEntity(iblockaccess, x, y, z, this.tileEntityMap[metadata]);
		if (tileentity != null) {
			return this.getIcon(tileentity.getBlockTexture(x, y, z, side), metadata);
		}
		return this.getIcon(side, metadata);
	}
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return this.iconList[metadata][side];
	}

	public BlockProjectBase(int blockID) {
		super(blockID, Material.rock);
		this.setHardness(2.0F);
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
	public int getRenderType() {
		return 0;
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
