package slimevoid.projectbench.tileentity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import slimevoid.projectbench.core.PBCore;
import slimevoid.projectbench.core.lib.NBTLib;

public class TileEntityProjectBase extends TileEntityBase {
	
	protected int rotation;
	protected boolean active;
	
	public TileEntityProjectBase() {
		this.rotation = 0;
		this.active = false;
	}
	
	@Override
	public void onBlockPlaced(ItemStack itemstack, int side, EntityLivingBase entity) {
		this.rotation = (int) Math.floor((double)((entity.rotationYaw * 4F) / 360F) + 0.5D) & 3;
	}

	@Override
	public int getBlockID() {
		return PBCore.blockProjectBase.blockID;
	}
	
	public int getLightValue() {
		return this.active ? 13 : 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.rotation = nbttagcompound.getByte(NBTLib.TILE_ROTATION);
		int status = nbttagcompound.getByte(NBTLib.TILE_ACTIVE);
		this.active = status > 0;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setByte(NBTLib.TILE_ROTATION, (byte) this.rotation);
		nbttagcompound.setByte(NBTLib.TILE_ACTIVE, (byte) (this.active ? 1 : 0));
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.customParam1);
		this.onInventoryChanged();
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		Packet packet = new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, nbttagcompound);
		return packet;
	}

	public int getBlockTexture(int x, int y, int z, int side) {
		// TODO :: Side based on Rotation
		switch (rotation) {
		case 0:
			return side;
		case 1:
			return side == 2 ? 5 : side == 5 ? 2 : side;
		case 2:
			return side == 2 ? 3 : side == 3 ? 2 : side;
		case 3:
			return side == 2 ? 4 : side == 4 ? 2 : side;
		}
		return side;
	}
}
