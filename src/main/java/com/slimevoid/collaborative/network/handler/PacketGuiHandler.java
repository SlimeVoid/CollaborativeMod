package com.slimevoid.collaborative.network.handler;

import com.slimevoid.collaborative.network.packet.PacketGui;
import com.slimevoid.library.network.PacketUpdate;
import com.slimevoid.library.network.handlers.SubPacketHandler;

public class PacketGuiHandler extends SubPacketHandler {

    @Override
    protected PacketUpdate createNewPacket() {
        return new PacketGui();
    }

}
