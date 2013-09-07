package slimevoid.collaborative.client.presentation.gui;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import slimevoid.collaborative.container.ContainerWorkBench;
import slimevoid.collaborative.core.lib.CommandLib;
import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.core.lib.ContainerLib;
import slimevoid.collaborative.core.lib.GuiLib;
import slimevoid.collaborative.core.lib.PacketLib;
import slimevoid.collaborative.network.packet.PacketGui;
import slimevoid.collaborative.tileentity.TileEntityWorkBench;

public class GuiCollaborativeWorkBench extends GuiContainer implements ICrafting {

	TileEntityWorkBench workBench;

	public GuiCollaborativeWorkBench(EntityPlayer entityplayer, InventoryPlayer playerInventory, World world,
			TileEntityWorkBench workBench) {
		super(new ContainerWorkBench(playerInventory, workBench));
		this.workBench = workBench;
		this.ySize = 222;
	}
        
	@Override
	protected void mouseClicked(int i, int j, int k) {
        int x = i - this.guiLeft;
        int y = j - this.guiTop;
        if(x >= 18 && y >= 55 && x <= 32 && y <= 69) {
            ItemStack plan = this.inventorySlots.getSlot(ContainerLib.PLAN_SLOT).getStack();
            ItemStack craft = this.inventorySlots.getSlot(ContainerLib.CRAFT_SLOT).getStack();
            if(plan == null || craft == null || plan.itemID != ConfigurationLib.itemPlanBlank.itemID) {
                return;
            }
            PacketGui pkt = new PacketGui(
            		this.workBench.xCoord,
            		this.workBench.yCoord,
            		this.workBench.zCoord,
            		CommandLib.CREATE_PLAN,
            		this.inventorySlots.windowId);
            PacketDispatcher.sendPacketToServer(pkt.getPacket());
        }
        //if(x >= 63 && y >= 125 && x <= 77 && y <= 139) {
        if(x >= 8 && y >= (this.ySize - 96) + 2 && x <= 8 + 50 && y <= (this.ySize - 96) + 2 + 10) {
            ConfigurationLib.updateplayerInventoryLocked(!ConfigurationLib.playerInventoryLocked);
    		PacketLib.sendPlayerInventoryStatus(ConfigurationLib.playerInventoryLocked);
        }
        super.mouseClicked(i, j, k);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		fontRenderer.drawString(
				GuiLib.TITLE_WORK_BENCH,
				(this.xSize / 2) - (fontRenderer.getStringWidth(GuiLib.TITLE_WORK_BENCH) / 2),
				6,
				0x404040);
		if (!ConfigurationLib.playerInventoryLocked) {
			fontRenderer.drawString("Inventory", 8, (this.ySize - 96) + 2, 0x404040);
		} else {
			fontRenderer.drawString("Inventory", 8, (this.ySize - 96) + 2, 0x400000);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiLib.GUI_WORK_BENCH);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
/*		if (!ConfigurationLib.playerInventoryLocked) {
        	this.drawTexturedModalRect(this.guiLeft + 63, this.guiTop + 125, 176, 28, 14, 14);
        } else {
        	this.drawTexturedModalRect(this.guiLeft + 63, this.guiTop + 125, 176, 14, 14, 14);
        }*/
		ItemStack plan = this.inventorySlots.getSlot(ContainerLib.PLAN_SLOT).getStack();
		ItemStack craft = this.inventorySlots.getSlot(ContainerLib.CRAFT_SLOT).getStack();
		if (plan != null && craft != null && plan.getItem() != null && plan.getItem().itemID == ConfigurationLib.itemPlanBlank.itemID) {
			this.drawTexturedModalRect(this.guiLeft + 18, this.guiTop + 55, 176, 0, 14, 14);
		}
		if (plan != null && plan.itemID == ConfigurationLib.itemPlanFull.itemID) {
			ContainerWorkBench cont = (ContainerWorkBench) this.inventorySlots;
			//ContainerWorkBench _tmp = cont;
			ItemStack ist[] = ContainerWorkBench.getShadowItems(plan);
			RenderHelper.enableGUIStandardItemLighting();
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(32826);
			GL11.glEnable(2896);
			GL11.glEnable(2929);
			if (ist != null) {
				for (int n = 0; n < 9; n++) {
					if (ist[n] == null)
						continue;
					Slot sl = this.inventorySlots.getSlot(n);
					if (sl.getStack() == null) {
						int slx = sl.xDisplayPosition + this.guiLeft;
						int sly = sl.yDisplayPosition + this.guiTop;
						itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, ist[n], slx, sly);
						itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, ist[n], slx, sly);
					}
				}
			}

			GL11.glDisable(2896);
			GL11.glDisable(2929);
			GL11.glEnable(3042);
			this.mc.getTextureManager().bindTexture(GuiLib.GUI_WORK_BENCH);
			for (int n = 0; n < 9; n++) {
				if (ist == null || ist[n] == null)
					continue;
				Slot sl = this.inventorySlots.getSlot(n);
				if (sl.getStack() != null)
					continue;
				int slx = sl.xDisplayPosition;
				int sly = sl.yDisplayPosition;
				if ((cont.satisfyMask & 1 << n) > 0) {
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
				} else {
					GL11.glColor4f(1.0F, 0.1F, 0.1F, 0.6F);
				}
				this.drawTexturedModalRect(this.guiLeft + slx, this.guiTop + sly, slx, sly, 16, 16);
			}

			GL11.glDisable(3042);
		}

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
