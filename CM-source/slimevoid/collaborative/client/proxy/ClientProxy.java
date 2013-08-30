package slimevoid.collaborative.client.proxy;

import java.io.File;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoid.collaborative.client.network.ClientPacketHandler;
import slimevoid.collaborative.client.presentation.gui.GuiCollaborativeWorkBench;
import slimevoid.collaborative.core.lib.CommandLib;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.core.lib.GuiLib;
import slimevoid.collaborative.core.lib.PacketLib;
import slimevoid.collaborative.network.packet.PacketSettings;
import slimevoid.collaborative.proxy.CommonProxy;
import slimevoid.collaborative.tileentity.TileEntityWorkBench;
import slimevoidlib.util.SlimevoidHelper;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerConfigurationProperties(File configFile) {
		ConfigurationLib.ClientConfig(configFile);
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == GuiLib.GUIID_WORK_BENCH) {
			TileEntity tileentity = SlimevoidHelper.getBlockTileEntity(world, x, y, z);
			if (tileentity != null && tileentity instanceof TileEntityWorkBench) {
				return new GuiCollaborativeWorkBench(player, player.inventory,world, (TileEntityWorkBench) tileentity);
			}
		}
		return null;
	}

	@Override
	public void preInit() {
		super.preInit();
		ClientPacketHandler.init();
		PacketLib.registerClientPacketHandlers();
	}
	
	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
		// This Sends the Local Player settings to the server, for persistent user settings
		PacketSettings packet = new PacketSettings();
		packet.setCommand(CommandLib.UPDATE_SETTINGS);
		packet.setPosition(0, 0, 0, ConfigurationLib.playerInventoryLocked ? 1 : 0);
		PacketDispatcher.sendPacketToServer(packet.getPacket());
	}

}
