package net.slimevoid.collaborative.tileentity;

import net.minecraft.block.Block;
import net.slimevoid.collaborative.core.lib.ConfigurationLib;
import net.slimevoid.library.tileentity.TileEntityBase;

public abstract class TileEntityCollaborativeBase extends TileEntityBase {

    @Override
    public Block getBlockType() {
        return ConfigurationLib.blockCollaborativeBase;
    }
}
