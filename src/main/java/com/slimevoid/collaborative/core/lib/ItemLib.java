package com.slimevoid.collaborative.core.lib;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemLib {

    public static final String ITEM_PREFIX = "cm.";
    public static final String ITEM_PLAN   = ITEM_PREFIX + "plan";
    public static final String PLAN_BLANK  = ITEM_PLAN + "_blank";
    public static final String PLAN_FULL   = ITEM_PLAN + "_full";

    public static boolean matchOre(ItemStack a, ItemStack b) {
        String s1 = OreDictionary.getOreName(OreDictionary.getOreID(a));
        String s2 = OreDictionary.getOreName(OreDictionary.getOreID(b));
        if (s1 != null && !s1.equals("Unknown") && s2 != null
            && !s2.equals("Unknown") && s1.equals(s2)) {
            return true;
        } else {
            return compareItemStack(a,
                                    b) == 0;
        }
    }

    public static int compareItemStack(ItemStack a, ItemStack b) {
        if (a.getItem() != b.getItem()) {
            return -1;
        }
        if (a.getItemDamage() == b.getItemDamage()) {
            return 0;
        }
        if (a.getItem().getHasSubtypes()) {
            return a.getItemDamage() - b.getItemDamage();
        } else {
            return 0;
        }
    }

}
