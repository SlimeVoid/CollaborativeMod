// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemPlan.java

package slimevoid.collaborative.items;

import java.util.*;

import slimevoid.collaborative.core.lib.ConfigurationLib;
import slimevoid.collaborative.core.lib.IconLib;
import slimevoid.collaborative.core.lib.ItemLib;
import slimevoid.collaborative.core.lib.LocaleLib;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Icon;

public class ItemPlan extends Item {
	
	@Override
	public void registerIcons(IconRegister iconregister) {
		this.itemIcon = iconregister.registerIcon(IconLib.PLAN_FULL);
	}

	public ItemPlan(int i) {
		super(i);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(ItemLib.PLAN_FULL);
		this.setMaxStackSize(1);
	}

	@Override
	public Icon getIconFromDamage(int i) {
		return this.itemIcon;
	}

	@Override
	public String getItemDisplayName(ItemStack ist) {
		if (ist.stackTagCompound == null) {
			return super.getItemDisplayName(ist);
		}
		if (!ist.stackTagCompound.hasKey("result")) {
			return super.getItemDisplayName(ist);
		} else {
			NBTTagCompound res = ist.stackTagCompound.getCompoundTag("result");
			ItemStack result = ItemStack.loadItemStackFromNBT(res);
			return (new StringBuilder()).append(result.getItem().getItemDisplayName(result))
					.append(" Plan").toString();
		}
	}

	@Override
	public void addInformation(ItemStack ist, EntityPlayer player, List lines, boolean par4) {
		if (ist.stackTagCompound == null) {
			return;
		}
		NBTTagList require = ist.stackTagCompound.getTagList("requires");
		if (require == null)
			return;
		HashMap counts = new HashMap();
		for (int i = 0; i < require.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound) require.tagAt(i);
			ItemStack is2 = ItemStack.loadItemStackFromNBT(item);
			List l1 = Arrays.asList(new Integer[] { Integer.valueOf(is2.itemID),
					Integer.valueOf(is2.getItemDamage()) });
			Integer lc = (Integer) counts.get(l1);
			if (lc == null) {
				lc = Integer.valueOf(0);
			}
			counts.put(l1, Integer.valueOf(lc.intValue() + 1));
		}

		java.util.Map.Entry kv;
		ItemStack i2d;
		for (Iterator i$ = counts.entrySet().iterator(); i$.hasNext(); lines
				.add((new StringBuilder()).append(kv.getValue()).append(" x ")
						.append(i2d.getItem().getItemDisplayName(i2d)).toString())) {
			kv = (java.util.Map.Entry) i$.next();
			List li = (List) kv.getKey();
			i2d = new ItemStack(((Integer) li.get(0)).intValue(), 1,
					((Integer) li.get(1)).intValue());
		}

	}

	@Override
	public EnumRarity getRarity(ItemStack ist) {
		return EnumRarity.rare;
	}

	@Override
	public boolean getShareTag() {
		return true;
	}
}