package net.slimevoid.collaborative.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.slimevoid.collaborative.core.lib.ConfigurationLib;
import net.slimevoid.collaborative.core.lib.IconLib;
import net.slimevoid.collaborative.core.lib.ItemLib;

public class ItemPlan extends Item {

    public ItemPlan() {
        super();
        this.setUnlocalizedName(ItemLib.PLAN_BLANK);
        this.setCreativeTab(ConfigurationLib.customTab);
    }

    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(IconLib.PLAN_BLANK);
    }

}
