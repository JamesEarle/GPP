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
 * Takes 2 input signals, calculates the u,v value and returns the 2 u,v, values
 * multiplied together
 * 
 * @author Andy Gibson
 */
public class SignalMultiply extends AbstractChannelSignal {

	private final ChannelSignal sourceA;
	private final ChannelSignal sourceB;

	public SignalMultiply(ChannelSignal sourceA, ChannelSignal sourceB) {
		if (sourceA == null) {throw new IllegalArgumentException("Source A cannot be null");}
		if (sourceB == null) {throw new IllegalArgumentException("Source B cannot be null");}
		this.sourceA = sourceA;
		this.sourceB = sourceB;
	}

	public double getValue(double u, double v) {
		double v2 = calculateValue(u, v, sourceA);
		double v1 = calculateValue(u, v, sourceB);		
		return v1 * v2;
	}
	
	public ChannelSignal getSourceA() {
		return sourceA;
	}
	
	public ChannelSignal getSourceB() {
		return sourceB;
	}
}
