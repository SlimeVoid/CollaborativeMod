package slimevoid.projectbench.core.lib;

public class GuiLib {

	private static final String GUI_PREFIX = "/assets/" + CoreLib.MOD_RESOURCES + "/textures/gui/";
	
	public static final int GUIID_PROJECT_BENCH = 0;
	
	public static final String PROJECT_BENCH = GUI_PREFIX + BlockLib.BLOCK_PROJECT_BENCH + ".png";
	public static final String GUI_PROJECT_BENCH = PROJECT_BENCH;
	
	public static final String GUI_PLAN_BUTTON = GUI_PREFIX + ItemLib.ITEM_PROJECT_PLAN;
	public static final String PLAN_BUTTON_ACTIVE = GUI_PLAN_BUTTON + "_active" + ".png";
	public static final String PLAN_BUTTON_INACTIVE = GUI_PLAN_BUTTON + "_inactive" + ".png";
	public static final String GUI_PLAN_BUTTON_ACTIVE = PLAN_BUTTON_ACTIVE;
	public static final String GUI_PLAN_BUTTON_INACTIVE = PLAN_BUTTON_INACTIVE;

}
