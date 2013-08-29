package slimevoid.collaborative.network.handler;

import slimevoid.collaborative.network.packet.PacketGui;
import slimevoidlib.network.PacketUpdate;
import slimevoidlib.network.handlers.SubPacketHandler;

public class PacketGuiHandler extends SubPacketHandler {

	@Override
	protected PacketUpdate createNewPacket() {
		return new PacketGui();
	}

}
