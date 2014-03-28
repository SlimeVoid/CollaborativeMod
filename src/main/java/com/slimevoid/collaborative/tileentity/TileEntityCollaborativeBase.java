package com.slimevoid.collaborative.tileentity;

import com.slimevoid.collaborative.core.lib.ConfigurationLib;

import net.minecraft.block.Block;
import net.slimevoid.library.tileentity.TileEntityBase;

public abstract class TileEntityCollaborativeBase extends TileEntityBase {

    @Override
    public Block getBlockType() {
        return ConfigurationLib.blockCollaborativeBase;
    }
}
