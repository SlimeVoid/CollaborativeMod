package com.slimevoid.collaborative.network.packet;

import net.minecraft.world.World;
import net.slimevoid.library.network.PacketIds;
import net.slimevoid.library.network.PacketUpdate;

import com.slimevoid.collaborative.core.lib.CoreLib;

public class PacketSettings extends PacketUpdate {

    public PacketSettings() {
        super(PacketIds.PLAYER);
        this.setChannel(CoreLib.MOD_CHANNEL);
    }

    @Override
    public boolean targetExists(World world) {
        return false;
    }

    public boolean getInventoryMode() {
        return this.side == 1;
    }

}
