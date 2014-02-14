package com.slimevoid.collaborative.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;

import com.slimevoid.collaborative.core.lib.ConfigurationLib;
import com.slimevoid.collaborative.core.lib.IconLib;
import com.slimevoid.collaborative.core.lib.ItemLib;

public class ItemPlanExtended extends ItemPlan {

    public ItemPlanExtended() {
        super();
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(ItemLib.PLAN_FULL);
        this.setCreativeTab(ConfigurationLib.customTab);
        this.setMaxStackSize(1);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(IconLib.PLAN_FULL);
    }

    @Override
    public IIcon getIconFromDamage(int i) {
        return this.itemIcon;
    }

    @Override
    public String getItemStackDisplayName(ItemStack ist) {
        if (ist.stackTagCompound == null) {
            return super.getItemStackDisplayName(ist);
        }
        if (!ist.stackTagCompound.hasKey("result")) {
            return super.getItemStackDisplayName(ist);
        } else {
            NBTTagCompound res = ist.stackTagCompound.getCompoundTag("result");
            ItemStack result = ItemStack.loadItemStackFromNBT(res);
            return (new StringBuilder()).append(result != null ? result.getItem().getItemStackDisplayName(result) : "Unknown").append(" Plan").toString();
        }
    }

    @Override
    public void addInformation(ItemStack ist, EntityPlayer player, List lines, boolean par4) {
        if (ist.stackTagCompound == null) {
            return;
        }
        NBTTagList require = ist.stackTagCompound.getTagList("requires",
                                                             10);
        if (require == null) return;
        HashMap counts = new HashMap();
        for (int i = 0; i < require.tagCount(); i++) {
            NBTTagCompound item = (NBTTagCompound) require.getCompoundTagAt(i);
            ItemStack is2 = ItemStack.loadItemStackFromNBT(item);
            List l1 = Arrays.asList(new Integer[] {
                    Integer.valueOf(Item.getIdFromItem(is2.getItem())),
                    Integer.valueOf(is2.getItemDamage()) });
            Integer lc = (Integer) counts.get(l1);
            if (lc == null) {
                lc = Integer.valueOf(0);
            }
            counts.put(l1,
                       Integer.valueOf(lc.intValue() + 1));
        }

        java.util.Map.Entry kv;
        ItemStack i2d;
        for (Iterator i$ = counts.entrySet().iterator(); i$.hasNext(); lines.add((new StringBuilder()).append(kv.getValue()).append(" x ").append(i2d.getItem().getItemStackDisplayName(i2d)).toString())) {
            kv = (java.util.Map.Entry) i$.next();
            List li = (List) kv.getKey();
            i2d = new ItemStack(Item.getItemById(((Integer) li.get(0)).intValue()), 1, ((Integer) li.get(1)).intValue());
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
