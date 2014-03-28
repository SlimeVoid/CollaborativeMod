package net.slimevoid.collaborative.network.handler;

import net.slimevoid.collaborative.network.packet.PacketGui;
import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.library.network.handlers.SubPacketHandler;

public class PacketGuiHandler extends SubPacketHandler {

    @Override
    protected PacketUpdate createNewPacket() {
        return new PacketGui();
    }

}
