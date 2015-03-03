package net.slimevoid.collaborative.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.slimevoid.collaborative.blocks.BlockCollaborativeBase;
import net.slimevoid.collaborative.core.lib.*;
import net.slimevoid.collaborative.items.ItemPlan;
import net.slimevoid.collaborative.items.ItemPlanExtended;
import net.slimevoid.collaborative.tileentity.TileEntityWorkBench;
import net.slimevoid.library.core.SlimevoidCore;
import net.slimevoid.library.items.ItemBlockBase;
import net.slimevoid.library.util.helpers.PacketHelper;

public class CMCore {

    private static boolean initialized;

    public static void preInitialize() {
        if (initialized) {
            return;
        }
        PacketHelper.registerHandler();
        CollaborativeMod.proxy.preInit();

        SlimevoidCore.console(CoreLib.MOD_ID,
                "Registering blocks...");
        registerBlocks();

        SlimevoidCore.console(CoreLib.MOD_ID,
                "Registering items...");
        registerItems();

        CollaborativeMod.proxy.registerRenderInformation();
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        CollaborativeMod.proxy.init();
    }

    public static void postInitialize() {
        if (initialized) {
            return;
        }
        CollaborativeMod.proxy.postInit();
        initialized = true;
    }

    public static void registerBlocks() {
        ConfigurationLib.blockCollaborativeBase = new BlockCollaborativeBase();

        GameRegistry.registerBlock(ConfigurationLib.blockCollaborativeBase.setUnlocalizedName(BlockLib.BLOCK_COLLABORATIVE_BASE),
                                   ItemBlockBase.class,
                                   BlockLib.BLOCK_COLLABORATIVE_BASE);

        /**
         * WORK BENCH
         */
        ConfigurationLib.blockCollaborativeBase.addMapping(BlockLib.BLOCK_WORK_BENCH_ID,
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
