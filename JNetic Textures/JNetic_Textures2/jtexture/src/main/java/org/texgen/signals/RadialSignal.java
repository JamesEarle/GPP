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
 * RadialSignal radiates the signal from 0 to 1 from the center of the texture
 * to the value defined by radius. The middle edges won't go to 1 with the
 * default radius, they go to 1 at the corners.
 * 
 * @author Andy Gibson
 */
public class RadialSignal extends AbstractChannelSignal {

	private final double radius;

	public RadialSignal() {
		this(0.707);
	}

	public RadialSignal(double radius) {
		this.radius = radius;
	}

	public double getValue(double u, double v) {
		u = u - 0.5;
		v = v - 0.5;

		double dist = Math.sqrt((u * u) + (v * v));
		if (radius != 0) {
			dist = dist / radius;
		}
		return dist;

	}

	public double getRadius() {
		return radius;
	}
}
