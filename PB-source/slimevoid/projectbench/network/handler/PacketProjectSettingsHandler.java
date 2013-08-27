package slimevoid.projectbench.network.handler;

import slimevoid.projectbench.network.packet.PacketProjectSettings;
import slimevoidlib.network.PacketUpdate;
import slimevoidlib.network.handlers.SubPacketHandler;

public class PacketProjectSettingsHandler extends SubPacketHandler {

	@Override
	protected PacketUpdate createNewPacket() {
		return new PacketProjectSettings();
	}

}
