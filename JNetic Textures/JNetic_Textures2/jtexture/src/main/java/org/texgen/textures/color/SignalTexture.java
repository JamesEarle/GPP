/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.color;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.ChannelSignal;
import org.texgen.utils.RGBAColor;

/**
 * Composes a texure from one or more individual signal inputs by setting the
 * color values based on the input signal(s). <br/>
 * <br/>
 * Construct with one signal used on all channels, or 1 signal per channel. This
 * class is immutable and thread safe, assuming the signal references are also
 * thread safe.
 * 
 * @author Andy Gibson
 */
public class SignalTexture extends AbstractTexture {

	private final ChannelSignal red;
	private final ChannelSignal blue;
	private final ChannelSignal green;
	private final ChannelSignal alpha;

	/**
	 * Construct a signal texture using one signal for the red, green, blue and
	 * alpha channels.
	 * 
	 * @param signal
	 *            Signual to compose the color from.
	 */
	public SignalTexture(ChannelSignal signal) {
		this(signal, signal, signal, signal);
	}

	/**
	 * Construct a signal texture using a signal for each of the red, green,
	 * blue and alpha channels.
	 * 
	 * @param red
	 *            Signal to use for the red channel
	 * @param blue
	 *            Signal to use for the blue channel
	 * @param green
	 *            Signal to use for the green channel
	 * @param alpha
	 *            Signal to use for the alpha channel
	 */
	public SignalTexture(ChannelSignal red, ChannelSignal blue,
			ChannelSignal green, ChannelSignal alpha) {
		this.red = red;
		this.blue = blue;
		this.green = green;
		this.alpha = alpha;
	}

	public void getColor(double u, double v, RGBAColor value) {
		value.setColor(calculateSignalFromSignal(u, v, red),
				calculateSignalFromSignal(u, v, blue),
				calculateSignalFromSignal(u, v, green),
				calculateSignalFromSignal(u, v, alpha));

	}

	public ChannelSignal getRed() {
		return red;
	}

	public ChannelSignal getBlue() {
		return blue;
	}

	public ChannelSignal getGreen() {
		return green;
	}

	public ChannelSignal getAlpha() {
		return alpha;
	}

}
