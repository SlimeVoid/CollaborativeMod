package slimevoid.projectbench.network.handler;

import slimevoid.projectbench.network.packet.PacketProjectGui;
import slimevoidlib.network.PacketUpdate;
import slimevoidlib.network.handlers.SubPacketHandler;

public class PacketProjectGuiHandler extends SubPacketHandler {

	@Override
	protected PacketUpdate createNewPacket() {
		return new PacketProjectGui();
	}

}
