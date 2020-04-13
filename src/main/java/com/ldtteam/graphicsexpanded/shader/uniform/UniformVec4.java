package com.ldtteam.graphicsexpanded.shader.uniform;

import com.ldtteam.graphicsexpanded.util.math.Vector4f;
import org.lwjgl.opengl.GL20;

public class UniformVec4 extends Uniform {

	public UniformVec4(final String name) {
		super(name);
	}

	public void loadVec4(final Vector4f vector) {
		loadVec4(vector.getX(), vector.getY(), vector.getZ(), vector.getW());
	}

	public void loadVec4(final float x, final float y, final float z, final float w) {
		GL20.glUniform4f(super.getLocation(), x, y, z, w);
	}
}
