package slimevoid.projectbench.network.packet.executor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoid.projectbench.container.ContainerProjectBench;
import slimevoid.projectbench.network.packet.PacketProjectGui;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import slimevoidlib.IPacketExecutor;
import slimevoidlib.network.PacketUpdate;

public class PacketProjectGuiExecutor implements IPacketExecutor {

	@Override
	public void execute(PacketUpdate packet, World world,
			EntityPlayer entityplayer) {
		if (packet instanceof PacketProjectGui) {
			PacketProjectGui packetGui = (PacketProjectGui) packet;
			if (packet.targetExists(world)) {
				TileEntity tileentity = packetGui.getTarget(world);
				if (tileentity != null && tileentity instanceof TileEntityProjectBench) {
					if (entityplayer.openContainer instanceof ContainerProjectBench) {
						((ContainerProjectBench) entityplayer.openContainer).handleGuiEvent(packetGui);
					}
				}
			}
		}
	}

}
