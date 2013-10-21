package slimevoid.collaborative.proxy;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoid.collaborative.container.ContainerWorkBench;
import slimevoid.collaborative.container.ContainerWorkChest;
import slimevoid.collaborative.core.CollaborativeMod;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.core.lib.GuiLib;
import slimevoid.collaborative.core.lib.PacketLib;
import slimevoid.collaborative.network.CommonPacketHandler;
import slimevoid.collaborative.tileentity.TileEntityWorkBench;
import slimevoid.collaborative.tileentity.TileEntityWorkChestBase;
import slimevoidlib.ICommonProxy;
import slimevoidlib.IPacketHandling;
import slimevoidlib.util.helpers.BlockHelper;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;

public class CommonProxy implements ICommonProxy {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GuiLib.GUIID_WORK_BENCH) {
			TileEntityWorkBench tileentity = (TileEntityWorkBench) BlockHelper.getTileEntity(	world,
																								x,
																								y,
																								z,
																								TileEntityWorkBench.class);
			if (tileentity != null) {
				return new ContainerWorkBench(player.inventory, tileentity);
			}
		} else if (ID == GuiLib.GUIID_WORK_CHEST) {
			TileEntityWorkChestBase tileentity = (TileEntityWorkChestBase) BlockHelper.getTileEntity(	world,
																								x,
																								y,
																								z,
																								TileEntityWorkChestBase.class);
			if (tileentity != null) {
				return new ContainerWorkChest(player.inventory, tileentity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public void registerConfigurationProperties(File configFile) {
		ConfigurationLib.CommonConfig(configFile);
	}

	@Override
	public void preInit() {
		CommonPacketHandler.init();
		PacketLib.registerPacketHandlers();
		NetworkRegistry.instance().registerGuiHandler(	CollaborativeMod.instance,
														CollaborativeMod.proxy);
	}

	@Override
	public void registerTickHandlers() {
		// TODO :: Auto-generated method stub

	}

	@Override
	public void registerEventHandlers() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerRenderInformation() {
		// TODO :: Auto-generated method stub

	}

	@Override
	public String getMinecraftDir() {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		// TODO :: Auto-generated method stub

	}

	@Override
	public IPacketHandling getPacketHandler() {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {
		// TODO :: Auto-generated method stub

	}

	@Override
	public boolean isClient(World world) {
		// TODO :: Auto-generated method stub
		return false;
	}

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		// TODO :: Auto-generated method stub

	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
		// TODO :: Auto-generated method stub

	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
		// TODO :: Auto-generated method stub

	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		// TODO :: Auto-generated method stub

	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
		// TODO :: Auto-generated method stub

	}

}
