/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.complex;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.textures.color.SolidTexture;
import org.texgen.utils.GraphUtils;
import org.texgen.utils.RGBAColor;

/**
 * 
 * A Noisy texture that takes an input source and sets the alpha channel based
 * on noise(u,v)*size; This only creates a texture with noisy transparency. To
 * mix with another texture see dirty.
 * 
 * @author Andy Gibson
 */
public class NoisyTexture extends AbstractTexture {

	private final Texture source;
	private final int octaves;
	private final double size;
	private final double speed;

	/**
	 * Makes a noisy texure that is a constant black that will be adjusted by
	 * noise.
	 */
	public NoisyTexture() {
		this(RGBAColor.black());
	}

	public NoisyTexture(RGBAColor color) {
		this(new SolidTexture(color));
	}

	/**
	 * 
	 * @param source
	 *            Texture to use for the source color that will be adjusted with
	 *            noise
	 */
	public NoisyTexture(Texture source) {
		this(source, 10, 10, 0.5);
	}

	/**
	 * 
	 * @param source
	 *            The source texure to be adjusted by noise
	 * @param speed
	 *            The rate of change of the noise over the texture (default 10)
	 * @param octaves
	 *            Number of octaves to use to calculate the texture (default 10)
	 * @param size
	 *            The size of the noise determines how much it affects the
	 *            texture (default 0.5)
	 */
	public NoisyTexture(Texture source, double speed, int octaves, double size) {
		if (source == null) {
			throw new IllegalArgumentException("Source texture cannot be null");
		}
		this.source = source;
		this.size = size;
		this.speed = speed;
		this.octaves = octaves;
	}

	public NoisyTexture(RGBAColor color, double speed, int octaves, double size) {
		this(new SolidTexture(color), speed, octaves, size);
	}

	public void getColor(double u, double v, RGBAColor value) {
		calculateColorFromTexture(u, v, source, value);
		double noiseValue = noise.fbmNoise2(100+(u * speed), 200+(v * speed), octaves)
				* size;
		value.setAlpha(GraphUtils.clamp(noiseValue));
	}

	public Texture getSource() {
		return source;
	}

	public int getOctaves() {
		return octaves;
	}

	public double getSize() {
		return size;
	}

	public double getSpeed() {
		return speed;
	}
}
