package com.slimevoid.collaborative.proxy;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.slimevoid.collaborative.container.ContainerWorkBench;
import com.slimevoid.collaborative.container.ContainerWorkChest;
import com.slimevoid.collaborative.core.CollaborativeMod;
import com.slimevoid.collaborative.core.lib.ConfigurationLib;
import com.slimevoid.collaborative.core.lib.GuiLib;
import com.slimevoid.collaborative.core.lib.PacketLib;
import com.slimevoid.collaborative.tileentity.TileEntityWorkBench;
import com.slimevoid.collaborative.tileentity.TileEntityWorkChestBase;
import com.slimevoid.library.ICommonProxy;
import com.slimevoid.library.IPacketHandling;
import com.slimevoid.library.util.helpers.BlockHelper;

import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy implements ICommonProxy {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiLib.GUIID_WORK_BENCH) {
            TileEntityWorkBench tileentity = (TileEntityWorkBench) BlockHelper.getTileEntity(world,
                                                                                             x,
                                                                                             y,
                                                                                             z,
                                                                                             TileEntityWorkBench.class);
            if (tileentity != null) {
                return new ContainerWorkBench(player.inventory, tileentity);
            }
        } else if (ID == GuiLib.GUIID_WORK_CHEST) {
            TileEntityWorkChestBase tileentity = (TileEntityWorkChestBase) BlockHelper.getTileEntity(world,
                                                                                                     x,
                                                                                                     y,
                                                                                                     z,
                                                                                                     TileEntityWorkChestBase.class);
            if (tileentity != null) {
                return new ContainerWorkChest(player.inventory, tileentity);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // TODO :: Auto-generated method stub
        return null;
    }

    @Override
    public void registerConfigurationProperties(File configFile) {
        ConfigurationLib.CommonConfig(configFile);
    }

    @Override
    public void preInit() {
        PacketLib.registerPacketHandlers();
        NetworkRegistry.INSTANCE.registerGuiHandler(CollaborativeMod.instance,
                                                    CollaborativeMod.proxy);
    }

    @Override
    public void registerTickHandlers() {
        // TODO :: Auto-generated method stub

    }

    @Override
    public void registerEventHandlers() {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerRenderInformation() {
        // TODO :: Auto-generated method stub

    }

    @Override
    public String getMinecraftDir() {
        // TODO :: Auto-generated method stub
        return null;
    }

    @Override
    public IPacketHandling getPacketHandler() {
        // TODO :: Auto-generated method stub
        return null;
    }

    @Override
    public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {
        // TODO :: Auto-generated method stub

    }

    @Override
    public boolean isClient(World world) {
        // TODO :: Auto-generated method stub
        return false;
    }
}
