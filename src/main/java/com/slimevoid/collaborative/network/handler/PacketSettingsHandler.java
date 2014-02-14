package com.slimevoid.collaborative.network.handler;

import com.slimevoid.collaborative.network.packet.PacketSettings;
import com.slimevoid.library.network.PacketUpdate;
import com.slimevoid.library.network.handlers.SubPacketHandler;

public class PacketSettingsHandler extends SubPacketHandler {

    @Override
    protected PacketUpdate createNewPacket() {
        return new PacketSettings();
    }

}
