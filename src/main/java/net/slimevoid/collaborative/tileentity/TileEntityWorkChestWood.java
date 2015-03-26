package net.slimevoid.collaborative.tileentity;

import net.slimevoid.collaborative.core.lib.BlockLib;

public class TileEntityWorkChestWood extends TileEntityWorkChestBase {

    @Override
    public int getExtendedBlockID() {
        return BlockLib.WORK_CHEST_WOOD_ID;
    }

}
