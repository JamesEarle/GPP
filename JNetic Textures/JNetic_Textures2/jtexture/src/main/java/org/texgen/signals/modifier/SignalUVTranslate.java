/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.signals.modifier;

import org.texgen.textures.AbstractChannelSignal;
import org.texgen.textures.ChannelSignal;

/**
 * Translates the u,v values by the offset amounts and returns a value from the
 * input source using the newly translated u,v values.
 * 
 * @author Andy Gibson
 */
public class SignalUVTranslate extends AbstractChannelSignal {

	private final ChannelSignal source;
	private final double uOffset;
	private final double vOffset;

	public SignalUVTranslate() {
		this(null, 0, 0);
	}

	public SignalUVTranslate(ChannelSignal source, double uOffset,
			double vOffset) {
		this.source = source;
		this.uOffset = uOffset;
		this.vOffset = vOffset;
	}

	public double getValue(double u, double v) {
		return calculateValue(u + uOffset, v + vOffset, source);

	}

	public ChannelSignal getSource() {
		return source;
	}

	public double getUOffset() {
		return uOffset;
	}
	public double getVOffset() {
		return vOffset;
	}
}
