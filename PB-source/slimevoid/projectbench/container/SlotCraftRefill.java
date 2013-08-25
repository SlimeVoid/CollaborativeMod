package slimevoid.projectbench.container;

import slimevoid.projectbench.core.lib.ItemLib;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

// Referenced classes of package com.eloraam.redpower.base:
//            ContainerAdvBench

public class SlotCraftRefill extends SlotCrafting {

	IInventory allSlots;
	IInventory craftingMatrix;
	ContainerProjectBench eventHandler;

	public SlotCraftRefill(EntityPlayer entityplayer, IInventory matrix,
			IInventory result, IInventory all, ContainerProjectBench evh,
			int i, int j, int k) {
		super(entityplayer, matrix, result, i, j, k);
		allSlots = all;
		craftingMatrix = matrix;
		eventHandler = evh;
	}

	private int findMatch(ItemStack itemstack) {
		for (int i = 0; i < 18; i++) {
			ItemStack test = allSlots.getStackInSlot(10 + i);
			if (test != null && test.stackSize != 0
					&& ItemLib.matchOre(itemstack, test))
				return 10 + i;
		}

		return -1;
	}

	public boolean isLastUse() {
		int bits = 0;
		for (int i = 0; i < 9; i++) {
			ItemStack st = allSlots.getStackInSlot(i);
			if (st == null) {
				bits |= 1 << i;
				continue;
			}
			if (!st.isStackable()) {
				bits |= 1 << i;
				continue;
			}
			if (st.stackSize > 1)
				bits |= 1 << i;
		}

		if (bits == 511)
			return false;
		for (int i = 0; i < 18; i++) {
			ItemStack test = allSlots.getStackInSlot(10 + i);
			if (test == null || test.stackSize == 0)
				continue;
			int sc = test.stackSize;
			for (int j = 0; j < 9; j++) {
				if ((bits & 1 << j) > 0)
					continue;
				ItemStack st = allSlots.getStackInSlot(j);
				if (st == null || !ItemLib.matchOre(st, test))
					continue;
				bits |= 1 << j;
				if (--sc == 0)
					break;
			}

		}

		return bits != 511;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack ist) {
		ItemStack plan[] = eventHandler.getPlanItems();
		ItemStack cur[] = new ItemStack[9];
		for (int i = 0; i < 9; i++) {
			ItemStack st = allSlots.getStackInSlot(i);
			if (st == null)
				cur[i] = null;
			else
				cur[i] = st.copy();
		}

		boolean last = isLastUse();
		if (plan != null) {
			for (int i = 0; i < 9; i++) {
				if (cur[i] != null || plan[i] == null)
					continue;
				int p = findMatch(plan[i]);
				if (p < 0)
					continue;
				ItemStack is2 = allSlots.getStackInSlot(p);
				if (is2 == null)
					continue;
				allSlots.decrStackSize(p, 1);
				if (is2.getItem().hasContainerItem()) {
					ItemStack is3 = is2.getItem().getContainerItemStack(is2);
					allSlots.setInventorySlotContents(p, is3);
				}
			}

		}
		super.onPickupFromSlot(player, ist);
		if (last) {
			eventHandler.onCraftMatrixChanged(craftingMatrix);
			return;
		}
		//boolean ch = false;
		for (int i = 0; i < 9; i++) {
			if (cur[i] == null)
				continue;
			ItemStack nsl = allSlots.getStackInSlot(i);
			if (plan != null && plan[i] != null)
				continue;
			int idx;
			if (nsl != null) {
				if (ItemLib.matchOre(nsl, cur[i]) || !cur[i].getItem().hasContainerItem())
					continue;
				ItemStack ctr = cur[i].getItem().getContainerItemStack(cur[i]);
				if (ctr == null || ctr.getItem().itemID != nsl.getItem().itemID)
					continue;
				idx = findMatch(cur[i]);
				if (idx >= 0) {
					ItemStack i1 = allSlots.getStackInSlot(idx);
					allSlots.setInventorySlotContents(idx, nsl);
					allSlots.setInventorySlotContents(i, i1);
					//ch = true;
				}
				continue;
			}
			idx = findMatch(cur[i]);
			if (idx >= 0) {
				ItemStack i1 = allSlots.getStackInSlot(idx);
				allSlots.setInventorySlotContents(i, allSlots.decrStackSize(idx, 1));
				//ch = true;
			}
		}

		eventHandler.onCraftMatrixChanged(craftingMatrix);
	}
}
