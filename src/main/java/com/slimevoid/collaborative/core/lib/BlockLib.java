package com.slimevoid.collaborative.core.lib;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockLib {

    private static final String BLOCK_PREFIX             = "cm.";
    public static final String  BLOCK_COLLABORATIVE_BASE = BLOCK_PREFIX
                                                           + "base";

    private static final String BLOCK_WORK_PREFIX        = BLOCK_PREFIX
                                                           + "work.";
    public static final String  BLOCK_WORK_BENCH         = BLOCK_WORK_PREFIX
                                                           + "bench";
    public static final String  BLOCK_WORK_CHEST         = BLOCK_WORK_PREFIX
                                                           + "chest";

    // TODO :: Add more Work Blocks (e.g. work chests)
    public static final int     BLOCK_MAX_TILES          = 6;
    public static final int     BLOCK_WORK_BENCH_ID      = 0;
    public static final int     BLOCK_WORK_CHEST_ID      = 1;
    public static final int     WORK_CHEST_WOOD_ID       = BLOCK_WORK_CHEST_ID + 0;
    public static final int     WORK_CHEST_STONE_ID      = BLOCK_WORK_CHEST_ID + 1;
    public static final int     WORK_CHEST_IRON_ID       = BLOCK_WORK_CHEST_ID + 2;
    public static final int     WORK_CHEST_GOLD_ID       = BLOCK_WORK_CHEST_ID + 3;
    public static final int     WORK_CHEST_DIAMOND_ID    = BLOCK_WORK_CHEST_ID + 4;

    public static final int     DEFAULT_CHEST_SIZE       = 6 * 9;

    public static IIcon[] registerBottomIcons(IIconRegister iconRegister, int MAX_TILES) {
        IIcon[] icons = new IIcon[MAX_TILES];
        icons[BLOCK_WORK_BENCH_ID] = iconRegister.registerIcon(IconLib.WORK_BENCH_BOTTOM);
        return icons;
    }

    public static IIcon[] registerTopIcons(IIconRegister iconRegister, int MAX_TILES) {
        IIcon[] icons = new IIcon[MAX_TILES];
        icons[BLOCK_WORK_BENCH_ID] = iconRegister.registerIcon(IconLib.WORK_BENCH_TOP);
        return icons;
    }

    public static IIcon[] registerFrontIcons(IIconRegister iconRegister, int MAX_TILES) {
        IIcon[] icons = new IIcon[MAX_TILES];
        icons[BLOCK_WORK_BENCH_ID] = iconRegister.registerIcon(IconLib.WORK_BENCH_FRONT);
        return icons;
    }

    public static IIcon[] registerSideIcons(IIconRegister iconRegister, int MAX_TILES) {
        IIcon[] icons = new IIcon[MAX_TILES];
        icons[BLOCK_WORK_BENCH_ID] = iconRegister.registerIcon(IconLib.WORK_BENCH_SIDE);
        // iconList = ChestLib.registerWorkChestIcons(iconRegister,
        // iconList);
        return icons;
    }
}
