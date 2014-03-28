package net.slimevoid.collaborative.network.handler;

import net.slimevoid.collaborative.network.packet.PacketSettings;
import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.library.network.handlers.SubPacketHandler;

public class PacketSettingsHandler extends SubPacketHandler {

    @Override
    protected PacketUpdate createNewPacket() {
        return new PacketSettings();
    }

}
