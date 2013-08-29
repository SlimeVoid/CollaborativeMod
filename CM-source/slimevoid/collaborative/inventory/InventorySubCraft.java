package slimevoid.collaborative.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import slimevoid.collaborative.tileentity.TileEntityWorkBench;

public class InventorySubCraft extends InventoryCrafting {
	private Container eventHandler;
	private TileEntityWorkBench parent;

	public InventorySubCraft(Container container, TileEntityWorkBench par) {
		super(container, 3, 3);
		parent = par;
		eventHandler = container;
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i >= 9) {
			return null;
		} else {
			return parent.getStackInSlot(i);
		}
	}

	@Override
	public ItemStack getStackInRowAndColumn(int i, int j) {
		if (i < 0 || i >= 3) {
			return null;
		} else {
			int k = i + j * 3;
			return getStackInSlot(k);
		}
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack tr = parent.decrStackSize(i, j);
		if (tr != null) {
			eventHandler.onCraftMatrixChanged(this);
		}
		return tr;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack ist) {
		parent.setInventorySlotContents(i, ist);
		eventHandler.onCraftMatrixChanged(this);
	}
}
