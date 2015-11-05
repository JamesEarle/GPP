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
 * Returns the sum of the 2 input signal values for each u,v point
 * 
 * @author Andy Gibson
 */
public class SignalSum extends AbstractChannelSignal {

	private final ChannelSignal sourceA;
	private final ChannelSignal sourceB;
	private boolean clamped;

	public SignalSum(ChannelSignal sourceA, ChannelSignal sourceB,
			boolean clamped) {
		
		if (sourceA == null) {throw new IllegalArgumentException("Source A cannot be null");}
		if (sourceB == null) {throw new IllegalArgumentException("Source B cannot be null");}
		
		this.sourceA = sourceA;
		this.sourceB = sourceB;
		this.clamped = clamped;
	}

	public double getValue(double u, double v) {
		double val = calculateValue(u, v, sourceA);
		val = val + calculateValue(u, v, sourceB);
		if (clamped) {
			if (val < 0) {
				return 0;
			}
			if (val > 1) {
				return 1;
			}
		}
		return val;
	}

	public ChannelSignal getSourceA() {
		return sourceA;
	}

	public ChannelSignal getSourceB() {
		return sourceB;
	}
}
