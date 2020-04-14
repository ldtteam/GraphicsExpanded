package com.ldtteam.graphicsexpanded.shader.uniform;

import com.ldtteam.graphicsexpanded.util.math.Matrix;
import com.ldtteam.graphicsexpanded.util.math.Matrix2f;
import com.ldtteam.graphicsexpanded.util.math.Matrix3f;
import com.ldtteam.graphicsexpanded.util.math.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.util.function.BiConsumer;

public class UniformMatrix<M extends Matrix<M>> extends FloatBufferWritingUniform<M> {

	public UniformMatrix(final String name, final int dimension, final BiConsumer<Integer, FloatBuffer> openGlUploader) {
		super(name, dimension^2 , openGlUploader);
	}

	public static class Mat2 extends UniformMatrix<Matrix2f> {

		public Mat2(final String name) {
			super(name,
					2,
					(location, buffer) -> GL20.glUniformMatrix2fv(location, false, buffer));
		}
	}

	public static class Mat3 extends UniformMatrix<Matrix3f> {

		public Mat3(final String name) {
			super(name,
					3,
					(location, buffer) -> GL20.glUniformMatrix3fv(location, false, buffer));
		}
	}

	public static class Mat4 extends UniformMatrix<Matrix4f> {

		public Mat4(final String name) {
			super(name,
					4,
					(location, buffer) -> GL20.glUniformMatrix4fv(location, false, buffer));
		}
	}

}
