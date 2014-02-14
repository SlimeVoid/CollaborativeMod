package com.slimevoid.collaborative.core.lib;

import com.slimevoid.collaborative.tileentity.TileEntityWorkChestStone;
import com.slimevoid.collaborative.tileentity.TileEntityWorkChestWood;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;

public class ChestLib {
    public enum EnumWorkChest {
        workChest_wood(BlockLib.WORK_CHEST_WOOD_ID, BlockLib.BLOCK_WORK_CHEST
                                                    + ".wood", BlockLib.DEFAULT_CHEST_SIZE * 1),

        workChest_stone(BlockLib.WORK_CHEST_STONE_ID, BlockLib.BLOCK_WORK_CHEST
                                                      + ".stone", BlockLib.DEFAULT_CHEST_SIZE * 2),

        workChest_iron(BlockLib.WORK_CHEST_IRON_ID, BlockLib.BLOCK_WORK_CHEST
                                                    + ".iron", BlockLib.DEFAULT_CHEST_SIZE * 3),

        workChest_gold(BlockLib.WORK_CHEST_GOLD_ID, BlockLib.BLOCK_WORK_CHEST
                                                    + ".gold", BlockLib.DEFAULT_CHEST_SIZE * 4),

        workChest_diamond(BlockLib.WORK_CHEST_DIAMOND_ID, BlockLib.BLOCK_WORK_CHEST
                                                          + ".diamond", BlockLib.DEFAULT_CHEST_SIZE * 5);

        private int    chestID;
        private String chestName;
        private int    chestSize;

        private EnumWorkChest(int id, String name, int size) {
            this.chestID = id;
            this.chestName = name;
            this.chestSize = size;
        }
    }

    private static EnumWorkChest getChestFromID(int id) {
        for (EnumWorkChest chest : EnumWorkChest.values()) {
            if (chest.chestID == id) {
                return chest;
            }
        }
        return null;
    }

    public static String getChestName(int id) {
        EnumWorkChest chest = getChestFromID(id);
        return chest != null ? chest.chestName : "Unknown Work Chest Type";
    }

    public static int getChestSize(int id) {
        EnumWorkChest chest = getChestFromID(id);
        return chest != null ? chest.chestSize : BlockLib.DEFAULT_CHEST_SIZE;
    }

    public static void registerWorkChests() {
        /**
         * WOODEN WORK CHEST
         */
        String woodChestLocale = getChestName(BlockLib.WORK_CHEST_WOOD_ID);

        ConfigurationLib.blockCollaborativeBase.addMapping(BlockLib.WORK_CHEST_WOOD_ID,
                                                           TileEntityWorkChestWood.class,
                                                           woodChestLocale);

        ItemStack woodChest = new ItemStack(ConfigurationLib.blockCollaborativeBase, 1, BlockLib.WORK_CHEST_WOOD_ID);
        GameRegistry.addRecipe(woodChest,
                               new Object[] {
                                       "WWW",
                                       "WCW",
                                       "SSS",
                                       Character.valueOf('W'),
                                       Blocks.wooden_slab,
                                       Character.valueOf('C'),
                                       Blocks.chest,
                                       Character.valueOf('S'),
                                       Blocks.cobblestone });

        /**
         * STONE WORK CHEST
         */
        String stoneChestLocale = getChestName(BlockLib.WORK_CHEST_STONE_ID);

        ConfigurationLib.blockCollaborativeBase.addMapping(BlockLib.WORK_CHEST_STONE_ID,
                                                           TileEntityWorkChestStone.class,
                                                           stoneChestLocale);

        ItemStack stoneChest = new ItemStack(ConfigurationLib.blockCollaborativeBase, 1, BlockLib.WORK_CHEST_STONE_ID);
        GameRegistry.addRecipe(stoneChest,
                               new Object[] {
                                       " W ",
                                       "WCW",
                                       " W ",
                                       Character.valueOf('W'),
                                       Blocks.cobblestone,
                                       Character.valueOf('C'),
                                       woodChest });
    }

    public static IIcon[][] registerWorkChestIcons(IIconRegister iconRegister, IIcon[][] iconList) {
        iconList[BlockLib.WORK_CHEST_WOOD_ID][0] = iconRegister.registerIcon(IconLib.WORK_BENCH_BOTTOM);
        iconList[BlockLib.WORK_CHEST_WOOD_ID][1] = iconRegister.registerIcon(IconLib.WORK_CHEST_WOOD_TOP);
        iconList[BlockLib.WORK_CHEST_WOOD_ID][2] = iconRegister.registerIcon(IconLib.WORK_BENCH_FRONT);
        iconList[BlockLib.WORK_CHEST_WOOD_ID][3] = iconRegister.registerIcon(IconLib.WORK_BENCH_SIDE);
        iconList[BlockLib.WORK_CHEST_WOOD_ID][4] = iconRegister.registerIcon(IconLib.WORK_BENCH_SIDE);
        iconList[BlockLib.WORK_CHEST_WOOD_ID][5] = iconRegister.registerIcon(IconLib.WORK_BENCH_SIDE);
        iconList[BlockLib.WORK_CHEST_STONE_ID][0] = iconRegister.registerIcon(IconLib.WORK_BENCH_BOTTOM);
        iconList[BlockLib.WORK_CHEST_STONE_ID][1] = iconRegister.registerIcon(IconLib.WORK_CHEST_STONE_TOP);
        iconList[BlockLib.WORK_CHEST_STONE_ID][2] = iconRegister.registerIcon(IconLib.WORK_BENCH_FRONT);
        iconList[BlockLib.WORK_CHEST_STONE_ID][3] = iconRegister.registerIcon(IconLib.WORK_BENCH_FRONT);
        iconList[BlockLib.WORK_CHEST_STONE_ID][4] = iconRegister.registerIcon(IconLib.WORK_BENCH_FRONT);
        iconList[BlockLib.WORK_CHEST_STONE_ID][5] = iconRegister.registerIcon(IconLib.WORK_BENCH_FRONT);
        return iconList;
    }

}
