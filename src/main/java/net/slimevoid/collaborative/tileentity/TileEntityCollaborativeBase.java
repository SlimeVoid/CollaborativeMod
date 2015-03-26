package net.slimevoid.collaborative.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.slimevoid.collaborative.core.lib.ConfigurationLib;
import net.slimevoid.library.blocks.BlockBase;
import net.slimevoid.library.blocks.state.BlockStates;
import net.slimevoid.library.tileentity.TileEntityBase;

public abstract class TileEntityCollaborativeBase extends TileEntityBase {

    @Override
    public Block getBlockType() {
        return ConfigurationLib.blockCollaborativeBase;
    }

    @Override
    public IBlockState getActualState(IBlockState state, BlockBase blockBase) {
        return this.getExtendedState(state, blockBase);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, BlockBase blockBase) {
        return state.withProperty(BlockStates.FACING, this.getFacing());
    }

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentTranslation(this.getInvName());
    }
}
