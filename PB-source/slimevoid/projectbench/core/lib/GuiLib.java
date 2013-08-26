package slimevoid.projectbench.core.lib;

import net.minecraft.util.ResourceLocation;

public class GuiLib {

	private static final String GUI_PREFIX = "textures/gui/";
	
	public static final int GUIID_PROJECT_BENCH = 0;
	
	public static final String PROJECT_BENCH = GUI_PREFIX + BlockLib.BLOCK_PROJECT_BENCH + ".png";
	public static final ResourceLocation GUI_PROJECT_BENCH = new ResourceLocation(CoreLib.MOD_RESOURCES, PROJECT_BENCH);
	
	public static final String GUI_PLAN_BUTTON = GUI_PREFIX + ItemLib.ITEM_PROJECT_PLAN;
	public static final String PLAN_BUTTON_ACTIVE = GUI_PLAN_BUTTON + "_active" + ".png";
	public static final String PLAN_BUTTON_INACTIVE = GUI_PLAN_BUTTON + "_inactive" + ".png";
	public static final ResourceLocation GUI_PLAN_BUTTON_ACTIVE = new ResourceLocation(CoreLib.MOD_RESOURCES, PLAN_BUTTON_ACTIVE);
	public static final ResourceLocation GUI_PLAN_BUTTON_INACTIVE = new ResourceLocation(CoreLib.MOD_RESOURCES, PLAN_BUTTON_INACTIVE);

}
