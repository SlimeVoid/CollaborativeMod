package com.slimevoid.collaborative.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

import com.slimevoid.collaborative.core.lib.BlockLib;
import com.slimevoid.collaborative.core.lib.ConfigurationLib;
import com.slimevoid.library.blocks.BlockBase;

public class BlockCollaborativeBase extends BlockBase {

    public BlockCollaborativeBase(int blockID) {
        super(Material.rock, BlockLib.BLOCK_MAX_TILES);
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeStone);
    }

    @Override
    public IIcon[] registerBottomIcons(IIconRegister iconRegister) {
        return this.bottom = BlockLib.registerBottomIcons(iconRegister,
                                                          this.tileEntityMap.length);
    }

    @Override
    public IIcon[] registerTopIcons(IIconRegister iconRegister) {
        return this.top = BlockLib.registerTopIcons(iconRegister,
                                                    this.tileEntityMap.length);
    }

    @Override
    public IIcon[] registerFrontIcons(IIconRegister iconRegister) {
        return this.front = BlockLib.registerFrontIcons(iconRegister,
                                                        this.tileEntityMap.length);
    }

    @Override
    public IIcon[] registerSideIcons(IIconRegister iconRegister) {
        return this.side = BlockLib.registerSideIcons(iconRegister,
                                                      this.tileEntityMap.length);
    }

    @Override
    public int getRenderType() {
        return 0;
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }

    public boolean isCube() {
        return true;
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return ConfigurationLib.customTab;
    }

}
