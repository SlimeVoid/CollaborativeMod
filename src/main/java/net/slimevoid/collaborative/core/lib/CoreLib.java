package net.slimevoid.collaborative.core.lib;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CoreLib {

    public static final String MOD_ID              = "collaborative";
    public static final String MOD_NAME            = "Collaborative Mod";
    public static final String MOD_VERSION         = "@VERSION@";
    public static final String MOD_DEPENDENCIES    = "";
    public static final String ACTUAL_DEPENDENCIES = "required-after:Forge@[@FORGEVERSION@,),SlimevoidLib@[@SLIMEVERSION@,)";
    public static final String MOD_CHANNEL         = "COLLABORATIVE";
    public static final String CLIENT_PROXY        = "net.slimevoid.collaborative.client.proxy.ClientProxy";
    public static final String COMMON_PROXY        = "net.slimevoid.collaborative.proxy.CommonProxy";
    @SideOnly(Side.CLIENT)
    public static boolean      OPTIFINE_INSTALLED  = FMLClientHandler.instance().hasOptifine();

}
