/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.demo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.textures.color.HorizontalGradient;
import org.texgen.textures.complex.NoisyTexture;
import org.texgen.textures.composite.MergedTexture;
import org.texgen.utils.ColorGradient;
import org.texgen.utils.RGBAColor;

/**
 * Composite texture made up of clouds laid over the sunset texture
 * 
 * @author Andy Gibson
 */
public class CloudTexture extends AbstractTexture {

	private final ColorGradient redCloudGradient;
	private final ColorGradient lightCloudGradient;
	private final Texture sunset = new SunsetTexture();
	private static final ConcurrentMap<Double, MergedTexture> textureCache = new ConcurrentHashMap<Double, MergedTexture>();	

	public CloudTexture() {

		redCloudGradient = new ColorGradient()
				.add(new RGBAColor(255, 240, 230)).add(
						new RGBAColor(255, 140, 64));
		lightCloudGradient = new ColorGradient().add(
				new RGBAColor(255, 250, 240)).add(new RGBAColor(255, 255, 245));

	}

	protected void getColorWithoutCache(double u, double v, RGBAColor value) {

		final NoisyTexture redCloud = new NoisyTexture(new HorizontalGradient(
				redCloudGradient), 2 + (v * 14), 10,0.3 + (v * 1.2));
		
		final NoisyTexture lightCloud = new NoisyTexture(
				new HorizontalGradient(lightCloudGradient), 0.4 + (v * 18), 10,0.3 + v);
		
		final MergedTexture clouds = new MergedTexture(lightCloud, redCloud);
		final MergedTexture mixer = new MergedTexture(sunset, clouds);

		mixer.getColor(u, v, value);
	}

	/**
	 * Calculates the cloud texture but tries to improve on performance by using
	 * a texture cache. Barely improves performance, but leaving around in case
	 * there is any sudden enlightenment of how to properly utilize it.
	 * 
	 * @param u
	 *            U value to calculate
	 * @param v
	 *            V value to calculate
	 * @param value
	 *            Object to contain the result color
	 */
	public void getColor(double u, double v, RGBAColor value) {
		final Double vObject = new Double(v);

		MergedTexture mixer = textureCache.get(vObject);
		if (mixer == null) {
			final MergedTexture clouds;
			final NoisyTexture redCloud;
			final NoisyTexture lightCloud;

			redCloud = new NoisyTexture(
					new HorizontalGradient(redCloudGradient), 2 + (v * 14),10,0.3 + (v * 1.2));
			lightCloud = new NoisyTexture(new HorizontalGradient(
					lightCloudGradient), 0.4 + (v * 18), 10,0.3 + v);
			clouds = new MergedTexture(lightCloud, redCloud);
			mixer = new MergedTexture(sunset, clouds);
			textureCache.putIfAbsent(vObject, mixer);

		}

		mixer.getColor(u, v, value);
	}

}
