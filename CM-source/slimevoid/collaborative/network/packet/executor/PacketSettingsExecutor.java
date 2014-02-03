package slimevoid.collaborative.network.packet.executor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import slimevoid.collaborative.container.ContainerWorkBench;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.network.packet.PacketSettings;
import slimevoidlib.IPacketExecutor;
import slimevoidlib.network.PacketUpdate;

public class PacketSettingsExecutor implements IPacketExecutor {

    @Override
    public void execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
        if (packet instanceof PacketSettings) {
            PacketSettings packetSettings = (PacketSettings) packet;
            ConfigurationLib.updatePlayersInventoryLocked(entityplayer,
                                                          packetSettings.getInventoryMode());
            if (entityplayer.openContainer != null
                && entityplayer.openContainer instanceof ContainerWorkBench) {
                ContainerWorkBench bench = ((ContainerWorkBench) entityplayer.openContainer);
                bench.onCraftMatrixChanged(bench.getPlayerInventory());
            }
        }
    }

}
