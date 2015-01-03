package net.slimevoid.collaborative.tileentity;

import net.minecraft.util.IChatComponent;
import net.slimevoid.collaborative.core.lib.BlockLib;

public class TileEntityWorkChestWood extends TileEntityWorkChestBase {

    @Override
    public int getExtendedBlockID() {
        return BlockLib.WORK_CHEST_WOOD_ID;
    }

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}

}
