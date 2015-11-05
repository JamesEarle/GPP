/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.utils;

/**
 * Some utility functions for clamping values, and calculating sine values and
 * distances
 * 
 * @author Andy Gibson
 */
public class GraphUtils {

	public static int clamp(int number) {
		if (number > 255) {
			number = 255;
		}

		if (number < 0) {
			number = 0;
		}
		return number;
	}

	public static double clamp(double number) {
		if (number > 1) {
			number = 1;
		}

		if (number < 0) {
			number = 0;
		}
		return number;
	}

	public static double calculateSine(double input, int octaves) {
		double res = 0;
		double amp = 1;
		double div = 0;
		for (int i = 0; i < octaves; i++) {
			div = div + amp;
			res += (Math.sin(input) * amp);
			input = input * 1.78;
			amp = amp / 2;
		}
		return res / div;
	}

	/**
	 * Returns the distance from point u,v to point pu,pv
	 * 
	 * @param u
	 *            u coordinate of point u,v
	 * @param v
	 *            v coordinate of point u,v
	 * @param pu
	 *            pu coordinate of point pu,pv
	 * @param pv
	 *            pv coordinate of point pu,pv
	 * @return The distance from one point to the other using Pythagoras
	 */
	public static double distance(double u, double v, double pu, double pv) {

		double du = pu - u;
		double dv = pv - v;
		dv = dv * dv;
		du = du * du;

		return Math.sqrt(dv + du);
	}
}
