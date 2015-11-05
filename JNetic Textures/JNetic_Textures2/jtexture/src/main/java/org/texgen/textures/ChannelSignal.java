/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures;

/**
 * 
 * Channel Signal interface to calculate the double output signal based on the
 * input u,v, values.
 * 
 * @author Andy Gibson
 */
public interface ChannelSignal {

    /**
     * Channel enum used to define which channel a signal applies to if needed
     * in one of the textures.
     */
    public enum Channel {

        RED, GREEN, BLUE, ALPHA;
    }

    /**
     * 
     * @param u
     * u Component used to determine the output signal value
     * @param v
     * v Component used to determine the output signal value
     * @return
     * the value of the output signal
     */
    double getValue(double u, double v);
}
