package com.slimevoid.collaborative.client.proxy;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.slimevoid.collaborative.client.presentation.gui.GuiCollaborativeWorkBench;
import com.slimevoid.collaborative.client.presentation.gui.GuiCollaborativeWorkChest;
import com.slimevoid.collaborative.container.ContainerWorkBench;
import com.slimevoid.collaborative.container.ContainerWorkChest;
import com.slimevoid.collaborative.core.lib.ConfigurationLib;
import com.slimevoid.collaborative.core.lib.GuiLib;
import com.slimevoid.collaborative.core.lib.PacketLib;
import com.slimevoid.collaborative.proxy.CommonProxy;
import com.slimevoid.collaborative.tileentity.TileEntityWorkBench;
import com.slimevoid.collaborative.tileentity.TileEntityWorkChestBase;
import com.slimevoid.library.util.helpers.BlockHelper;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerConfigurationProperties(File configFile) {
        ConfigurationLib.ClientConfig(configFile);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiLib.GUIID_WORK_BENCH) {
            TileEntityWorkBench tileentity = (TileEntityWorkBench) BlockHelper.getTileEntity(world,
                                                                                             x,
                                                                                             y,
                                                                                             z,
                                                                                             TileEntityWorkBench.class);
            if (tileentity != null) {
                return new GuiCollaborativeWorkBench(new ContainerWorkBench(player.inventory, tileentity));
            }
        } else if (ID == GuiLib.GUIID_WORK_CHEST) {
            TileEntityWorkChestBase tileentity = (TileEntityWorkChestBase) BlockHelper.getTileEntity(world,
                                                                                                     x,
                                                                                                     y,
                                                                                                     z,
                                                                                                     TileEntityWorkChestBase.class);
            if (tileentity != null) {
                return new GuiCollaborativeWorkChest(new ContainerWorkChest(player.inventory, tileentity));
            }
        }
        return null;
    }

    @Override
    public void init() {
        super.init();
        PacketLib.registerClientPacketHandlers();
    }

    // TODO :: Send Server Settings to Client
    // @Override
    // public void clientLoggedIn(NetHandler clientHandler, INetworkManager
    // manager, Packet1Login login) {
    // // This Sends the Local Player settings to the server, for persistent
    // // user settings
    // PacketSettings packet = new PacketSettings();
    // packet.setCommand(CommandLib.UPDATE_SETTINGS);
    // packet.setPosition(0,
    // 0,
    // 0,
    // ConfigurationLib.playerInventoryLocked ? 1 : 0);
    // PacketDispatcher.sendPacketToServer(packet.getPacket());
    // }

}
