package com.ldtteam.graphicsexpanded.shader.uniform;

import com.ldtteam.graphicsexpanded.util.math.Vector;
import com.ldtteam.graphicsexpanded.util.math.Vector2f;
import com.ldtteam.graphicsexpanded.util.math.Vector3f;
import com.ldtteam.graphicsexpanded.util.math.Vector4f;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.util.function.BiConsumer;

public class UniformVector<V extends Vector<V>> extends FloatBufferWritingUniform<V> {

	public UniformVector(final String name, final int dimension, final BiConsumer<Integer, FloatBuffer> openGlUploader) {
		super(name, dimension, openGlUploader);
	}

	public static class Vec2 extends UniformVector<Vector2f> {

		public Vec2(final String name) {
			super(name,
					2,
					GL20::glUniform2fv);
		}
	}

	public static class Vec3 extends UniformVector<Vector3f> {

		public Vec3(final String name) {
			super(name,
					3,
					GL20::glUniform3fv);
		}
	}

	public static class Vec4 extends UniformVector<Vector4f> {

		public Vec4(final String name) {
			super(name,
					4,
					GL20::glUniform4fv);
		}
	}
}
