package net.slimevoid.collaborative.proxy;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.collaborative.container.ContainerWorkBench;
import net.slimevoid.collaborative.container.ContainerWorkChest;
import net.slimevoid.collaborative.core.CollaborativeMod;
import net.slimevoid.collaborative.core.lib.ConfigurationLib;
import net.slimevoid.collaborative.core.lib.GuiLib;
import net.slimevoid.collaborative.core.lib.PacketLib;
import net.slimevoid.collaborative.tileentity.TileEntityWorkBench;
import net.slimevoid.collaborative.tileentity.TileEntityWorkChestBase;
import net.slimevoid.library.ICommonProxy;
import net.slimevoid.library.util.helpers.BlockHelper;
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
    }

    @Override
    public void init() {
        PacketLib.registerPacketHandlers();
        NetworkRegistry.INSTANCE.registerGuiHandler(CollaborativeMod.instance,
                                                    CollaborativeMod.proxy);
    }

    @Override
    public void postInit() {
    }

    @Override
    public void registerTickHandlers() {
    }

    @Override
    public void registerEventHandlers() {
    }

    @Override
    public void registerRenderInformation() {
    }

    @Override
    public String getMinecraftDir() {
        return null;
    }

    @Override
    public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {
    }

    @Override
    public boolean isClient(World world) {
        return false;
    }
}
