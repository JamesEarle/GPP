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
 * Takes the incoming signal and passes the value through after clamping it 
 * between  0 and 1
 * 
 * @author Andy Gibson
 */
public class ClampSignal extends ChainedChannelSignal {
    
	public ClampSignal(ChannelSignal source) {
		super(source);
	}
	
    public double getValue(double u, double v) {
        double val = calculateValue(u, v, getSource());
        
        if (val > 1) {
            return 1;
        }

        if (val < 0) {
            return 0;
        }

        return val;

    }

}
