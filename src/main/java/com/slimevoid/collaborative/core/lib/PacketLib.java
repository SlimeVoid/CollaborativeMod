package com.slimevoid.collaborative.core.lib;

import com.slimevoid.collaborative.network.handler.PacketGuiHandler;
import com.slimevoid.collaborative.network.handler.PacketSettingsHandler;
import com.slimevoid.collaborative.network.packet.PacketSettings;
import com.slimevoid.collaborative.network.packet.executor.PacketGuiExecutor;
import com.slimevoid.collaborative.network.packet.executor.PacketSettingsExecutor;
import com.slimevoid.library.network.PacketIds;
import com.slimevoid.library.network.handlers.PacketHandler;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketLib {

    @SideOnly(Side.CLIENT)
    public static void registerClientPacketHandlers() {

    }

    public static void registerPacketHandlers() {
        /*
         * Create new Packet Handler
         */
        PacketHandler handler = new PacketHandler();

        /*
         * Regiter SubPacket handlers
         */
        PacketGuiHandler packetGuiHandler = new PacketGuiHandler();
        packetGuiHandler.registerPacketHandler(CommandLib.CREATE_PLAN,
                                               new PacketGuiExecutor());

        handler.registerPacketHandler(PacketIds.GUI,
                                      packetGuiHandler);

        PacketSettingsHandler packetSettingsHandler = new PacketSettingsHandler();
        packetSettingsHandler.registerPacketHandler(CommandLib.UPDATE_SETTINGS,
                                                    new PacketSettingsExecutor());

        handler.registerPacketHandler(PacketIds.PLAYER,
                                      packetSettingsHandler);

        /*
         * Register Listeners
         */
        PacketHandler.listener = NetworkRegistry.INSTANCE.newEventDrivenChannel(CoreLib.MOD_CHANNEL);
        PacketHandler.listener.register(handler);
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
