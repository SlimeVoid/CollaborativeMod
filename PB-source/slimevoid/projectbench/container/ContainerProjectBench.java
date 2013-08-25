package slimevoid.projectbench.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import slimevoid.projectbench.core.PBCore;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;

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

		public InventorySubUpdate(IInventory parentInventory, int startSlot,
				int inventorySize) {
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

		@Override
		// isItemValid
		public boolean isItemValid(ItemStack itemstack) {
			return itemstack.itemID == PBCore.itemPlanBlank.itemID
			|| itemstack.itemID == PBCore.itemPlanFull.itemID;
		}

		public int getSlotStackLimit() {
			return 1;
		}
	}

	TileEntityProjectBench projectbench;
	SlotCraftRefill slotCraft;
	public IInventory craftResult = new InventoryCraftResult();
	public InventoryCrafting craftMatrix;
	private World worldObj;
	public int satisfyMask;

	public ContainerProjectBench(InventoryPlayer playerInventory, World world,
			TileEntityProjectBench tileentity) {
		super();
		this.projectbench = tileentity;
		this.craftMatrix = new InventorySubCraft(this, tileentity);
		this.worldObj = world;
		int b0 = 140;
		int l;
		int i1;

		// crafting matrix will have ghost inventory "slots" behind
		// This area should drop items stored here out into world or attempt to
		// store items into internal
		// inventory and then drop remaining items from the player
		for (l = 0; l < 3; ++l) {
			for (i1 = 0; i1 < 3; ++i1) {
				this.addSlotToContainer(new Slot(this.craftMatrix, i1 + l * 3,
						48 + i1 * 18, 18 + l * 18));
			}
		}
		IInventory[] sourceINventories = new IInventory[2];
		sourceINventories[0] = this.projectbench;
		sourceINventories[1] = playerInventory;
		// crafting result
		this.addSlotToContainer(new SlotCraftRefill(playerInventory.player,
				this.craftMatrix, this.craftResult, sourceINventories, this, 9,
				143, 35));

		// plan slot
		this.addSlotToContainer(new SlotPlan(tileentity, 9, 17, 36));
		// bench inventory
		for (l = 0; l < 2; ++l) {
			for (i1 = 0; i1 < tileentity.getSizeInventory() / 2; ++i1) {
				this.addSlotToContainer(new Slot(tileentity, i1 + l * 9 + 10,
						8 + i1 * 18, l * 18 + 90));
			}
		}
		// Player inventory
		for (l = 0; l < 3; ++l) {
			for (i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(playerInventory, i1 + l * 9
						+ 9, 8 + i1 * 18, l * 18 + b0));
			}
		}
		// hotbar inventory
		for (l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(playerInventory, l, 8 + l * 18,
					58 + b0));
		}
	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	public void onCraftMatrixChanged(IInventory inventory) {
		this.craftResult.setInventorySlotContents(
				0,
				CraftingManager.getInstance().findMatchingRecipe(
						this.craftMatrix, this.worldObj));
	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(EntityPlayer entityplayer) {
		// TODO :: try to shove as many items found in crafting matrix into
		// internal storage before dumping to world
		super.onContainerClosed(entityplayer);

		if (!this.worldObj.isRemote) {
			for (int i = 0; i < 9; ++i) {
				ItemStack itemstack = this.craftMatrix
						.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					entityplayer.dropPlayerItem(itemstack);
				}
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	public ItemStack[] getPlanItems() {
		ItemStack plan = this.projectbench.getStackInSlot(9);
		if (plan == null)
			return null;
		else
			return getShadowItems(plan);
	}

	public static ItemStack[] getShadowItems(ItemStack ist) {
		if (ist.stackTagCompound == null)
			return null;
		NBTTagList require = ist.stackTagCompound.getTagList("requires");
		if (require == null)
			return null;
		ItemStack tr[] = new ItemStack[9];
		for (int i = 0; i < require.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound) require.tagAt(i);
			ItemStack is2 = ItemStack.loadItemStackFromNBT(item);
			int sl = item.getByte("Slot");
			if (sl >= 0 && sl < 9)
				tr[sl] = is2;
		}

		return tr;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer entityplayer,
			int slotShiftClicked) {
		ItemStack itemstackCopy = null;
		Slot slot = (Slot) this.inventorySlots.get(slotShiftClicked);

		if (slot != null && slot.getHasStack()) {
			ItemStack stackInSlot = slot.getStack();
			itemstackCopy = stackInSlot.copy();

			if (slotShiftClicked < 2 * 9 + 11) {
				if (!this.mergeItemStack(stackInSlot, 2 * 9 + 11,
						this.inventorySlots.size(), true)) {
					return null;
				}
				slot.onSlotChange(stackInSlot, itemstackCopy);
			} else if (!this.mergeItemStack(stackInSlot, 10, 2 * 9 + 11, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			slot.onPickupFromSlot(entityplayer, stackInSlot);
		}

		return itemstackCopy;
	}

	public class InventorySubCraft extends InventoryCrafting {

		public InventorySubCraft(Container container, TileEntityProjectBench par) {
			super(container, 3, 3);
			parent = par;
			eventHandler = container;
		}

		public int getSizeInventory() {
			return 9;
		}

		public ItemStack getStackInSlot(int i) {
			if (i >= 9)
				return null;
			else
				return parent.getStackInSlot(i);
		}

		public ItemStack getStackInRowAndColumn(int i, int j) {
			if (i < 0 || i >= 3) {
				return null;
			} else {
				int k = i + j * 3;
				return getStackInSlot(k);
			}
		}

		public ItemStack decrStackSize(int i, int j) {
			ItemStack tr = parent.decrStackSize(i, j);
			if (tr != null)
				eventHandler.onCraftMatrixChanged(this);
			return tr;
		}

		public void setInventorySlotContents(int i, ItemStack ist) {
			parent.setInventorySlotContents(i, ist);
			eventHandler.onCraftMatrixChanged(this);
		}

		private Container eventHandler;
		private TileEntityProjectBench parent;
	}
}
