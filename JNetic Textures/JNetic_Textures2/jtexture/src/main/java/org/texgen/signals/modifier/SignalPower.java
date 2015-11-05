/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.signals.modifier;

import org.texgen.textures.ChainedChannelSignal;
import org.texgen.textures.ChannelSignal;

/**
 * Takes an input signal, and returns the value raised to the power of
 * <code>power</code> which is passed in to the constructor
 * 
 * @author Andy Gibson
 */
public class SignalPower extends ChainedChannelSignal {

	private double power;

	/**
	 * @param source
	 *            The source signal to get the original value from
	 * @param power
	 */
	public SignalPower(ChannelSignal source, double power) {
		super(source);
		this.power = power;
	}


	public double getValue(double u, double v) {
		double val = calculateValue(u, v, getSource());
		return Math.pow(val, power);
	}
}
