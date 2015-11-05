/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures;

import org.texgen.utils.PerlinNoise;

/**
 * Abstract Channel Signal class that implements some basic methods and the
 * ChannelSignal interface.
 * 
 * @author Andy Gibson
 */
public abstract class AbstractChannelSignal implements ChannelSignal {

	protected static final PerlinNoise noise = new PerlinNoise();

	/**
	 * Determines the channel signal value based on the u,v inputs. Checks for a
	 * null source passed in and returns 0 if it is null.
	 * 
	 * @param u
	 *            u component used to determine the result.
	 * @param v
	 *            v component used to determine the result.
	 * @param source
	 *            Signal used to calculate the result
	 * @return the double value calculated from the source using the u,v inputs
	 */
	protected double calculateValue(double u, double v, ChannelSignal source) {
		if (source != null) {
			return source.getValue(u, v);
		}
		return 0;
	}
	
	public static PerlinNoise getNoise() {
		return noise;
	}
	
	public double clamp(double value) {
		if (value > 1) {return 1;}
		if (value < 0) {return 0;}
		return value;
	}
}
