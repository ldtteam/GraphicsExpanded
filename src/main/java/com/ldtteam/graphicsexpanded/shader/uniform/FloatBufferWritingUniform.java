package com.ldtteam.graphicsexpanded.shader.uniform;

import com.ldtteam.graphicsexpanded.util.math.WriteableToFloatBuffer;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.function.BiConsumer;

class FloatBufferWritingUniform<T extends WriteableToFloatBuffer<T>> extends Uniform<T> {
    protected final FloatBuffer dataBuffer;
    protected final BiConsumer<Integer, FloatBuffer> openGlUploader;

    FloatBufferWritingUniform(final String name, final int dimension, final BiConsumer<Integer, FloatBuffer> openGlUploader) {
        super(name);
        dataBuffer = BufferUtils.createFloatBuffer(dimension);
        this.openGlUploader = openGlUploader;
    }

    public void load(final T toLoad) {
        toLoad.store(dataBuffer);
        dataBuffer.flip();
        this.openGlUploader.accept(getLocation(), dataBuffer);
    }
}
