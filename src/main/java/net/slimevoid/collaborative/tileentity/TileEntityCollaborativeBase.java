package net.slimevoid.collaborative.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.slimevoid.collaborative.core.lib.ConfigurationLib;
import net.slimevoid.library.blocks.BlockBase;
import net.slimevoid.library.tileentity.TileEntityBase;

public abstract class TileEntityCollaborativeBase extends TileEntityBase {

    @Override
    public Block getBlockType() {
        return ConfigurationLib.blockCollaborativeBase;
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, BlockBase blockBase) {
        return state;
    }
}
