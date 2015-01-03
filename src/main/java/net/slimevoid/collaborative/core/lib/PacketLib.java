package net.slimevoid.collaborative.core.lib;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slimevoid.collaborative.network.handler.PacketGuiHandler;
import net.slimevoid.collaborative.network.handler.PacketSettingsHandler;
import net.slimevoid.collaborative.network.packet.PacketSettings;
import net.slimevoid.collaborative.network.packet.executor.PacketGuiExecutor;
import net.slimevoid.collaborative.network.packet.executor.PacketSettingsExecutor;
import net.slimevoid.library.network.PacketIds;
import net.slimevoid.library.network.handlers.PacketPipeline;
import net.slimevoid.library.util.helpers.PacketHelper;

public class PacketLib {
    /*
     * Create new Packet Handler
     */
    public static PacketPipeline handler = new PacketPipeline();

    @SideOnly(Side.CLIENT)
    public static void registerClientPacketHandlers() {

    }

    public static void registerPacketHandlers() {

        /*
         * Register SubPacket handlers
         */
        PacketGuiHandler packetGuiHandler = new PacketGuiHandler();
        packetGuiHandler.registerServerExecutor(CommandLib.CREATE_PLAN,
                                               new PacketGuiExecutor());

        handler.registerPacketHandler(PacketIds.GUI,
                                      packetGuiHandler);

        PacketSettingsHandler packetSettingsHandler = new PacketSettingsHandler();
        packetSettingsHandler.registerServerExecutor(CommandLib.UPDATE_SETTINGS,
                                                    new PacketSettingsExecutor());

        handler.registerPacketHandler(PacketIds.PLAYER,
                                      packetSettingsHandler);
    }

    public static void sendPlayerInventoryStatus(boolean newVal) {
        PacketSettings packet = new PacketSettings();
        packet.setPosition(0,
                           0,
                           0,
                           newVal ? 1 : 0);
        packet.setCommand(CommandLib.UPDATE_SETTINGS);
        PacketHelper.sendToServer(packet);
    }

}
