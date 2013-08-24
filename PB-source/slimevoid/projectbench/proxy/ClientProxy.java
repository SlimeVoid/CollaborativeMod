package slimevoid.projectbench.proxy;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoid.projectbench.client.presentation.gui.GuiProjectBench;
import slimevoid.projectbench.core.ProjectBench;
import slimevoid.projectbench.core.lib.ConfigurationLib;
import slimevoid.projectbench.core.lib.GuiLib;
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
				return new GuiProjectBench(player.inventory, (TileEntityProjectBench) tileentity);
			}
		}
		return null;
	}

	@Override
	public void preInit() {
		NetworkRegistry.instance().registerGuiHandler(ProjectBench.instance, ProjectBench.proxy);
	}

}
