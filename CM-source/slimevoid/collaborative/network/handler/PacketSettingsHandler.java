package slimevoid.collaborative.network.handler;

import slimevoid.collaborative.network.packet.PacketSettings;
import slimevoidlib.network.PacketUpdate;
import slimevoidlib.network.handlers.SubPacketHandler;

public class PacketSettingsHandler extends SubPacketHandler {

	@Override
	protected PacketUpdate createNewPacket() {
		return new PacketSettings();
	}

}
