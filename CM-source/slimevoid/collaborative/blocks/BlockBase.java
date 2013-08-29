package slimevoid.collaborative.blocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import slimevoid.collaborative.core.lib.BlockLib;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.core.lib.RenderLib;
import slimevoid.collaborative.items.ItemBase;
import slimevoid.collaborative.tileentity.TileEntityBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockBase extends BlockContainer {
	
	Class[] tileEntityMap;

	protected BlockBase(int blockID, Material material) {
		super(blockID, material);
		this.tileEntityMap = new Class[BlockLib.BLOCK_BASE_MAX];
		this.setCreativeTab(ConfigurationLib.customTab);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean isCube() {
		return false;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	@Override
	public ArrayList getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> harvestList = new ArrayList<ItemStack>();
		TileEntityBase tileentitybase = (TileEntityBase) BlockLib.getTileEntity(world, x, y, z, TileEntityBase.class);
		if (tileentitybase == null) {
			return harvestList;
		} else {
			tileentitybase.addHarvestContents(harvestList);
			return harvestList;
		}
	}
	
	@Override
	public int quantityDroppedWithBonus(int i, Random random) {
		return 0;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int x,
			int y, int z, int damage) {
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer entityplayer,
			int x, int y, int z) {
		if (world.isRemote) {
			return true;
		}
		int blockId = world.getBlockId(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		Block block = Block.blocksList[blockId];
		if (block == null) {
			return false;
		}
		if (block.canHarvestBlock(entityplayer, metadata)
				&& !entityplayer.capabilities.isCreativeMode) {
			ArrayList blockDropped = getBlockDropped(world, x, y, z, metadata,
					EnchantmentHelper.getFortuneModifier(entityplayer));
			ItemStack itemstack;
			for (Iterator stack = blockDropped.iterator(); stack.hasNext(); BlockLib
					.dropItem(world, x, y, z, itemstack))
				itemstack = (ItemStack) stack.next();

		}
		world.setBlock(x, y, z, 0);
		return true;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID) {
		TileEntityBase tileentitybase = (TileEntityBase) BlockLib
				.getTileEntity(world, x, y, z, TileEntityBase.class);
		if (tileentitybase == null) {
			world.setBlock(x, y, z, 0);
			return;
		} else {
			tileentitybase.onBlockNeighborChange(blockID);
			return;
		}
	}

	public void onBlockPlaced(World world, int x, int y, int z, int side, EntityPlayer entityplayer, ItemStack itemstack) {
		TileEntityBase tileentitybase = (TileEntityBase) BlockLib
				.getTileEntity(world, x, y, z, TileEntityBase.class);
		if (tileentitybase == null) {
			return;
		} else {
			tileentitybase.onBlockPlaced(itemstack, side, entityplayer);
			return;
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int side, int metadata) {
		TileEntityBase tileentitybase = (TileEntityBase) BlockLib
				.getTileEntity(world, x, y, z, TileEntityBase.class);
		if (tileentitybase == null) {
			return;
		} else {
			tileentitybase.onBlockRemoval();
			super.breakBlock(world, x, y, z, side, metadata);
			return;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float xHit, float yHit, float zHit) {
		TileEntityBase tileentitybase = (TileEntityBase) BlockLib
				.getTileEntity(world, x, y, z, TileEntityBase.class);
		if (tileentitybase == null) {
			return false;
		} else {
			return tileentitybase.onBlockActivated(entityplayer);
		}
	}
	
	@Override
	public int getRenderType() {
		return RenderLib.BLOCK_BASE;
	}
	
	public void addTileEntityMapping(int metadata, Class tileEntityClass) {
		this.tileEntityMap[metadata] = tileEntityClass;
	}

	public void setItemName(int metadata, String name) {
		Item item = Item.itemsList[this.blockID];
		if (item != null) {
			((ItemBase) item).setMetaName(metadata, (new StringBuilder()).append("tile.").append(name).toString());
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		try {
			return (TileEntity) this.tileEntityMap[metadata].newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

}
