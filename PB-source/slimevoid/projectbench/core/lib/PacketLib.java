package slimevoid.projectbench.core.lib;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import slimevoid.projectbench.network.CommonPacketHandler;
import slimevoid.projectbench.network.handler.PacketProjectGuiHandler;
import slimevoid.projectbench.network.handler.PacketProjectSettingsHandler;
import slimevoid.projectbench.network.packet.PacketProjectSettings;
import slimevoid.projectbench.network.packet.executor.PacketProjectGuiExecutor;
import slimevoid.projectbench.network.packet.executor.PacketProjectSettingsExecutor;
import slimevoidlib.network.PacketIds;

public class PacketLib {
	
	@SideOnly(Side.CLIENT)
	public static void registerClientPacketHandlers() {
		
	}
	
	public static void registerPacketHandlers() {
		PacketProjectGuiHandler projectGuiHandler = new PacketProjectGuiHandler();
		projectGuiHandler.registerPacketHandler(CommandLib.CREATE_PROJECT_PLAN, new PacketProjectGuiExecutor());
		
		CommonPacketHandler.registerPacketHandler(PacketIds.GUI, projectGuiHandler);
		
		PacketProjectSettingsHandler projectSettingsHandler = new PacketProjectSettingsHandler();
		projectSettingsHandler.registerPacketHandler(CommandLib.UPDATE_PROJECT_SETTINGS, new PacketProjectSettingsExecutor());
		
		CommonPacketHandler.registerPacketHandler(PacketIds.PLAYER, projectSettingsHandler);
	}

	public static void sendPlayerInventoryStatus(boolean newVal) {
		PacketProjectSettings packet = new PacketProjectSettings();
		packet.setPosition(0, 0, 0, newVal ? 1 : 0);
		packet.setCommand(CommandLib.UPDATE_PROJECT_SETTINGS);
		PacketDispatcher.sendPacketToServer(packet.getPacket());
	}

}
