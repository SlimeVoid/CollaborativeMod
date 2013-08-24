package slimevoid.projectbench.container;

import slimevoid.projectbench.core.PBCore;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerProjectBench extends Container {
	
	public static class ContainerNull extends Container {


		@Override
		public boolean canInteractWith(EntityPlayer entityplayer) {
			return false;
		}

		@Override
		public void onCraftMatrixChanged(IInventory inventory) {
		}

		public ContainerNull() {
		}
	}

	public class InventorySubUpdate implements IInventory {
		
		int size;
		int start;
		IInventory parent;
		final ContainerProjectBench containerProjectBench;

		public InventorySubUpdate(IInventory parentInventory, int startSlot, int inventorySize) {
			super();
			containerProjectBench = ContainerProjectBench.this;
			parent = parentInventory;
			start = startSlot;
			size = inventorySize;
		}

		@Override
		public int getSizeInventory() {
			return size;
		}

		@Override
		public ItemStack getStackInSlot(int slot) {
			return parent.getStackInSlot(slot + start);
		}

		@Override
		public ItemStack decrStackSize(int slot, int amount) {
			ItemStack itemstack = parent.decrStackSize(slot + start, amount);
			if (itemstack != null) {
				ContainerProjectBench.this.onCraftMatrixChanged(this);
			}
			return itemstack;
		}

		@Override
		public ItemStack getStackInSlotOnClosing(int slot) {
			return parent.getStackInSlotOnClosing(slot + start);
		}

		@Override
		public void setInventorySlotContents(int slot, ItemStack ist) {
			parent.setInventorySlotContents(slot + start, ist);
			ContainerProjectBench.this.onCraftMatrixChanged(this);
		}

		@Override
		public String getInvName() {
			return parent.getInvName();
		}

		@Override
		public int getInventoryStackLimit() {
			return parent.getInventoryStackLimit();
		}

		@Override
		public void onInventoryChanged() {
			ContainerProjectBench.this.onCraftMatrixChanged(this);
			parent.onInventoryChanged();
		}

		@Override
		public boolean isUseableByPlayer(EntityPlayer entityplayer) {
			return false;
		}

		@Override
		public void openChest() {
		}

		@Override
		public void closeChest() {
		}

		@Override
		public boolean isInvNameLocalized() {
			return false;
		}

		@Override
		public boolean isItemValidForSlot(int i, ItemStack itemstack) {
			return true;
		}
	}

	public static class SlotPlan extends Slot {

		public SlotPlan(IInventory inventory, int i, int j, int k) {
			super(inventory, i, j, k);
		}

		public boolean a(ItemStack itemstack) {
			return itemstack.itemID == PBCore.itemPlanBlank.itemID
					|| itemstack.itemID == PBCore.itemPlanFull.itemID;
		}

		public int getSlotStackLimit() {
			return 1;
		}
	}

	TileEntityProjectBench projectbench;
	// SlotCraftRefill slotCraft;
	//public InventorySubCraft craftMatrix;
	public IInventory craftResult;
	public InventoryCrafting fakeInv;
	public int satisfyMask;

	public ContainerProjectBench(InventoryPlayer player, TileEntityProjectBench tileentity) {
		super();
		this.projectbench = tileentity;
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
