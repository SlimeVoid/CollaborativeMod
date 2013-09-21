package slimevoid.collaborative.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import slimevoid.collaborative.core.CollaborativeMod;
import slimevoid.collaborative.core.lib.BlockLib;
import slimevoid.collaborative.core.lib.GuiLib;
import slimevoidlib.util.helpers.ItemHelper;
import slimevoidlib.util.helpers.SlimevoidHelper;

public class TileEntityWorkBench extends TileEntityCollaborativeBase implements
		ISidedInventory {

	private ItemStack[]	contents;

	public TileEntityWorkBench() {
		contents = new ItemStack[28];
	}

	@Override
	public int getExtendedBlockID() {
		return 0;
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public boolean onBlockActivated(EntityPlayer entityplayer) {
		if (entityplayer.isSneaking()) {
			return false;
		}
		if (this.worldObj.isRemote) {
			return true;
		} else {
			entityplayer.openGui(	CollaborativeMod.instance,
									GuiLib.GUIID_WORK_BENCH,
									this.worldObj,
									this.xCoord,
									this.yCoord,
									this.zCoord);
			return true;
		}
	}

	@Override
	public void onBlockRemoval(int side, int metadata) {
		for (int i = 0; i < 27; i++) {
			ItemStack itemstack = this.contents[i];
			if (itemstack != null && itemstack.stackSize > 0) {
				ItemHelper.dropItem(this.worldObj,
									this.xCoord,
									this.yCoord,
									this.zCoord,
									itemstack);
			}
		}

	}

	public int getStartInventorySide(ForgeDirection side) {
		// TODO :: Start Inventory
		return 1;
	}

	public int getSizeInventorySide(ForgeDirection side) {
		// TODO :: Size Inventory
		return 18;
	}

	@Override
	public int getSizeInventory() {
		// this is the internal persistent inventory 2 rows of 9 and the plan
		// slot
		return 28;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.contents[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.contents[slot] == null) {
			return null;
		}
		ItemStack itemstack;
		if (this.contents[slot].stackSize <= amount) {
			itemstack = this.contents[slot];
			this.contents[slot] = null;
			this.onInventoryChanged();
			return itemstack;
		}
		itemstack = contents[slot].splitStack(amount);
		if (contents[slot].stackSize == 0) {
			contents[slot] = null;
		}
		this.onInventoryChanged();
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.contents[i] == null) {
			return null;
		} else {
			ItemStack itemstack = this.contents[i];
			contents[i] = null;
			return itemstack;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		contents[i] = itemstack;
		if (itemstack != null
			&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return BlockLib.BLOCK_WORK_BENCH;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return SlimevoidHelper.isUseableByPlayer(	this.worldObj,
													entityplayer,
													this.xCoord,
													this.yCoord,
													this.zCoord,
													0.5D,
													0.5D,
													0.5D,
													64D);
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		// TODO :: Auto-generated method stub
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		// TODO :: Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO :: Auto-generated method stub
		return false;
	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		NBTTagList items = nbttagcompound.getTagList("Items");
		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound) items.tagAt(i);
			int j = item.getByte("Slot") & 0xff;
			if (j >= 0 && j < this.contents.length) {
				this.contents[j] = ItemStack.loadItemStackFromNBT(item);
			}
		}
	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < contents.length; i++) {
			if (contents[i] != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte(	"Slot",
								(byte) i);
				this.contents[i].writeToNBT(item);
				items.appendTag(item);
			}
		}
		nbttagcompound.setTag(	"Items",
								items);
	}

}
