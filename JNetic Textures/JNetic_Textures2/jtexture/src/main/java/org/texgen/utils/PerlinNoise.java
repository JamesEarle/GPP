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
 * Class for creating noise values based on Ken Perlin's idea for Perlin Noise.
 * Original noise class based on someone elses implementation that I cannot
 * remember. If it's you, let me know!
 * 
 * @author Andy Gibson
 */
public class PerlinNoise {

	static final double randomCoef = 1 / 1073741824.0;

	/**
	 * returns a random number based on the inputs. Identical inputs return
	 * indentical values
	 * 
	 * @param x
	 *            value to use to derive the randome number
	 * @return Random number based on the input
	 */
	private double rawNoise1(int x) {
		x = (x << 13) ^ x;
		double res = (1.0 - ((x * (x * x * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
		return (res + 1) / 2;
	}

	/**
	 * returns a random number based on the inputs. Identical inputs return
	 * indentical values
	 * 
	 * @param x
	 *            value to use to derive the random number
	 * @param y
	 *            value to use to derive the random number
	 * @return Random number based on the input
	 */
	private double rawNoise2(int x, int y) {
		int n = x + y * 57;
		n = (n << 13) ^ n;
		double res = (1.0 - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) * randomCoef);
		return (res + 1) / 2;
	}

	/**
	 * returns a random number based on the inputs. Identical inputs return
	 * indentical values
	 * 
	 * @param x
	 *            value to use to derive the random number
	 * @param y
	 *            value to use to derive the random number
	 * @param z
	 *            value to use to derive the random number
	 * @return Random number based on the input
	 */
	private double rawNoise3(int x, int y, int z) {
		int n = x + (y * 57) + (z * 102);
		n = (n << 13) ^ n;
		double res = (1.0 - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff)
				* randomCoef);// / 1073741824.0);
		return (res + 1) * 0.5;
	}

	/**
	 * Returns a noise value by interpolating between two values based on the
	 * floating point part of the input.
	 * 
	 * @param x
	 *            Value to derive the noise from.
	 * @return Noise value
	 */
	public double noise1(double x) {

		int ix = (int) Math.floor(x);
		double fx = x - ix;
		double v1 = rawNoise1(ix);
		double v2 = rawNoise1(ix + 1);
		return Gradient.cosInterpolate(v1, v2, fx);

	}

	/**
	 * Returns a noise value by interpolating between two values based on the
	 * floating point part of the input.
	 * 
	 * @param x
	 *            Value to derive the noise from.
	 * @param y
	 *            Value to derive the noise from.
	 * @return Noise value
	 */
	public double noise2(double x, double y) {
		int ix = (int) Math.floor(x);
		int iy = (int) Math.floor(y);
		double fy = y - iy;
		double fx = x - ix;

		double v1 = rawNoise2(ix, iy);
		double v2 = rawNoise2(ix + 1, iy);
		double v3 = rawNoise2(ix, iy + 1);
		double v4 = rawNoise2(ix + 1, iy + 1);

		
		double i1 = Gradient.t32Interpolate(v1,v2,fx);
		double i2 = Gradient.t32Interpolate(v3,v4,fx);

		return Gradient.cosInterpolate(i1,i2,fy);

	}

	/**
	 * Returns a noise value by interpolating between two values based on the
	 * floating point part of the input.
	 * 
	 * @param x
	 *            Value to derive the noise from.
	 * @param y
	 *            Value to derive the noise from.
	 * @param z
	 *            Value to derive the noise from.
	 * @return Noise value
	 */
	public double noise3(double x, double y, double z) {
		int ix = (int) Math.floor(x);
		int iy = (int) Math.floor(y);
		int iz = (int) Math.floor(z);
		double fy = y - iy;
		double fx = x - ix;
		double fz = z - iz;

		
		fx = Gradient.cosInterpolate(0, 1, fx);
		fy = Gradient.cosInterpolate(0, 1, fy);
		fz = Gradient.cosInterpolate(0, 1, fz);

		double v1 = rawNoise3(ix, iy, iz);
		double v2 = rawNoise3(ix + 1, iy, iz);
		double v3 = rawNoise3(ix, iy + 1, iz);
		double v4 = rawNoise3(ix + 1, iy + 1, iz);

		double v5 = rawNoise3(ix, iy, iz + 1);
		double v6 = rawNoise3(ix + 1, iy, iz + 1);
		double v7 = rawNoise3(ix, iy + 1, iz + 1);
		double v8 = rawNoise3(ix + 1, iy + 1, iz + 1);

		double i1 = Gradient.cosInterpolate(v1, v2, fx);
		double i2 = Gradient.cosInterpolate(v3, v4, fx);
		double i3 = Gradient.cosInterpolate(v5, v6, fx);
		double i4 = Gradient.cosInterpolate(v7, v8, fx);

		double j1 = Gradient.cosInterpolate(i1, i2, fy);
		double j2 = Gradient.cosInterpolate(i3, i4, fy);

		return Gradient.cosInterpolate(j1, j2, fz);

	}

	public double fbmNoise(double x, int octaves) {
		double res = 0;
		double amp = 1;
		double div = 0;
		for (int i = 0; i < octaves; i++) {
			div = div + amp;
			res += (noise1(x) * amp);
			x = x * 2;
			amp = amp / 2;
		}
		return res / div;
	}

	public double fbmNoise2(double x, double y, int octaves) {
		double res = 0;
		double amp = 1;
		double div = 0;
		for (int i = 0; i < octaves; i++) {
			div = div + amp;
			res += (noise2(x, y) * amp);
			x = x * 2;
			y = y * 2;
			amp = amp / 2;
		}
		return res / div;
	}

	public double fbmNoise3(double x, double y, double z, int octaves) {
		double res = 0;
		double amp = 1;
		double div = 0;
		for (int i = 0; i < octaves; i++) {
			div = div + amp;
			res += (noise3(x, y, z) * amp);
			x = x * 2;
			y = y * 2;
			z = z * 2;
			amp = amp / 2;
		}
		return res / div;
	}

	/**
	 * Takes a sine wave and perturbs it using noise. Repeats for muliple
	 * octaves
	 * 
	 * @param u
	 *            u value to generate the wave from
	 * @param v
	 *            v value to generate the wave from
	 * @param sineScale
	 *            Speed of the sine wave over the u,v values
	 * @param speed
	 *            Speed of the noise over the u,v values
	 * @param octaves
	 *            Number of frequencies to use
	 * @return The noisy sine value
	 */
	public double noisySine(double u, double v, double sineScale,double speed, int octaves) {

		double noiseValue = (fbmNoise2(u * speed, v * speed, octaves)) + u;

		double texture = Math.sin(u + (noiseValue * sineScale));

		texture = 1 - Math.abs(texture);

		return texture;
	}
}
