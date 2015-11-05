/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.signals;

import org.texgen.textures.AbstractChannelSignal;

/**
 * Returns a fractal noise signal based on the u,v input values.
 * 
 * @author Andy Gibson
 */
public class NoiseSignal extends AbstractChannelSignal {

	private final double speed;
	private final int octaves;
	private final double size;
	private final double offset;

	/**
	 * Returns a value Fractal Brownian Motion noise by re-sampling noise at
	 * different frequencies and amplitudes.
	 * 
	 */	
	public NoiseSignal() {
		this(8, 10, 1, 0);
	}

	/**
	 * Returns a value Fractal Brownian Motion noise by re-sampling noise at
	 * different frequencies and amplitudes.
	 * 
	 * @param speed
	 *            Rate at which the noise changes
	 */	
	public NoiseSignal(double speed) {
		this(speed, 15, 1, 0);
	}

	/**
	 * Returns a value Fractal Brownian Motion noise by re-sampling noise at
	 * different frequencies and amplitudes.
	 * 
	 * @param speed
	 *            Rate at which the noise changes
	 * @param octaves
	 *            Number of octaves used to calculate the noise
	 */	
	public NoiseSignal(double speed, int octaves) {
		this(speed, octaves, 1, 0);
	}

	/**
	 * Returns a value Fractal Brownian Motion noise by re-sampling noise at
	 * different frequencies and amplitudes.
	 * 
	 * @param speed
	 *            Rate at which the noise changes
	 * @param octaves
	 *            Number of octaves used to calculate the noise
	 * @param size
	 *            Range of the final value (usually from 0-1)
	 */	
	public NoiseSignal(double speed, int octaves, double size) {
		this(speed, octaves, size, 0);
	}

	/**
	 * Returns a value Fractal Brownian Motion noise by re-sampling noise at
	 * different frequencies and amplitudes.
	 * 
	 * @param speed
	 *            Rate at which the noise changes
	 * @param octaves
	 *            Number of octaves used to calculate the noise
	 * @param size
	 *            Range of the final value (usually from 0-1)
	 * @param offset
	 *            Offset of the u,v values to use to prevent duplicate noise.
	 */
	public NoiseSignal(double speed, int octaves, double size, double offset) {
		this.speed = speed;
		this.octaves = octaves;
		this.size = size;
		this.offset = offset;
	}

	public double getValue(double u, double v) {
		return getNoise().fbmNoise2(getOffset() + (u * getSpeed()),
				getOffset() + (v * getSpeed()), getOctaves())
				* getSize();
	}

	public double getSpeed() {
		return speed;
	}

	public int getOctaves() {
		return octaves;
	}

	public double getSize() {
		return size;
	}

	public double getOffset() {
		return offset;
	}
}
