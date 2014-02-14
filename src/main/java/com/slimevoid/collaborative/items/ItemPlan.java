package com.slimevoid.collaborative.items;

import com.slimevoid.collaborative.core.lib.ConfigurationLib;
import com.slimevoid.collaborative.core.lib.IconLib;
import com.slimevoid.collaborative.core.lib.ItemLib;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemPlan extends Item {

    public ItemPlan() {
        this.setUnlocalizedName(ItemLib.PLAN_BLANK);
        this.setCreativeTab(ConfigurationLib.customTab);
    }

    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(IconLib.PLAN_BLANK);
    }

}
