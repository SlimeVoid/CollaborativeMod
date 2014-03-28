package net.slimevoid.collaborative.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.slimevoid.collaborative.blocks.BlockCollaborativeBase;
import net.slimevoid.collaborative.core.lib.BlockLib;
import net.slimevoid.collaborative.core.lib.ChestLib;
import net.slimevoid.collaborative.core.lib.ConfigurationLib;
import net.slimevoid.collaborative.core.lib.ItemLib;
import net.slimevoid.collaborative.items.ItemPlan;
import net.slimevoid.collaborative.items.ItemPlanExtended;
import net.slimevoid.collaborative.tileentity.TileEntityWorkBench;
import net.slimevoid.library.items.ItemBlockBase;
import cpw.mods.fml.common.registry.GameRegistry;

public class CMCore {
    public static void registerBlocks() {
        ConfigurationLib.blockCollaborativeBase = new BlockCollaborativeBase(ConfigurationLib.blockCollaborativeBaseID);

        GameRegistry.registerBlock(ConfigurationLib.blockCollaborativeBase,
                                   ItemBlockBase.class,
                                   BlockLib.BLOCK_COLLABORATIVE_BASE);

        /**
         * WORK BENCH
         */
        ConfigurationLib.blockCollaborativeBase.addMapping(BlockLib.BLOCK_WORK_BENCH_ID,
                                                           TileEntityWorkBench.class,
                                                           BlockLib.BLOCK_WORK_BENCH);
        ConfigurationLib.createCustomTab();

        GameRegistry.addRecipe(new ItemStack(ConfigurationLib.blockCollaborativeBase, 1, BlockLib.BLOCK_WORK_BENCH_ID),
                               new Object[] {
                                       "SBS",
                                       "SCS",
                                       "WWW",
                                       Character.valueOf('S'),
                                       Blocks.cobblestone,
                                       Character.valueOf('B'),
                                       Blocks.crafting_table,
                                       Character.valueOf('C'),
                                       Blocks.chest,
                                       Character.valueOf('W'),
                                       Blocks.wooden_slab });

        /**
         * WORK CHESTS
         */
        ChestLib.registerWorkChests();
    }

    public static void registerItems() {
        ConfigurationLib.itemPlanBlank = new ItemPlan();

        ConfigurationLib.itemPlanFull = new ItemPlanExtended();

        GameRegistry.registerItem(ConfigurationLib.itemPlanBlank,
                                  ItemLib.PLAN_BLANK);
        GameRegistry.registerItem(ConfigurationLib.itemPlanFull,
                                  ItemLib.PLAN_FULL);

        GameRegistry.addRecipe(new ItemStack(ConfigurationLib.itemPlanBlank, 5, 0),
                               new Object[] {
                                       "P P",
                                       " P ",
                                       "P P",
                                       Character.valueOf('P'),
                                       Items.paper });

        GameRegistry.addShapelessRecipe(new ItemStack(Items.paper),
                                        new ItemStack(ConfigurationLib.itemPlanBlank));
    }
}
