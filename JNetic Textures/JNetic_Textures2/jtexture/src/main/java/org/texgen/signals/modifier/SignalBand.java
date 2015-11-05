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
 * Takes a source signal and divides it into bands as opposed to a set of smooth 
 * values. The returned value is the closest band based on the input.
 * 
 * @author Andy Gibson
 */
public class SignalBand extends ChainedChannelSignal {

    private final int bandCount;    

    public SignalBand(ChannelSignal source) {    	
        this(source, 10);
    }
    

    public SignalBand(ChannelSignal source, int bandCount) {
    	super(source);
        this.bandCount = bandCount;
    }

    public double getValue(double u, double v) {
        double val = calculateValue(u, v, getSource());
        if (bandCount != 0) {
            val = val * bandCount;
            val = (int) val;
            val = val / bandCount;
        }
        return val;


    }

	public int getBandCount() {
		return bandCount;
	}
}
