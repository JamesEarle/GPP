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
 * Scales the u,v values by a fixed amount and returns a value based on the 
 * input source and the scales u,v values.
 * 
 * @author Andy Gibson
 */
public class SignalUVScale extends AbstractChannelSignal {

    private final ChannelSignal source;
    private final double uScale;
    private final double vScale;

    public SignalUVScale(ChannelSignal source, double uScale, double vScale) {
        this.source = source;
        this.uScale = uScale;
        this.vScale = vScale;
    }

    
    public SignalUVScale(ChannelSignal source, double scale) {
        this(source,scale,scale);
    }

    public double getValue(double u, double v) {
        return calculateValue(u * uScale, v * vScale, source);

    }

	public ChannelSignal getSource() {
		return source;
	}

	public double getUScale() {
		return uScale;
	}

	public double getVScale() {
		return vScale;
	}

}
