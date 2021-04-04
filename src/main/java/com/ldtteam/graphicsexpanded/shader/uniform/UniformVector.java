package com.ldtteam.graphicsexpanded.shader.uniform;

import com.ldtteam.graphicsexpanded.util.math.*;
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

		public void load(final float x, final float y) {
			this.load(new Vector2f(x, y));
		}
	}

	public static class Vec3 extends UniformVector<Vector3f> {

		public Vec3(final String name) {
			super(name,
					3,
					GL20::glUniform3fv);
		}

		public void load(final net.minecraft.util.math.vector.Vector3f vector3f) {
			this.load(vector3f.getX(), vector3f.getY(), vector3f.getZ());
		}

		public void load(final float x, final float y, final float z) {
			this.load(new Vector3f(x, y, z));
		}
	}

	public static class Vec4 extends UniformVector<Vector4f> {

		public Vec4(final String name) {
			super(name,
					4,
					GL20::glUniform4fv);
		}

		public void load(final Quaternion quaternion) {
			this.loadWithBuffer(quaternion::store);
		}

		public void load(final net.minecraft.util.math.vector.Quaternion quaternion) {
			this.load(quaternion.getX(), quaternion.getY(), quaternion.getZ(), quaternion.getW());
		}

		public void load(final net.minecraft.util.math.vector.Vector4f vector4f) {
			this.load(vector4f.getX(), vector4f.getY(), vector4f.getZ(), vector4f.getW());
		}

		public void load(final float x, final float y, final float z, final float w) {
			this.load(new Vector4f(x, y, z, w));
		}
	}
}
