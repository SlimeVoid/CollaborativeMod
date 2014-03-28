package net.slimevoid.collaborative.core.lib;

import net.minecraft.block.BlockWood;

public class IconLib {

    private static final String ICON_PREFIX          = CoreLib.MOD_ID
                                                       + ":";
    public static final String  WORK_BENCH_PREFIX    = ICON_PREFIX
                                                       + BlockLib.BLOCK_WORK_BENCH;
    public static final String  WORK_BENCH_BOTTOM    = WORK_BENCH_PREFIX
                                                       + "_bottom";
    public static final String  WORK_BENCH_TOP       = WORK_BENCH_PREFIX
                                                       + "_top";
    public static final String  WORK_BENCH_SIDE      = WORK_BENCH_PREFIX
                                                       + "_side";
    public static final String  WORK_BENCH_FRONT     = WORK_BENCH_PREFIX
                                                       + "_front";

    /**
     * Work Chest Icons
     */
    public static final String  WORK_CHEST_WOOD_TOP  = BlockWood.field_150096_a[0];
    public static final String  WORK_CHEST_STONE_TOP = "stone";

    private static final String PLAN_PREFIX          = ICON_PREFIX
                                                       + ItemLib.ITEM_PLAN;
    public static final String  PLAN_BLANK           = PLAN_PREFIX + "_blank";
    public static final String  PLAN_FULL            = PLAN_PREFIX + "_full";

}
