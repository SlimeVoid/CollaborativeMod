package net.slimevoid.collaborative.core.lib;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CoreLib {

    public static final String MOD_ID              = "Collaborative";
    public static final String MOD_NAME            = "Collaborative Mod";
    public static final String MOD_VERSION         = "@VERSION@";
    public static final String MOD_DEPENDENCIES    = "required-after:SlimevoidLib";
    @Deprecated
    public static final String MOD_CHANNEL         = MOD_ID;
    public static final String CLIENT_PROXY        = "net.slimevoid.collaborative.client.proxy.ClientProxy";
    public static final String COMMON_PROXY        = "net.slimevoid.collaborative.proxy.CommonProxy";
    @SideOnly(Side.CLIENT)
    public static boolean      OPTIFINE_INSTALLED  = FMLClientHandler.instance().hasOptifine();

}
