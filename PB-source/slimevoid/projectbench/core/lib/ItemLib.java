package slimevoid.projectbench.core.lib;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemLib {

	public static final String ITEM_PREFIX = "project.";
	public static final String ITEM_PROJECT_PLAN = ITEM_PREFIX + "plan.";
	public static final String PROJECT_PLAN_BLANK = ITEM_PROJECT_PLAN + "blank";
	public static final String PROJECT_PLAN_FULL = ITEM_PROJECT_PLAN + "full";

	public static boolean matchOre(ItemStack a, ItemStack b) {
		String s1 = OreDictionary.getOreName(OreDictionary.getOreID(a));
		String s2 = OreDictionary.getOreName(OreDictionary.getOreID(b));
		if (s1 != null && !s1.equals("Unknown") && s2 != null && !s2.equals("Unknown") && s1.equals(s2)) {
			return true;
		} else {
			return compareItemStack(a, b) == 0;
		}
	}

	public static int compareItemStack(ItemStack a, ItemStack b) {
		if (a.itemID != b.itemID) {
			return a.itemID - b.itemID;
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
