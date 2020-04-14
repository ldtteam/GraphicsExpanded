package com.ldtteam.graphicsexpanded.shader.uniform;

import org.lwjgl.opengl.GL20;

/**
 * Simple wrapper class around {@link UniformPrimitive.Int} to indicate that the uniform
 * value is actually a Texture Sampler in the Shader.
 *
 * It is preferred to use location binding available in OpenGL 3.3 or Higher however!
 */
public class UniformSampler extends UniformPrimitive.Int {
	public UniformSampler(final String name) {
		super(name);
	}
}
