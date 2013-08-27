package slimevoid.projectbench.network.packet.executor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import slimevoid.projectbench.container.ContainerProjectBench;
import slimevoid.projectbench.core.lib.ConfigurationLib;
import slimevoid.projectbench.network.packet.PacketProjectSettings;
import slimevoidlib.IPacketExecutor;
import slimevoidlib.network.PacketUpdate;

public class PacketProjectSettingsExecutor implements IPacketExecutor {

	@Override
	public void execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
		if (packet instanceof PacketProjectSettings) {
			PacketProjectSettings packetSettings = (PacketProjectSettings) packet;
			ConfigurationLib.updatePlayersInventoryLocked(entityplayer, packetSettings.getInventoryMode());
			if (entityplayer.openContainer != null && entityplayer.openContainer instanceof ContainerProjectBench) {
				ContainerProjectBench bench = ((ContainerProjectBench) entityplayer.openContainer);
				bench.onCraftMatrixChanged(bench.playerInventory);
			}
		}
	}

}
