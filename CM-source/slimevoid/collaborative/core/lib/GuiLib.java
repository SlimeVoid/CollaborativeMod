package slimevoid.collaborative.core.lib;

public class GuiLib {

	private static final String	GUI_PREFIX			= "/assets/"
														+ CoreLib.MOD_RESOURCES
														+ "/textures/gui/";

	public static final int		GUIID_WORK_BENCH	= 0;
	public static final int		GUIID_WORK_CHEST	= 1;

	public static final String	WORK_BENCH			= GUI_PREFIX
														+ BlockLib.BLOCK_WORK_BENCH
														+ ".png";
	public static final String	GUI_WORK_BENCH		= WORK_BENCH;

	public static final String	TITLE_WORK_BENCH	= "Collaborative Work Bench";
}
