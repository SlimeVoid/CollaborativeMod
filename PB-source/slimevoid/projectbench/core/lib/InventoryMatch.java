package slimevoid.projectbench.core.lib;

import net.minecraft.inventory.IInventory;

public class InventoryMatch {
	public IInventory InventoryMatch=null;
	public int slotIndex = -1;
	
	public InventoryMatch(IInventory matchingInventory,int slotIndex){
		this.InventoryMatch=matchingInventory;
		this.slotIndex=slotIndex;
	}
}
