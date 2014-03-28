package net.slimevoid.collaborative.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.slimevoid.collaborative.tileentity.TileEntityWorkBench;

public class InventorySubCraft extends InventoryCrafting {
    private TileEntityWorkBench parent;

    public InventorySubCraft(Container container, TileEntityWorkBench bench) {
        super(container, 3, 3);
        this.parent = bench;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        if (i >= 9) {
            return null;
        } else {
            return this.parent.getStackInSlot(i);
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
        ItemStack tr = this.parent.decrStackSize(i,
                                                 j);
        if (tr != null) {
        }
        return tr;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack ist) {
        this.parent.setInventorySlotContents(i,
                                             ist);
    }
}
