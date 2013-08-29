package slimevoid.collaborative.blocks;

import slimevoid.collaborative.core.lib.BlockLib;
import slimevoid.collaborative.tileentity.TileEntityCollaborativeBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockCollaborativeBase extends BlockBase {
	
	protected Icon[][] iconList;
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		iconList = new Icon[BlockLib.BLOCK_BASE_MAX][6];
		iconList = BlockLib.registerIcons(iconRegister, iconList);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z, int side) {
		int metadata = iblockaccess.getBlockMetadata(x, y, z);
		TileEntityCollaborativeBase tileentity = (TileEntityCollaborativeBase) BlockLib.getTileEntity(iblockaccess, x, y, z, this.tileEntityMap[metadata]);
		if (tileentity != null) {
			return this.getIcon(tileentity.getBlockTexture(x, y, z, side), metadata);
		}
		return this.getIcon(side, metadata);
	}
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return this.iconList[metadata][side];
	}

	public BlockCollaborativeBase(int blockID) {
		super(blockID, Material.rock);
		this.setHardness(2.0F);
	}

	@Override
	public int getLightValue(IBlockAccess iblockaccess, int x, int y, int z) {
		TileEntityCollaborativeBase tileentitybase = (TileEntityCollaborativeBase) BlockLib.getTileEntity(iblockaccess, x, y, z, TileEntityCollaborativeBase.class);
		if (tileentitybase == null)
			return super.getLightValue(iblockaccess, x, y, z);
		else
			return tileentitybase.getLightValue();
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
