package slimevoid.projectbench.client.presentation.gui;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import slimevoid.projectbench.container.ContainerProjectBench;
import slimevoid.projectbench.core.lib.GuiLib;
import slimevoid.projectbench.tileentity.TileEntityProjectBench;

public class GuiProjectBench extends GuiContainer implements ICrafting {

	TileEntityProjectBench projectBench;
	private IInventory PlayerInventory;
	private GuiButton lockButton;
	private boolean locked;

	public GuiProjectBench(InventoryPlayer playerInventory, World world,
			TileEntityProjectBench projectBench) {
		super(new ContainerProjectBench(playerInventory, world, projectBench));
		this.projectBench = projectBench;
		this.PlayerInventory = playerInventory;
		this.ySize = 222;
	}

	@Override
	public void initGui() {
		super.initGui();
		lockButton = new GuiButton(3, this.guiLeft + 60, this.guiTop + 127, 10,
				10, "gui.lock");
		buttonList.add(lockButton);
		if (!this.locked) {
			lockButton.displayString = "u";
		} else {
			lockButton.displayString = "l";
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Project Bench", 60, 6, 0x404040);
		fontRenderer
				.drawString("Inventory", 8, (this.ySize - 96) + 2, 0x404040);
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

	@Override
	public void sendContainerAndContentsToPlayer(Container par1Container,
			List par2List) {
	}

	@Override
	public void sendSlotContents(Container container, int i, ItemStack itemstack) {
		this.mc.playerController.sendSlotPacket(itemstack, i);

	}

	@Override
	public void sendProgressBarUpdate(Container container, int i, int j) {
	}

}
