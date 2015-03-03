package net.slimevoid.collaborative.network.packet.executor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.slimevoid.collaborative.container.ContainerWorkBench;
import net.slimevoid.collaborative.core.lib.ConfigurationLib;
import net.slimevoid.collaborative.network.packet.PacketSettings;
import net.slimevoid.library.IPacketExecutor;
import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.library.network.executor.PacketExecutor;

public class PacketSettingsExecutor extends PacketExecutor {

    @Override
    public PacketUpdate execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
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
        return null;
    }

}
