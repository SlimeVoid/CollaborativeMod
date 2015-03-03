package net.slimevoid.collaborative.core.lib;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slimevoid.collaborative.network.packet.PacketGui;
import net.slimevoid.collaborative.network.packet.PacketSettings;
import net.slimevoid.collaborative.network.packet.executor.PacketGuiExecutor;
import net.slimevoid.collaborative.network.packet.executor.PacketSettingsExecutor;
import net.slimevoid.library.network.PacketIds;
import net.slimevoid.library.util.helpers.PacketHelper;

public class PacketLib {

    @SideOnly(Side.CLIENT)
    public static void registerClientPacketHandlers() {

    }

    public static void registerPacketHandlers() {

        /*
         * Register SubPacket handlers
         */
        PacketHelper.registerServerExecutor(
                PacketGuiExecutor.class,
                PacketGui.class,
                0);
                //CommandLib.CREATE_PLAN,

        PacketHelper.registerServerExecutor(
                PacketSettingsExecutor.class,
                PacketSettings.class,
                1);
                //CommandLib.UPDATE_SETTINGS
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
