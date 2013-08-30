package slimevoid.collaborative.core.lib;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;


public class BlockLib {

	private static final String BLOCK_PREFIX = "cm.";
	public static final String BLOCK_COLLABORATIVE_BASE = BLOCK_PREFIX + "base";
	
	private static final String BLOCK_WORK_PREFIX = BLOCK_PREFIX + "work.";
	public static final String BLOCK_WORK_BENCH = BLOCK_WORK_PREFIX + "bench";
	public static final String BLOCK_WORK_CHEST = BLOCK_WORK_PREFIX + "chest";
	
	// TODO :: Add more Work Blocks (e.g. work chests)
	public static final int BLOCK_MAX_TILES = 1;
	public static final int BLOCK_WORK_BENCH_ID = 0;
	public static final int BLOCK_WORK_CHEST_ID = 1;

	public static Icon[][] registerIcons(IconRegister iconRegister, Icon[][] iconList) {
		iconList[BLOCK_WORK_BENCH_ID][0] = iconRegister.registerIcon(IconLib.WORK_BENCH_BOTTOM);
		iconList[BLOCK_WORK_BENCH_ID][1] = iconRegister.registerIcon(IconLib.WORK_BENCH_TOP);
		iconList[BLOCK_WORK_BENCH_ID][2] = iconRegister.registerIcon(IconLib.WORK_BENCH_FRONT);
		iconList[BLOCK_WORK_BENCH_ID][3] = iconRegister.registerIcon(IconLib.WORK_BENCH_SIDE);
		iconList[BLOCK_WORK_BENCH_ID][4] = iconRegister.registerIcon(IconLib.WORK_BENCH_SIDE);
		iconList[BLOCK_WORK_BENCH_ID][5] = iconRegister.registerIcon(IconLib.WORK_BENCH_SIDE);
		return iconList;
	}
}
