package net.slimevoid.collaborative.network.packet;

import net.minecraft.world.World;
import net.slimevoid.collaborative.core.lib.CoreLib;
import net.slimevoid.library.network.PacketIds;
import net.slimevoid.library.network.PacketUpdate;

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
