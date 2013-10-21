package slimevoid.collaborative.tileentity;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import slimevoid.collaborative.core.CollaborativeMod;
import slimevoid.collaborative.core.lib.BlockLib;
import slimevoid.collaborative.core.lib.ChestLib;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.core.lib.GuiLib;
import slimevoidlib.util.helpers.ItemHelper;
import slimevoidlib.util.helpers.SlimevoidHelper;

public class TileEntityWorkChestBase extends TileEntityCollaborativeBase
		implements ISidedInventory {

	private ItemStack[]	storedPlans;
	private int			chestType;

	public TileEntityWorkChestBase() {
		storedPlans = new ItemStack[ChestLib.DEFAULT_CHEST_SIZE];
		chestType = 0;
	}

	@Override
	public int getExtendedBlockID() {
		return BlockLib.BLOCK_WORK_CHEST_ID;
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
									GuiLib.GUIID_WORK_CHEST,
									this.worldObj,
									this.xCoord,
									this.yCoord,
									this.zCoord);
			return true;
		}
	}

	@Override
	public void onBlockRemoval(int side, int metadata) {
		for (int slot = 0; slot < this.storedPlans.length; slot++) {
			ItemStack itemstack = this.storedPlans[slot];
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
		return 0;
	}

	public int getSizeInventorySide(ForgeDirection side) {
		return this.storedPlans.length;
	}

	@Override
	public int getSizeInventory() {
		return this.storedPlans.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.storedPlans[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.storedPlans[slot] == null) {
			return null;
		}
		ItemStack itemstack;
		if (this.storedPlans[slot].stackSize <= amount) {
			itemstack = this.storedPlans[slot];
			this.storedPlans[slot] = null;
			this.onInventoryChanged();
			return itemstack;
		}
		itemstack = storedPlans[slot].splitStack(amount);
		if (storedPlans[slot].stackSize == 0) {
			storedPlans[slot] = null;
		}
		this.onInventoryChanged();
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.storedPlans[i] == null) {
			return null;
		} else {
			ItemStack itemstack = this.storedPlans[i];
			storedPlans[i] = null;
			return itemstack;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		storedPlans[i] = itemstack;
		if (itemstack != null
			&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return ChestLib.getChestName(this.chestType);
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
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
		return itemstack.getItem().itemID == ConfigurationLib.itemPlanFull.itemID;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		int[] slots = new int[this.storedPlans.length];
		for (int i = 0; i < slots.length; i++) {
			slots[i] = i;
		}
		return slots;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		return itemstack.itemID == ConfigurationLib.itemPlanFull.itemID
				&& ForgeDirection.getOrientation(side) != ForgeDirection.DOWN;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return ForgeDirection.getOrientation(side) == ForgeDirection.DOWN;
	}

	@Override
	public void onBlockPlacedBy(ItemStack itemstack, EntityLiving entity) {
		super.onBlockPlacedBy(	itemstack,
								entity);
		if (itemstack.getTagCompound() != null) {
			this.chestType = itemstack.stackTagCompound.getInteger("Type");
		}
		this.storedPlans = new ItemStack[ChestLib.getChestSize(this.chestType)];
	}

	@Override
	public void addHarvestContents(ArrayList<ItemStack> harvestList) {
		ItemStack chest = new ItemStack(this.getBlockID(), 1, this.getExtendedBlockID());
		NBTTagCompound nbtTag = new NBTTagCompound();
		nbtTag.setInteger(	"Type",
							this.chestType);
		chest.setTagCompound(nbtTag);
		harvestList.add(chest);
	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		NBTTagList plans = nbttagcompound.getTagList("Plans");
		for (int i = 0; i < plans.tagCount(); i++) {
			NBTTagCompound plan = (NBTTagCompound) plans.tagAt(i);
			int j = plan.getByte("Plan") & 0xff;
			if (j >= 0 && j < this.storedPlans.length) {
				this.storedPlans[j] = ItemStack.loadItemStackFromNBT(plan);
			}
		}
		if (nbttagcompound.hasKey("Type")) {
			this.chestType = nbttagcompound.getInteger("Type");
		} else {
			this.chestType = 0;
		}
	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		NBTTagList plans = new NBTTagList();
		for (int i = 0; i < storedPlans.length; i++) {
			if (storedPlans[i] != null) {
				NBTTagCompound plan = new NBTTagCompound();
				plan.setByte(	"Plan",
								(byte) i);
				this.storedPlans[i].writeToNBT(plan);
				plans.appendTag(plan);
			}
		}
		nbttagcompound.setTag(	"Plans",
								plans);
		nbttagcompound.setInteger(	"Type",
									this.chestType);
	}

}
