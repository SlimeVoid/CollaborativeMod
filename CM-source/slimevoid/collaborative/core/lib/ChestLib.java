package slimevoid.collaborative.core.lib;

public class ChestLib {

	private static final String[]	chestNames			= {
			BlockLib.BLOCK_WORK_CHEST + ".wood",
			BlockLib.BLOCK_WORK_CHEST + ".stone",
			BlockLib.BLOCK_WORK_CHEST + ".iron",
			BlockLib.BLOCK_WORK_CHEST + ".gold",
			BlockLib.BLOCK_WORK_CHEST + ".diamond"		};

	public static final int			DEFAULT_CHEST_SIZE	= 6 * 9;

	public static String getChestName(int id) {
		return id >= 0 && id < chestNames.length ? chestNames[id] : "Unknown Chest Type";
	}

	public static int getChestSize(int id) {
		return (id + 1) * DEFAULT_CHEST_SIZE;
	}

}
