package slimevoid.collaborative.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import slimevoid.collaborative.core.lib.CommandLib;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.core.lib.ContainerLib;
import slimevoid.collaborative.network.packet.PacketGui;
import slimevoid.collaborative.tileentity.TileEntityWorkBench;
import slimevoid.collaborative.tileentity.TileEntityWorkChest;

public class ContainerWorkChest extends Container {

	TileEntityWorkChest workchest;
	public InventoryPlayer playerInventory;

	public ContainerWorkChest(InventoryPlayer playerInventory, TileEntityWorkChest tileentity) {
		super();
		this.workchest = tileentity;
		this.playerInventory = playerInventory;
		/*
		 * Place Holders for additional inventories
		 */
		
		int b0 = 140;
		int l;
		int i1;

		// chest inventory
		for (l = 0; l < 4; ++l) {
			for (i1 = 0; i1 < 9; ++i1) {
				int slotIndex = i1 + (l * 9);
				this.addSlotToContainer(new Slot(tileentity, slotIndex, 8 + i1 * 18, l * 18 + 90));
			}
		}
		
		// Player inventory
		for (l = 0; l < 3; ++l) {
			for (i1 = 0; i1 < 9; ++i1) {
				int slotIndex = 9 + i1 + (l * 9);
				this.addSlotToContainer(new Slot(playerInventory, slotIndex, 8 + i1 * 18, l * 18 + b0));
			}
		}
		
		// hotbar inventory
		for (l = 0; l < 9; ++l) {
			int slotIndex = l;
			this.addSlotToContainer(new Slot(playerInventory, slotIndex, 8 + l * 18, 58 + b0));
		}
		
	}	   
	

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.workchest.isUseableByPlayer(entityplayer);
	}
	
	public boolean putPlanInSlot(ItemStack stackInSource, int validSlots, int targetSlot, boolean force, EntityPlayer entityplayer) {
		// TODO :: Plan Slot Shift Click logic
		Slot slot = (Slot) this.inventorySlots.get(validSlots);
		if (slot.getStack() != null) {
			transferStackInSlot(entityplayer, validSlots);
		}
		
		if (stackInSource.stackSize == 1) {		
			return this.mergeItemStack(stackInSource, validSlots, targetSlot, force);
		} else {
			stackInSource.stackSize -= 1;
			ItemStack destinationStack = stackInSource.copy();
			destinationStack.stackSize = 1;
			slot.putStack(destinationStack);			
			return true;
		}
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityplayer,
			int slotShiftClicked) {
		ItemStack itemstackCopy = null;
		Slot slot = (Slot) this.inventorySlots.get(slotShiftClicked);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack stackInSlot = slot.getStack();
			itemstackCopy = stackInSlot.copy();
			if (slotShiftClicked != 9 && (stackInSlot.itemID == ConfigurationLib.itemPlanBlank.itemID
					|| stackInSlot.itemID == ConfigurationLib.itemPlanFull.itemID)){
				if (!this.putPlanInSlot(stackInSlot, 9, 10, true, entityplayer)) {//try to place into plan slot					
					if ((slotShiftClicked >= 11 && slotShiftClicked < 29)||!this.mergeItemStack(stackInSlot, 11, 29, false)) {//else place in internal inventory
						if ((slotShiftClicked >= 29)||!this.mergeItemStack(stackInSlot, 29, 65, false)) {//else place in player inventory
							return null;
						}
					}
				}				
			} else if (slotShiftClicked < 9 || slotShiftClicked == 10) {
				if (!this.mergeItemStack(stackInSlot, 11, 65, true)) {
					return null;
				}
			} else if (slotShiftClicked < 29) { //if internal inventory shift click into player inventory
				if (!this.mergeItemStack(stackInSlot, 29, 65, true)) {					
						return null;					
				}
			} else if (!this.mergeItemStack(stackInSlot, 11, 29, false)) { //if player then go into internal inventory first
				if (!this.mergeItemStack(stackInSlot, 0, 9, false)){//then crafting grid					
					return null;					
				}
			}
			if (stackInSlot.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			if (stackInSlot.stackSize != itemstackCopy.stackSize) {
				slot.onPickupFromSlot(entityplayer, stackInSlot);
			} else {
				return null;
			}
		}
		return itemstackCopy;
	}
	
}
