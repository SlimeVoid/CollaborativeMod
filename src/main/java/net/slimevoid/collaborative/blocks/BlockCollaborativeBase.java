package net.slimevoid.collaborative.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.slimevoid.collaborative.core.lib.BlockLib;
import net.slimevoid.collaborative.core.lib.ConfigurationLib;
import net.slimevoid.library.blocks.BlockBase;
import net.slimevoid.library.items.ItemBlockBase;

public class BlockCollaborativeBase extends BlockBase {

    public BlockCollaborativeBase(int blockID) {
        super(Material.rock, BlockLib.BLOCK_MAX_TILES);
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    public int getRenderType() {
        return 0;
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    public boolean isCube() {
        return true;
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ConfigurationLib.customTab;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        if (item instanceof ItemBlockBase) {
            for (int i = 0; i < ((ItemBlockBase) item).getValidItemBlocks().size(); i++) {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

}
