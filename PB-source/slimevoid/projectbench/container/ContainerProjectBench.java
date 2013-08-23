package slimevoid.projectbench.container;

import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerProjectBench extends Container {

	public ContainerProjectBench(InventoryPlayer player, TileEntityProjectBench tileentity) {
		super();
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		// TODO :: Auto-generated method stub
		return false;
	}

}
