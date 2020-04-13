package com.ldtteam.graphicsexpanded.shader.uniform;

import net.minecraft.client.renderer.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

public class UniformMatrix extends Uniform{
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

	public UniformMatrix(final String name) {
		super(name);
	}
	
	public void loadMatrix(final Matrix4f matrix){
		matrix.write(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4fv(super.getLocation(), false, matrixBuffer);
	}

}
