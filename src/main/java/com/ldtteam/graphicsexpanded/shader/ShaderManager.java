package com.ldtteam.graphicsexpanded.shader;

import com.ldtteam.graphicsexpanded.util.log.Log;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public final class ShaderManager
{

    private static final ShaderManager ourInstance = new ShaderManager();

    public static ShaderManager getInstance()
    {
        return ourInstance;
    }

    private final ConcurrentMap<WeakReference<ShaderProgram>, ShaderDeletionHandler> shaders = new ConcurrentHashMap<>();

    private ShaderManager()
    {
    }

    public void initialize()
    {
        Minecraft.getInstance().execute(new ClearingRunnable(this));
    }

    public void registerShader(final ShaderProgram shaderProgram) throws IOException {
        shaders.put(new WeakReference<>(shaderProgram), shaderProgram.init());
        Log.getLogger().info("Created Shader: " + shaderProgram.getProgramID());
    }

    /**
     * Clears up the GPU when VBOs and VAOs are no longer needed.
     */
    private static final class ClearingRunnable implements Runnable
    {

        private final ShaderManager managerToHandle;

        public ClearingRunnable(final ShaderManager managerToHandle) {this.managerToHandle = managerToHandle;}

        @Override
        public void run()
        {
            final List<Map.Entry<WeakReference<ShaderProgram>, ShaderDeletionHandler>> removedShaders =
              managerToHandle.shaders.entrySet().stream().filter(entry -> entry.getKey().get() == null).collect(Collectors.toList());

            removedShaders.forEach(weakReferenceIntegerEntry -> {
                Log.getLogger().info("Deleting Shader: " + weakReferenceIntegerEntry.getValue().getProgramId());
                managerToHandle.shaders.remove(weakReferenceIntegerEntry.getKey());
                weakReferenceIntegerEntry.getValue().run();
            });

            //Reschedule clearing task.
            //We will have to wait a minimal amount of time.
            final Thread rescheduleThread = new Thread(() -> {
                try
                {
                    Thread.sleep(500);
                }
                catch (final InterruptedException ignored)
                {
                }

                try
                {
                    Minecraft.getInstance().execute(this);
                }
                catch (final Exception ex)
                {
                    Log.getLogger().error("Failed to re-register the ShaderManager. GPU memory leaks will occur.", ex);
                }
            });
            rescheduleThread.setDaemon(true);
            rescheduleThread.setName("GE Shader WatchDog.");
            rescheduleThread.start();
        }

        @Override
        public String toString()
        {
            return "ClearingRunnable{" +
                     "managerToHandle=" + managerToHandle +
                     '}';
        }
    }

    @Override
    public String toString()
    {
        return "ShaderManager{" +
                 "shaders=" + shaders +
                 '}';
    }
}
