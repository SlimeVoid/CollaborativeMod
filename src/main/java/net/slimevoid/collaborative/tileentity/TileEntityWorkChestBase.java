package net.slimevoid.collaborative.tileentity;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.slimevoid.collaborative.core.CollaborativeMod;
import net.slimevoid.collaborative.core.lib.ChestLib;
import net.slimevoid.collaborative.core.lib.GuiLib;
import net.slimevoid.collaborative.items.ItemPlanExtended;
import net.slimevoid.library.util.helpers.SlimevoidHelper;

public abstract class TileEntityWorkChestBase extends
        TileEntityCollaborativeBase implements ISidedInventory {

    private ItemStack[] storedPlans;

    public TileEntityWorkChestBase() {
        storedPlans = new ItemStack[ChestLib.getChestSize(this.getExtendedBlockID())];
    }

    @Override
    public boolean onBlockActivated(IBlockState blockState, EntityPlayer entityplayer, EnumFacing side, float xHit, float yHit, float zHit) {
        if (entityplayer.isSneaking()) {
            return false;
        }
        if (this.worldObj.isRemote) {
            return true;
        } else {
            entityplayer.openGui(CollaborativeMod.instance,
            					 GuiLib.GUIID_WORK_CHEST,
                                 this.worldObj,
                                 this.pos.getX(),
                                 this.pos.getY(),
                                 this.pos.getZ());
            return true;
        }
    }

    public int getStartInventorySide(EnumFacing side) {
        return 0;
    }

    public int getSizeInventorySide(EnumFacing side) {
        return this.storedPlans.length;
    }

    @Override
    public int getSizeInventory() {
        return this.storedPlans.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.storedPlans[i];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (this.storedPlans[slot] == null) {
            return null;
        }
        ItemStack itemstack;
        if (this.storedPlans[slot].stackSize <= amount) {
            itemstack = this.storedPlans[slot];
            this.storedPlans[slot] = null;
            this.onInventoryChanged();
            return itemstack;
        }
        itemstack = storedPlans[slot].splitStack(amount);
        if (storedPlans[slot].stackSize == 0) {
            storedPlans[slot] = null;
        }
        this.onInventoryChanged();
        return itemstack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.storedPlans[i] == null) {
            return null;
        } else {
            ItemStack itemstack = this.storedPlans[i];
            storedPlans[i] = null;
            return itemstack;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        storedPlans[i] = itemstack;
        if (itemstack != null
            && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
        this.onInventoryChanged();
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return SlimevoidHelper.isUseableByPlayer(this.worldObj,
                                                 entityplayer,
                                                 this.pos.getX(),
                                                 this.pos.getY(),
                                                 this.pos.getZ(),
                                                 0.5D,
                                                 0.5D,
                                                 0.5D,
                                                 64D);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemPlanExtended;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        int[] slots = new int[this.storedPlans.length];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = i;
        }
        return slots;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, EnumFacing side) {
        return isItemValidForSlot(slot,
                                  itemstack)
               && !side.equals(EnumFacing.DOWN);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, EnumFacing side) {
        return side.equals(EnumFacing.DOWN);
    }

    @Override
    public void onBlockPlacedBy(ItemStack itemstack, EntityLivingBase entity) {
        super.onBlockPlacedBy(itemstack,
                              entity);
        this.storedPlans = new ItemStack[ChestLib.getChestSize(this.getExtendedBlockID())];
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        NBTTagList plans = nbttagcompound.getTagList("Plans",
                                                     10);
        for (int i = 0; i < plans.tagCount(); i++) {
            NBTTagCompound plan = (NBTTagCompound) plans.getCompoundTagAt(i);
            int j = plan.getByte("Plan") & 0xff;
            if (j >= 0 && j < this.storedPlans.length) {
                this.storedPlans[j] = ItemStack.loadItemStackFromNBT(plan);
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        NBTTagList plans = new NBTTagList();
        for (int i = 0; i < storedPlans.length; i++) {
            if (storedPlans[i] != null) {
                NBTTagCompound plan = new NBTTagCompound();
                plan.setByte("Plan",
                             (byte) i);
                this.storedPlans[i].writeToNBT(plan);
                plans.appendTag(plan);
            }
        }
    }

    @Override
    public void addHarvestContents(ArrayList<ItemStack> harvestList) {
        for (ItemStack itemstack : this.storedPlans) {
            if (itemstack != null) {
                harvestList.add(itemstack);
            }
        }
    }

    @Override
    public String getInvName() {
        return ChestLib.getChestName(this.getExtendedBlockID());
    }

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
