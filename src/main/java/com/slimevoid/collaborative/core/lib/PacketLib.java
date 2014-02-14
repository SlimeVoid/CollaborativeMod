package com.slimevoid.collaborative.core.lib;

import com.slimevoid.collaborative.network.PacketHandler;
import com.slimevoid.collaborative.network.handler.PacketGuiHandler;
import com.slimevoid.collaborative.network.handler.PacketSettingsHandler;
import com.slimevoid.collaborative.network.packet.PacketSettings;
import com.slimevoid.collaborative.network.packet.executor.PacketGuiExecutor;
import com.slimevoid.collaborative.network.packet.executor.PacketSettingsExecutor;
import com.slimevoid.library.network.PacketIds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketLib {

    @SideOnly(Side.CLIENT)
    public static void registerClientPacketHandlers() {

    }

    public static void registerPacketHandlers() {
        PacketGuiHandler packetGuiHandler = new PacketGuiHandler();
        packetGuiHandler.registerPacketHandler(CommandLib.CREATE_PLAN,
                                               new PacketGuiExecutor());

        PacketHandler.registerPacketHandler(PacketIds.GUI,
                                            packetGuiHandler);

        PacketSettingsHandler packetSettingsHandler = new PacketSettingsHandler();
        packetSettingsHandler.registerPacketHandler(CommandLib.UPDATE_SETTINGS,
                                                    new PacketSettingsExecutor());

        PacketHandler.registerPacketHandler(PacketIds.PLAYER,
                                            packetSettingsHandler);
    }

    public static void sendPlayerInventoryStatus(boolean newVal) {
        PacketSettings packet = new PacketSettings();
        packet.setPosition(0,
                           0,
                           0,
                           newVal ? 1 : 0);
        packet.setCommand(CommandLib.UPDATE_SETTINGS);
        PacketHandler.listener.sendToServer(packet.getPacket());
    }

}
