package com.slimevoid.collaborative.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.slimevoid.collaborative.blocks.BlockCollaborativeBase;
import com.slimevoid.collaborative.core.lib.BlockLib;
import com.slimevoid.collaborative.core.lib.ChestLib;
import com.slimevoid.collaborative.core.lib.ConfigurationLib;
import com.slimevoid.collaborative.items.ItemPlan;
import com.slimevoid.collaborative.items.ItemPlanExtended;
import com.slimevoid.collaborative.tileentity.TileEntityWorkBench;
import com.slimevoid.library.items.ItemBlockBase;

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
