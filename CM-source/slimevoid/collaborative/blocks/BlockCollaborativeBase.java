package slimevoid.collaborative.blocks;

import slimevoid.collaborative.core.lib.BlockLib;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoidlib.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockCollaborativeBase extends BlockBase {
	
	protected Icon[][] iconList;
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		iconList = new Icon[BlockLib.BLOCK_MAX_TILES][6];
		iconList = BlockLib.registerIcons(iconRegister, iconList);
	}
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return this.iconList[metadata][side];
	}

	public BlockCollaborativeBase(int blockID) {
		super(blockID, Material.rock, BlockLib.BLOCK_MAX_TILES);
		this.setHardness(2.0F);
		this.setStepSound(Block.soundStoneFootstep);
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

	@Override
	public CreativeTabs getCreativeTab() {
		return ConfigurationLib.customTab;
	}

}
