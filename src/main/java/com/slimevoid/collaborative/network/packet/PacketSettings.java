package com.slimevoid.collaborative.network.packet;

import net.minecraft.world.World;

import com.slimevoid.collaborative.core.lib.CoreLib;
import com.slimevoid.library.network.PacketIds;
import com.slimevoid.library.network.PacketUpdate;

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
