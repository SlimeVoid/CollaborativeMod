package slimevoid.collaborative.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import slimevoid.collaborative.core.lib.ConfigurationLib;

public class SlotPlan extends Slot {

	public SlotPlan(IInventory inventory, int i, int j, int k) {
		super(inventory, i, j, k);
	}

	@Override
	// isItemValid
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.itemID == ConfigurationLib.itemPlanBlank.itemID
		|| itemstack.itemID == ConfigurationLib.itemPlanFull.itemID;
	}

	public int getSlotStackLimit() {
		return 1;
	}
}
