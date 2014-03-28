package net.slimevoid.collaborative.network.packet.executor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.collaborative.container.ContainerWorkBench;
import net.slimevoid.collaborative.network.packet.PacketGui;
import net.slimevoid.collaborative.tileentity.TileEntityWorkBench;
import net.slimevoid.library.IPacketExecutor;
import net.slimevoid.library.network.PacketUpdate;

public class PacketGuiExecutor implements IPacketExecutor {

    @Override
    public void execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
        if (packet instanceof PacketGui) {
            PacketGui packetGui = (PacketGui) packet;
            if (packet.targetExists(world)) {
                TileEntity tileentity = packetGui.getTarget(world);
                if (tileentity != null
                    && tileentity instanceof TileEntityWorkBench) {
                    if (entityplayer.openContainer instanceof ContainerWorkBench) {
                        ((ContainerWorkBench) entityplayer.openContainer).handleGuiEvent(packetGui);
                    }
                }
            }
        }
    }

}
