package com.slimevoid.collaborative.network.packet;

import com.slimevoid.collaborative.core.lib.CoreLib;
import com.slimevoid.library.network.PacketGuiEvent;
import com.slimevoid.library.util.helpers.SlimevoidHelper;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
        return world.blockExists(this.xPosition,
                                 this.yPosition,
                                 this.zPosition);
    }

    public TileEntity getTarget(World world) {
        return SlimevoidHelper.getBlockTileEntity(world,
                                                  this.xPosition,
                                                  this.yPosition,
                                                  this.zPosition);
    }

}
