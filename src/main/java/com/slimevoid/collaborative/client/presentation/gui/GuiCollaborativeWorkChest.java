package com.slimevoid.collaborative.client.presentation.gui;

import net.minecraft.client.gui.inventory.GuiContainer;

import org.lwjgl.opengl.GL11;

import com.slimevoid.collaborative.container.ContainerWorkChest;
import com.slimevoid.collaborative.core.lib.GuiLib;
import com.slimevoid.library.inventory.ContainerBase;

import cpw.mods.fml.common.registry.LanguageRegistry;
// import net.minecraft.client.resources.I18n;
// import net.minecraft.util.ResourceLocation;

public class GuiCollaborativeWorkChest extends GuiContainer {

    // private static final ResourceLocation field_110421_t = new
    // ResourceLocation("textures/gui/container/generic_54.png");
    // private IInventory workChestInventory;
    // private IInventory playerInventory;

    public GuiCollaborativeWorkChest(ContainerBase container) {
        super(container);
        this.allowUserInput = false;
        this.ySize = 222;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of
     * the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String title = LanguageRegistry.instance().getStringLocalization(((ContainerWorkChest) this.inventorySlots).getInventoryData().getInventoryName());
        fontRendererObj.drawString(title,
                                   (this.xSize / 2)
                                           - (fontRendererObj.getStringWidth(title) / 2),
                                   6,
                                   0x404040);
        fontRendererObj.drawString("Inventory",
                                   8,
                                   (this.ySize - 96) + 2,
                                   0x404040);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the
     * items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F,
                       1.0F,
                       1.0F,
                       1.0F);
        this.mc.renderEngine.bindTexture(GuiLib.GUI_WORK_CHEST);
        this.drawTexturedModalRect(this.guiLeft,
                                   this.guiTop,
                                   0,
                                   0,
                                   this.xSize,
                                   this.ySize);
    }

}
