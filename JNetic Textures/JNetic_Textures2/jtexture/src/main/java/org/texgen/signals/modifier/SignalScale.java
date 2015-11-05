/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.signals.modifier;

import org.texgen.signals.ConstantSignal;
import org.texgen.textures.ChannelSignal;

/**
 *  Takes an input source signal and scales it by a fixed value
 * 
 * @author Andy Gibson
 */
public class SignalScale extends SignalMultiply {

    public SignalScale(ChannelSignal source, double scale) {
    	super(source,new ConstantSignal(scale));
    }

}
