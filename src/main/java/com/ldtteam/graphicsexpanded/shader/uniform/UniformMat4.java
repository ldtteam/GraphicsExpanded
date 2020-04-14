package com.ldtteam.graphicsexpanded.shader.uniform;

import com.ldtteam.graphicsexpanded.util.math.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

public class UniformMat4 extends Uniform<Matrix4f> {
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

	public UniformMat4(final String name) {
		super(name);
	}
	
	public void load(final Matrix4f matrix){
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4fv(super.getLocation(), false, matrixBuffer);
	}

}
