package com.slimevoid.collaborative.inventory;

import net.minecraft.inventory.IInventory;

public class InventoryMatch {
    public IInventory inventoryMatch = null;
    public int        slotIndex      = -1;

    public InventoryMatch(IInventory matchingInventory, int slotIndex) {
        this.inventoryMatch = matchingInventory;
        this.slotIndex = slotIndex;
    }
}
