package com.slimevoid.collaborative.network.handler;

import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.library.network.handlers.SubPacketHandler;

import com.slimevoid.collaborative.network.packet.PacketSettings;

public class PacketSettingsHandler extends SubPacketHandler {

    @Override
    protected PacketUpdate createNewPacket() {
        return new PacketSettings();
    }

}
