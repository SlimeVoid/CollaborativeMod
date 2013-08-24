package slimevoid.projectbench.core.lib;

import net.minecraft.util.ResourceLocation;

public class GuiLib {

	private static final String GUI_PREFIX = "textures/gui/";
	public static final String PROJECT_BENCH = GUI_PREFIX + BlockLib.BLOCK_PROJECT_BENCH + ".png";
	public static final ResourceLocation GUI_PROJECT_BENCH = new ResourceLocation(CoreLib.MOD_RESOURCES, PROJECT_BENCH);
	public static final int GUIID_PROJECT_BENCH = 0;

}
