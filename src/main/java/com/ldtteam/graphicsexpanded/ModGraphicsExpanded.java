package com.ldtteam.graphicsexpanded;

import com.ldtteam.graphicsexpanded.gpu.GPUMemoryManager;
import com.ldtteam.graphicsexpanded.shader.ShaderManager;
import com.ldtteam.graphicsexpanded.util.constant.Constants;
import com.ldtteam.graphicsexpanded.util.log.Log;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;

@Mod(
  Constants.General.MOD_ID
)
public class ModGraphicsExpanded
{

    public ModGraphicsExpanded() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> Mod.EventBusSubscriber.Bus.MOD.bus().get().addListener(this::onClientInit));
    }

    public void onClientInit(final FMLClientSetupEvent setupEvent) {
        Log.setLogger(LogManager.getLogger());
        Log.getLogger().info("Starting GPU Memory manager.");
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> GPUMemoryManager.getInstance().initialize());
        Log.getLogger().info("Starting Shader manager.");
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> ShaderManager.getInstance().initialize());
    }
}
