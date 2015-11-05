/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.signal;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.ChannelSignal;
import org.texgen.textures.Texture;
import org.texgen.signals.ConstantSignal;
import org.texgen.textures.color.SolidTexture;
import org.texgen.utils.GraphUtils;
import org.texgen.utils.RGBAColor;

/**
 * 
 * AlphaSignal multiplies the alpha channel value of the incoming source texture
 * by a value defined by the alpha signal input. This can be used to make source
 * textures transparent according to the source signal.
 * 
 * <pre>
 * Color c = source.Color(u,v) 
 * c.alpha = filter.getValue(u,v)s
 * </pre>
 * 
 * The alpha result is clamped between 0 and 1.
 * 
 * @author Andy Gibson
 * 
 */
public class AlphaSignal extends AbstractTexture {

	private final ChannelSignal signal;
	private final Texture source;

	public AlphaSignal(RGBAColor color, ChannelSignal signal) {
		this(new SolidTexture(color), signal);
	}

	/**
	 * Constructs an AlphaSignal with a default alpha multiplier of 0.5
	 * 
	 * @param source
	 *            source Texture that we are filtering
	 * 
	 */
	public AlphaSignal(Texture source) {
		this(source, new ConstantSignal(0.5));
	}

	/**
	 * Constructs an AlphaSignal that returns an constant alpha value. Mainly
	 * used to make textures transparent.
	 * 
	 * @param source
	 *            source texture that we are filtering
	 * @param value
	 *            constant value to return as the alpha value
	 * 
	 */
	public AlphaSignal(Texture source, double value) {
		this(source, new ConstantSignal(value));
	}

	/**
	 * Constructs an AlphaSignal that has an alpha value multiplied by the value
	 * from the alpha signal passed in.
	 * 
	 * 
	 * @param source
	 *            source texture that we are filtering
	 * @param signal
	 *            Channel signal that we use to provide an alpha value over u,v
	 * 
	 */
	public AlphaSignal(Texture source, ChannelSignal signal) {
		this.source = source;
		this.signal = signal;
	}

	/**
	 * Returns the color for co-ordinates u,v
	 * 
	 * @param u
	 *            U component used to generate the texture
	 * @param v
	 *            V Component used to generate the texture
	 * @param value
	 *            RGBAColor object used to receive the result
	 */
	public void getColor(double u, double v, RGBAColor value) {
		calculateColorFromTexture(u, v, source, value);

		double a = value.getAlpha();
		a = a * calculateSignalFromSignal(u, v, signal, 0.5);
		a = GraphUtils.clamp(a);
		value.setAlpha(a);
	}

	public ChannelSignal getAlphaSignal() {
		return signal;
	}

	public Texture getSource() {
		return source;
	}
}
