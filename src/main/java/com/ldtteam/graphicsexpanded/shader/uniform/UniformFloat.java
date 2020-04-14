package com.ldtteam.graphicsexpanded.shader.uniform;

import org.lwjgl.opengl.GL20;

public class UniformFloat extends Uniform<Float>{
	
	private float currentValue;
	private boolean used = false;
	
	public UniformFloat(final String name){
		super(name);
	}
	
	public void load(final Float value){
		if(!used || currentValue!=value){
			GL20.glUniform1f(super.getLocation(), value);
			used = true;
			currentValue = value;
		}
	}

}
