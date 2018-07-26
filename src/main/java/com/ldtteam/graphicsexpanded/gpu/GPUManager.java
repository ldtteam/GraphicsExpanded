package com.ldtteam.graphicsexpanded.gpu;

import com.ldtteam.animatrix.util.Log;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@SideOnly(Side.CLIENT)
public class GPUManager
{
    private static GPUManager ourInstance = new GPUManager();

    public static GPUManager getInstance()
    {
        return ourInstance;
    }

    private final ConcurrentMap<WeakReference<VBO>, Integer> VBOs = new ConcurrentHashMap();
    private final ConcurrentMap<WeakReference<VAO>, Integer> VAOs = new ConcurrentHashMap();

    private GPUManager()
    {
    }

    public void initialize()
    {

    }

    /**
     * Creates a new VAO on the GPU.
     * @return The new VAO.
     */
    public VAO createVAO() {
        final int id = GL30.glGenVertexArrays();
        final VAO vao = new VAO(id);

        VAOs.put(new WeakReference<>(vao), id);

        return vao;
    }

    /**
     * Creates a new VBO for the given type.
     *
     * @param type The type.
     * @return The VBO.
     */
    public VBO createVBO(final int type){
        final int id = GL15.glGenBuffers();
        final VBO vbo = new VBO(id, type);

        VBOs.put(new WeakReference<>(vbo), id);

        return vbo;
    }

    /**
     * Clears up the GPU when VBOs and VAOs are no longer needed.
     */
    private static final class ClearingRunnable implements Runnable
    {

        private final GPUManager managerToHandle;

        public ClearingRunnable(final GPUManager managerToHandle) {this.managerToHandle = managerToHandle;}

        @Override
        public void run()
        {
            final List<Map.Entry<WeakReference<VBO>, Integer>> removedVBOs =
              managerToHandle.VBOs.entrySet().stream().filter(entry -> entry.getKey().get() == null).collect(Collectors.toList());
            final List<Map.Entry<WeakReference<VAO>, Integer>> removedVAOs =
              managerToHandle.VAOs.entrySet().stream().filter(entry -> entry.getKey().get() == null).collect(Collectors.toList());

            removedVBOs.forEach(weakReferenceIntegerEntry -> {
                managerToHandle.VBOs.remove(weakReferenceIntegerEntry.getKey());
                GL15.glDeleteBuffers(weakReferenceIntegerEntry.getValue());
            });


            removedVAOs.forEach(weakReferenceIntegerEntry -> {
                managerToHandle.VAOs.remove(weakReferenceIntegerEntry.getKey());
                GL30.glDeleteVertexArrays(weakReferenceIntegerEntry.getValue());
            });

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
                    Minecraft.getMinecraft().addScheduledTask(this);
                }
                catch (final Exception ex)
                {
                    Log.getLogger().error("Failed to reregister the GPUManager. GPU memory leaks will occur.", ex);
                }
            });
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
        return "GPUManager{" +
                 "VBOs=" + VBOs +
                 ", VAOs=" + VAOs +
                 '}';
    }
}
