package slimevoid.projectbench.client.presentation.gui;

import org.lwjgl.opengl.GL11;

import slimevoid.projectbench.container.ContainerProjectBench;
import slimevoid.projectbench.core.lib.GuiLib;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiProjectBench extends GuiContainer {
	
	TileEntityProjectBench projectBench;
	
	public GuiProjectBench(InventoryPlayer playerInventory, TileEntityProjectBench projectBench) {
		super(new ContainerProjectBench(playerInventory, projectBench));
		this.projectBench = projectBench;
		this.ySize = 222;
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Project Bench", 60, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (this.ySize - 96) + 2, 0x404040);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.func_110434_K().func_110577_a(GuiLib.GUI_PROJECT_BENCH);
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        this.drawBench();
	}

	private void drawBench() {
	}

}
