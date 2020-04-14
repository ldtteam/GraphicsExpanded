/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.ldtteam.graphicsexpanded.util.math;

import java.io.Serializable;
import java.nio.FloatBuffer;

/**
 *
 * Base class for matrices. When a matrix is constructed it will be the identity
 * matrix unless otherwise stated.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 * $Id$
 */
public abstract class Matrix<M extends Matrix<M>> implements Serializable, WriteableToFloatBuffer<M> {

	/**
	 * Constructor for Matrix.
	 */
	protected Matrix() {
		super();
	}

	/**
	 * Set this matrix to be the identity matrix.
	 * @return this
	 */
	public abstract M setIdentity();


	/**
	 * Invert this matrix
	 * @return this
	 */
	public abstract M invert();


	/**
	 * Load from a float buffer. The buffer stores the matrix in column major
	 * (OpenGL) order.
	 *
	 * @param buf A float buffer to read from
	 * @return this
	 */
	public abstract M load(FloatBuffer buf);


	/**
	 * Load from a float buffer. The buffer stores the matrix in row major
	 * (mathematical) order.
	 *
	 * @param buf A float buffer to read from
	 * @return this
	 */
	public abstract M loadTranspose(FloatBuffer buf);


	/**
	 * Negate this matrix
	 * @return this
	 */
	public abstract M negate();

	/**
	 * Store this matrix in a float buffer. The matrix is stored in row
	 * major (maths) order.
	 * @param buf The buffer to store this matrix in
	 * @return this
	 */
	public abstract M storeTranspose(FloatBuffer buf);


	/**
	 * Transpose this matrix
	 * @return this
	 */
	public abstract M transpose();


	/**
	 * Set this matrix to 0.
	 * @return this
	 */
	public abstract M setZero();


	/**
	 * @return the determinant of the matrix
	 */
	public abstract float determinant();


}
