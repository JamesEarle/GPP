/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.uv;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Takes the U,V values and adjusts them by a noise function, and uses the new
 * U,V to get the result from a source texture. The calculated U,V are scaled to
 * fix in the same 0..1 range. This creates a perturbed UV effect in the final
 * texture.
 * 
 * @author Andy Gibson
 */
public class UVNoiseTranslate extends AbstractTexture {

	private final Texture source;
	private final double speed;
	private final int octaves;
	private final double size;

	/**
	 * 
	 * Constructor that uses a default speed,octave and size of 3,10 and 0.25
	 * 
	 * @param source
	 *            Texture to use
	 */
	public UVNoiseTranslate(Texture source) {
		this(source, 3, 10, 0.25);
	}

	/**
	 * Constructor that allows all parameters to be set
	 * 
	 * @param source
	 *            Texture to use
	 * @param speed -
	 *            rate of change for the noise
	 * @param octaves -
	 *            number of octaves to generate
	 * @param size -
	 *            Scalar value to indicate how much to perturb the UV
	 */
	public UVNoiseTranslate(Texture source, double speed, int octaves,
			double size) {
		this.source = source;
		this.speed = speed;
		this.octaves = octaves;
		this.size = size;
	}

	public void getColor(double u, double v, RGBAColor value) {

		double maxSize = 1 + size;

		double du = noise.fbmNoise2(u * speed, v * speed, octaves) * size;
		double dv = noise
				.fbmNoise2((u * speed) + 20, (v * speed) + 20, octaves)
				* size;
		u = (u + du) / maxSize;
		v = (v + dv) / maxSize;
		calculateColorFromTexture(u, v, source, value);

	}

}
