package com.slimevoid.collaborative.network.packet;

import com.slimevoid.collaborative.core.lib.CoreLib;
import com.slimevoid.library.network.PacketIds;
import com.slimevoid.library.network.PacketUpdate;

import net.minecraft.world.World;

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
