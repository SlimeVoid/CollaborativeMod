package com.slimevoid.collaborative.core.lib;

import net.minecraft.util.ResourceLocation;

public class GuiLib {

    private static final String          GUI_PREFIX       = "textures/gui/";

    public static final int              GUIID_WORK_BENCH = 0;
    public static final int              GUIID_WORK_CHEST = 1;

    public static final String           WORK_BENCH       = GUI_PREFIX
                                                            + BlockLib.BLOCK_WORK_BENCH
                                                            + ".png";
    public static final ResourceLocation GUI_WORK_BENCH   = new ResourceLocation(CoreLib.MOD_RESOURCES, WORK_BENCH);

    public static final String           TITLE_WORK_BENCH = "Collaborative Work Bench";

    public static final String           WORK_CHEST       = GUI_PREFIX
                                                            + BlockLib.BLOCK_WORK_CHEST
                                                            + ".png";
    public static final ResourceLocation GUI_WORK_CHEST   = new ResourceLocation(CoreLib.MOD_RESOURCES, WORK_CHEST);
}
