package net.slimevoid.collaborative.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.slimevoid.collaborative.items.ItemPlan;

public class SlotPlan extends Slot {

    public SlotPlan(IInventory inventory, int i, int j, int k) {
        super(inventory, i, j, k);
    }

    @Override
    // isItemValid
    public boolean isItemValid(ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemPlan;
    }

    public int getSlotStackLimit() {
        return 1;
    }
}
