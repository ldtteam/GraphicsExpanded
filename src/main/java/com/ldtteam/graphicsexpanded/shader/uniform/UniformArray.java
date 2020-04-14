package com.ldtteam.graphicsexpanded.shader.uniform;

import com.ldtteam.graphicsexpanded.util.math.Matrix4f;
import com.ldtteam.graphicsexpanded.util.math.Vector2f;
import com.ldtteam.graphicsexpanded.util.math.Vector3f;
import com.ldtteam.graphicsexpanded.util.math.Vector4f;

import java.util.function.Function;

public class UniformArray<T, U extends Uniform<T>> extends Uniform<T[]> {

    private U[] arrayUniforms;

    public UniformArray(final String name, final int size, final Function<Integer, U[]> arrayConstructor, final Function<String, U> constructor) {
        super(name);
        arrayUniforms = arrayConstructor.apply(size);
        for(int i=0;i<size;i++){
            arrayUniforms[i] = constructor.apply(name + "["+i+"]");
        }
    }

    @Override
    public void storeUniformLocation(final int programID) {
        for(final U uniform : arrayUniforms){
            uniform.storeUniformLocation(programID);
        }
    }

    public void load(final T[] toLoad){
        for(int i=0;i<toLoad.length;i++){
            arrayUniforms[i].load(toLoad[i]);
        }
    }

    public static class Mat4 extends UniformArray<Matrix4f, UniformMat4> {

        public Mat4(final String name, final int size) {
            super(name,
                    size,
                    UniformMat4[]::new,
                    UniformMat4::new
            );
        }
    }

    public static class Vec2 extends UniformArray<Vector2f, UniformVec2> {

        public Vec2(final String name, final int size) {
            super(name,
                    size,
                    UniformVec2[]::new,
                    UniformVec2::new
            );
        }
    }

    public static class Vec3 extends UniformArray<Vector3f, UniformVec3> {

        public Vec3(final String name, final int size) {
            super(name,
                    size,
                    UniformVec3[]::new,
                    UniformVec3::new
            );
        }
    }

    public static class Vec4 extends UniformArray<Vector4f, UniformVec4> {

        public Vec4(final String name, final int size) {
            super(name,
                    size,
                    UniformVec4[]::new,
                    UniformVec4::new
            );
        }
    }

    public static class Bool extends UniformArray<Boolean, UniformPrimitive.Bool> {

        public Bool(final String name, final int size) {
            super(name,
                    size,
                    UniformPrimitive.Bool[]::new,
                    UniformPrimitive.Bool::new
            );
        }
    }

    public static class Int extends UniformArray<Integer, UniformPrimitive.Int> {

        public Int(final String name, final int size) {
            super(name,
                    size,
                    UniformPrimitive.Int[]::new,
                    UniformPrimitive.Int::new
            );
        }
    }

    public static class Float extends UniformArray<Float, UniformPrimitive.Float> {

        public Float(final String name, final int size) {
            super(name,
                    size,
                    UniformPrimitive.Float[]::new,
                    UniformPrimitive.Float::new
            );
        }
    }

}
