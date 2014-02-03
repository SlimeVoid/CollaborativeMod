package slimevoid.collaborative.core.lib;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import slimevoid.collaborative.network.CommonPacketHandler;
import slimevoid.collaborative.network.handler.PacketGuiHandler;
import slimevoid.collaborative.network.handler.PacketSettingsHandler;
import slimevoid.collaborative.network.packet.PacketSettings;
import slimevoid.collaborative.network.packet.executor.PacketGuiExecutor;
import slimevoid.collaborative.network.packet.executor.PacketSettingsExecutor;
import slimevoidlib.network.PacketIds;

public class PacketLib {

    @SideOnly(Side.CLIENT)
    public static void registerClientPacketHandlers() {

    }

    public static void registerPacketHandlers() {
        PacketGuiHandler packetGuiHandler = new PacketGuiHandler();
        packetGuiHandler.registerPacketHandler(CommandLib.CREATE_PLAN,
                                               new PacketGuiExecutor());

        CommonPacketHandler.registerPacketHandler(PacketIds.GUI,
                                                  packetGuiHandler);

        PacketSettingsHandler packetSettingsHandler = new PacketSettingsHandler();
        packetSettingsHandler.registerPacketHandler(CommandLib.UPDATE_SETTINGS,
                                                    new PacketSettingsExecutor());

        CommonPacketHandler.registerPacketHandler(PacketIds.PLAYER,
                                                  packetSettingsHandler);
    }

    public static void sendPlayerInventoryStatus(boolean newVal) {
        PacketSettings packet = new PacketSettings();
        packet.setPosition(0,
                           0,
                           0,
                           newVal ? 1 : 0);
        packet.setCommand(CommandLib.UPDATE_SETTINGS);
        PacketDispatcher.sendPacketToServer(packet.getPacket());
    }

}
