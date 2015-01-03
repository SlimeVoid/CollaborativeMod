package net.slimevoid.collaborative.network.packet;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.slimevoid.collaborative.core.lib.CoreLib;
import net.slimevoid.library.network.PacketGuiEvent;
import net.slimevoid.library.util.helpers.SlimevoidHelper;

public class PacketGui extends PacketGuiEvent {

    public PacketGui() {
        super();
        this.setChannel(CoreLib.MOD_CHANNEL);
    }

    public PacketGui(int x, int y, int z, String command, int guiID) {
        this();
        this.setPosition(x,
                         y,
                         z,
                         0);
        this.setCommand(command);
        this.setGuiID(guiID);
    }

    @Override
    public boolean targetExists(World world) {
        return !(world.getBlockState(new BlockPos(this.xPosition,
                                 this.yPosition,
                                 this.zPosition)).getBlock().equals(Blocks.air));
    }

    public TileEntity getTarget(World world) {
        return SlimevoidHelper.getBlockTileEntity(world,
                                                  new BlockPos(this.xPosition,
                                                  this.yPosition,
                                                  this.zPosition));
    }

}
