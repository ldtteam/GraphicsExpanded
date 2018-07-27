package com.ldtteam.graphicsexpanded;

import com.ldtteam.graphicsexpanded.gpu.GPUMemoryManager;
import com.ldtteam.graphicsexpanded.util.constant.Constants;
import com.ldtteam.graphicsexpanded.util.log.Log;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
  modid = Constants.General.MOD_ID,
  name = Constants.General.MOD_NAME,
  version = Constants.General.MOD_VERSION,
  clientSideOnly = true
)
@SideOnly(Side.CLIENT)
public class ModGraphicsExpanded
{

    @Mod.EventHandler
    public void onFMLPreInitialization(final FMLPreInitializationEvent event)
    {
        Log.setLogger(event.getModLog());

        Log.getLogger().info("Starting GPU Memory manager.");
        GPUMemoryManager.getInstance().initialize();
    }
}
