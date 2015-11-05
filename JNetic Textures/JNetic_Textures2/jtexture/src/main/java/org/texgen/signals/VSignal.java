/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.signals;

import org.texgen.textures.AbstractChannelSignal;

/**
 * Channel signal that returns the v value
 * 
 * @author Andy Gibson
 */
public class VSignal extends AbstractChannelSignal {

	public double getValue(double u, double v) {
		return v;

	}
}
