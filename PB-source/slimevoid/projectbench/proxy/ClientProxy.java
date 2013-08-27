package slimevoid.projectbench.proxy;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoid.projectbench.client.network.ClientPacketHandler;
import slimevoid.projectbench.client.presentation.gui.GuiProjectBench;
import slimevoid.projectbench.core.lib.CommandLib;
import slimevoid.projectbench.core.lib.ConfigurationLib;
import slimevoid.projectbench.core.lib.GuiLib;
import slimevoid.projectbench.core.lib.PacketLib;
import slimevoid.projectbench.network.packet.PacketProjectSettings;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import slimevoidlib.util.SlimevoidHelper;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerConfigurationProperties() {
		ConfigurationLib.ClientConfig();
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == GuiLib.GUIID_PROJECT_BENCH) {
			TileEntity tileentity = SlimevoidHelper.getBlockTileEntity(world, x, y, z);
			if (tileentity != null && tileentity instanceof TileEntityProjectBench) {
				return new GuiProjectBench(player, player.inventory,world, (TileEntityProjectBench) tileentity);
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
	public void login(NetHandler handler, INetworkManager manager,
			Packet1Login login) {
		// This Sends the Local Player settings to the server, for persistent user settings
		PacketProjectSettings packet = new PacketProjectSettings();
		packet.setCommand(CommandLib.UPDATE_PROJECT_SETTINGS);
		packet.setPosition(0, 0, 0, ConfigurationLib.playerInventoryLocked ? 1 : 0);
		PacketDispatcher.sendPacketToServer(packet.getPacket());
	}

}
