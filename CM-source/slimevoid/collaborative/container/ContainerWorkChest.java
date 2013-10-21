package slimevoid.collaborative.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.tileentity.TileEntityWorkChest;

public class ContainerWorkChest extends Container {

	private int				numRows;
	TileEntityWorkChest		workchest;
	public InventoryPlayer	playerInventory;

	public ContainerWorkChest(IInventory playerInventory, IInventory tileentity) {
		super();
		this.workchest = (TileEntityWorkChest) tileentity;
		this.playerInventory = (InventoryPlayer) playerInventory;
		this.numRows = tileentity.getSizeInventory() / 9;
		/*
		 * Place Holders for additional inventories
		 */

		int offSet = 140;

		// chest inventory
		for (int row = 0; row < 6; ++row) {
			for (int column = 0; column < 9; ++column) {
				int slotIndex = column + (row * 9);
				this.addSlotToContainer(new Slot(tileentity, slotIndex, 8 + column * 18, row * 18 + 18));
			}
		}

		// Player inventory
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				int slotIndex = 9 + column + (row * 9);
				this.addSlotToContainer(new Slot(playerInventory, slotIndex, 8 + column * 18, row
																								* 18
																								+ offSet));
			}
		}

		// hotbar inventory
		for (int row = 0; row < 9; ++row) {
			int slotIndex = row;
			this.addSlotToContainer(new Slot(playerInventory, slotIndex, 8 + row * 18, 58 + offSet));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.workchest.isUseableByPlayer(entityplayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotShiftClicked) {
		ItemStack stackCopy = null;
		Slot slot = (Slot) this.inventorySlots.get(slotShiftClicked);

		if (slot != null && slot.getHasStack()) {
			ItemStack stackInSlot = slot.getStack();
			if (stackInSlot.itemID == ConfigurationLib.itemPlanFull.itemID) {
				stackCopy = stackInSlot.copy();
				if (slotShiftClicked < this.numRows * 9) {
					if (!this.mergeItemStack(	stackInSlot,
												this.numRows * 9,
												this.inventorySlots.size(),
												true)) {
						return null;
					}
				} else if (!this.mergeItemStack(stackInSlot,
												0,
												this.numRows * 9,
												false)) {
					return null;
				}

				if (stackInSlot.stackSize == 0) {
					slot.putStack((ItemStack) null);
				} else {
					slot.onSlotChanged();
				}
			}
		}

		return stackCopy;
	}
}
