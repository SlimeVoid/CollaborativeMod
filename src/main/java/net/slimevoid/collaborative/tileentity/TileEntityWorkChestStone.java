package net.slimevoid.collaborative.tileentity;

import net.minecraft.util.IChatComponent;
import net.slimevoid.collaborative.core.lib.BlockLib;

public class TileEntityWorkChestStone extends TileEntityWorkChestBase {

    @Override
    public int getExtendedBlockID() {
        return BlockLib.WORK_CHEST_STONE_ID;
    }

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}

}
