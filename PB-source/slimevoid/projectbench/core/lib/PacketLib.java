package slimevoid.projectbench.core.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import slimevoid.projectbench.network.CommonPacketHandler;
import slimevoid.projectbench.network.handler.PacketProjectGuiHandler;
import slimevoid.projectbench.network.packet.executor.PacketProjectGuiExecutor;
import slimevoidlib.network.PacketIds;

public class PacketLib {
	
	@SideOnly(Side.CLIENT)
	public static void registerClientPacketHandlers() {
		
	}
	
	public static void registerPacketHandlers() {
		PacketProjectGuiHandler projectGuiHandler = new PacketProjectGuiHandler();
		projectGuiHandler.registerPacketHandler(CommandLib.CREATE_PROJECT_PLAN, new PacketProjectGuiExecutor());
		
		CommonPacketHandler.registerPacketHandler(PacketIds.GUI, projectGuiHandler);
	}

}
