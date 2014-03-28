package com.slimevoid.collaborative.container;

import com.slimevoid.collaborative.container.slot.SlotPlan;
import com.slimevoid.collaborative.core.lib.ConfigurationLib;
import com.slimevoid.collaborative.tileentity.TileEntityWorkChestBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.slimevoid.library.inventory.ContainerBase;

public class ContainerWorkChest extends ContainerBase {

    private int numRows;

    public ContainerWorkChest(InventoryPlayer playerInventory, TileEntityWorkChestBase tileentity) {
        super(playerInventory, tileentity, tileentity.getWorldObj(), 0, 140);
        this.numRows = tileentity.getSizeInventory() / 9;
    }

    @Override
    public void bindLocalInventory() {

        // Chest inventory
        for (int row = 0; row < 6; ++row) {
            for (int column = 0; column < 9; ++column) {
                int slotIndex = column + (row * 9);
                this.addSlotToContainer(new SlotPlan(this.customInventory, slotIndex, 8 + column * 18, row * 18 + 18));
            }
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotShiftClicked) {
        ItemStack stackCopy = null;
        Slot slot = (Slot) this.inventorySlots.get(slotShiftClicked);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            if (this.isPlan(stackInSlot)) {
                stackCopy = stackInSlot.copy();
                if (slotShiftClicked < this.numRows * 9) {
                    if (!this.mergeItemStack(stackInSlot,
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

    private boolean isPlan(ItemStack stackInSlot) {
        // TODO Auto-generated method stub
        return stackInSlot.getItem().getClass().isInstance(ConfigurationLib.itemPlanFull.getClass());
    }
}
