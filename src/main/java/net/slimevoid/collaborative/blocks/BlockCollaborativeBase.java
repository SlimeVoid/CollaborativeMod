package net.slimevoid.collaborative.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.slimevoid.collaborative.core.lib.ConfigurationLib;
import net.slimevoid.collaborative.core.lib.RenderLib;
import net.slimevoid.library.blocks.IEnumBlockType;
import net.slimevoid.library.blocks.BlockSimpleBase;
import net.slimevoid.library.blocks.state.BlockStates;
import net.slimevoid.library.items.ItemBlockBase;

public class BlockCollaborativeBase extends BlockSimpleBase {
	
	protected static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockTypeCollaborative.class);

    public BlockCollaborativeBase() {
        super(Material.rock);
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    public int getRenderType() {
        return RenderLib.BLOCK_BASE;
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

    @Override
    protected IBlockState getInitialState(IBlockState state) {
        return state;
    }

    @Override
    protected PropertyEnum getBlockTypeProperty() {
        return VARIANT;
    }

    @Override
    protected IProperty[] getPropertyList() {
        return new IProperty[] {BlockStates.FACING, VARIANT};
    }

    @Override
    public Comparable<? extends IEnumBlockType> getDefaultBlockType() {
    	return BlockTypeCollaborative.WORKBENCH;
    }

	@Override
	protected Comparable<? extends IEnumBlockType> getBlockType(int meta) {
		return meta < BlockTypeCollaborative.values().length ? BlockTypeCollaborative.values()[meta] : this.getDefaultBlockType();
	}

}
