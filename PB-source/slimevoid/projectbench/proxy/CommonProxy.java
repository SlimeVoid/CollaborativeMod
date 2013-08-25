package slimevoid.projectbench.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.Player;
import slimevoid.projectbench.container.ContainerProjectBench;
import slimevoid.projectbench.core.lib.ConfigurationLib;
import slimevoid.projectbench.core.lib.GuiLib;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import slimevoidlib.ICommonProxy;
import slimevoidlib.IPacketHandling;
import slimevoidlib.util.SlimevoidHelper;

public class CommonProxy implements ICommonProxy {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == GuiLib.GUIID_PROJECT_BENCH) {
			TileEntity tileentity = SlimevoidHelper.getBlockTileEntity(world, x, y, z);
			if (tileentity != null && tileentity instanceof TileEntityProjectBench) {
				return new ContainerProjectBench(player.inventory,world, (TileEntityProjectBench) tileentity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public void registerConfigurationProperties() {
		ConfigurationLib.CommonConfig();
	}

	@Override
	public void preInit() {
		// TODO :: Auto-generated method stub
		
	}

	@Override
	public void registerTickHandler() {
		// TODO :: Auto-generated method stub
		
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
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		// TODO :: Auto-generated method stub
		return 0;
	}

	@Override
	public int getBlockTextureFromMetadata(int meta) {
		// TODO :: Auto-generated method stub
		return 0;
	}

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		// TODO :: Auto-generated method stub
		
	}

	@Override
	public IPacketHandling getPacketHandler() {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public void registerTileEntitySpecialRenderer(
			Class<? extends TileEntity> clazz) {
		// TODO :: Auto-generated method stub
		
	}

	@Override
	public void displayTileEntityGui(EntityPlayer entityplayer,
			TileEntity tileentity) {
		// TODO :: Auto-generated method stub
		
	}

	@Override
	public World getWorld() {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public World getWorld(NetHandler handler) {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public EntityPlayer getPlayer() {
		// TODO :: Auto-generated method stub
		return null;
	}

	@Override
	public void login(NetHandler handler, INetworkManager manager,
			Packet1Login login) {
		// TODO :: Auto-generated method stub
		
	}

}
