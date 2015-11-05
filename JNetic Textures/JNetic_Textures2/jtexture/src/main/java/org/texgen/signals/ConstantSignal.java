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
 * Returns a constant value as a signal.
 *  
 * @author Andy Gibson
 */
public class ConstantSignal extends AbstractChannelSignal {

    private final double value;

    public ConstantSignal() {
        this(0.5);
    }
    
    public ConstantSignal(double value) {
        this.value = value;
    }
    
    public double getValue(double u, double v) {
        return value;
    }

    public double getValue() {
        return value;
    }

}
