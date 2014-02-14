package com.slimevoid.collaborative.tileentity;

import com.slimevoid.collaborative.core.lib.ConfigurationLib;
import com.slimevoid.library.tileentity.TileEntityBase;

import net.minecraft.block.Block;

public abstract class TileEntityCollaborativeBase extends TileEntityBase {

    @Override
    public Block getBlockType() {
        return ConfigurationLib.blockCollaborativeBase;
    }
}
