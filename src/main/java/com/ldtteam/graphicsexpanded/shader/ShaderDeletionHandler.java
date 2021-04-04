package com.ldtteam.graphicsexpanded.shader;

import org.lwjgl.opengl.GL20;

final class ShaderDeletionHandler implements Runnable {

    private final int programId;

    ShaderDeletionHandler(final int programId) {
        this.programId = programId;
    }

    @Override
    public void run() {
        GL20.glDeleteProgram(programId);
    }

    public int getProgramId() {
        return programId;
    }
}
