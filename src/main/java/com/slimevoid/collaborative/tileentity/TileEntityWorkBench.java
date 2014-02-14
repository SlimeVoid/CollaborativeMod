package com.slimevoid.collaborative.tileentity;

import java.util.ArrayList;

import com.slimevoid.collaborative.core.CollaborativeMod;
import com.slimevoid.collaborative.core.lib.BlockLib;
import com.slimevoid.collaborative.core.lib.ContainerLib;
import com.slimevoid.collaborative.core.lib.GuiLib;
import com.slimevoid.collaborative.items.ItemPlan;
import com.slimevoid.collaborative.items.ItemPlanExtended;
import com.slimevoid.library.util.helpers.SlimevoidHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityWorkBench extends TileEntityCollaborativeBase implements
        ISidedInventory {

    private ItemStack[] contents;

    public TileEntityWorkBench() {
        contents = new ItemStack[28];
    }

    @Override
    public int getExtendedBlockID() {
        return BlockLib.BLOCK_WORK_BENCH_ID;
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public boolean onBlockActivated(EntityPlayer entityplayer) {
        if (entityplayer.isSneaking()) {
            return false;
        }
        if (this.worldObj.isRemote) {
            return true;
        } else {
            entityplayer.openGui(CollaborativeMod.instance,
                                 GuiLib.GUIID_WORK_BENCH,
                                 this.worldObj,
                                 this.xCoord,
                                 this.yCoord,
                                 this.zCoord);
            return true;
        }
    }

    @Override
    public int getSizeInventory() {
        // this is the internal persistent inventory
        // 3 * 3 crafting window
        // 1 plan slot
        // 2 rows of 9
        return 28;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        if (isValidSlot(i)) {
            return this.contents[i];
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (this.getStackInSlot(slot) == null) {
            return null;
        }
        ItemStack itemstack;
        if (this.contents[slot].stackSize <= amount) {
            itemstack = this.contents[slot];
            this.contents[slot] = null;
            this.onInventoryChanged();
            return itemstack;
        }
        itemstack = contents[slot].splitStack(amount);
        if (contents[slot].stackSize == 0) {
            contents[slot] = null;
        }
        this.onInventoryChanged();
        return itemstack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.getStackInSlot(i) == null) {
            return null;
        } else {
            ItemStack itemstack = this.contents[i];
            contents[i] = null;
            return itemstack;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        contents[i] = itemstack;
        if (itemstack != null
            && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
        this.onInventoryChanged();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return SlimevoidHelper.isUseableByPlayer(this.worldObj,
                                                 entityplayer,
                                                 this.xCoord,
                                                 this.yCoord,
                                                 this.zCoord,
                                                 0.5D,
                                                 0.5D,
                                                 0.5D,
                                                 64D);
    }

    public boolean isValidSlot(int i) {
        return i >= 0 && i < this.getSizeInventory();
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        if (isValidSlot(i)) {
            return itemstack == null ? false : i == ContainerLib.PLAN_SLOT ? itemstack.getItem() instanceof ItemPlan ? true : itemstack.getItem() instanceof ItemPlanExtended ? true : false : true;
        }
        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[] {
                27,
                26,
                25,
                24,
                23,
                22,
                21,
                20,
                19,
                18,
                17,
                16,
                15,
                14,
                13,
                12,
                11,
                10 };
    }

    private boolean isInventorySlot(int slot) {
        return slot >= 10 && slot < this.getSizeInventory();
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
        return isInventorySlot(slot)
               && ForgeDirection.getOrientation(side) != ForgeDirection.DOWN;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
        return isInventorySlot(slot)
               && ForgeDirection.getOrientation(side) != ForgeDirection.DOWN;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        NBTTagList items = nbttagcompound.getTagList("Items",
                                                     10);
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(i);
            int j = item.getByte("Slot") & 0xff;
            if (j >= 0 && j < this.contents.length) {
                this.contents[j] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot",
                             (byte) i);
                this.contents[i].writeToNBT(item);
                items.appendTag(item);
            }
        }
        nbttagcompound.setTag("Items",
                              items);
    }

    @Override
    public void addHarvestContents(ArrayList<ItemStack> harvestList) {
        for (ItemStack itemstack : this.contents) {
            if (itemstack != null) {
                harvestList.add(itemstack);
            }
        }
    }

    @Override
    public String getInvName() {
        return BlockLib.BLOCK_WORK_BENCH;
    }

}
