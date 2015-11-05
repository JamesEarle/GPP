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
 * Returns one signal or another depending on whether the input being evaluated 
 * is past a certain threshold value.
 * 
 * @author Andy Gibson
 */
public class SignalThreshold extends AbstractChannelSignal {

    private final ChannelSignal sourceA;
    private final ChannelSignal sourceB;
    private final ChannelSignal input;
    private final double threshold;

    /**
     * Uses a default threshold switch point of 0.5
     * 
     * @param sourceA
     * Signal for source A
     * @param sourceB
     * Signal for source B
     * @param input
     * input to evaluate to determine which source is used
     */
    public SignalThreshold(ChannelSignal sourceA, ChannelSignal sourceB, ChannelSignal input) {
        this(sourceA,sourceB,input,0.5);
    }

    /**
     * 
     * @param sourceA
     * Signal for source A
     * @param sourceB
     * Signal for source B
     * @param input
     * input to evaluate to determine which source is used
     * @param threshold
     * Threshold at which point we toggle which source we use.
     */
    public SignalThreshold(ChannelSignal sourceA, ChannelSignal sourceB, ChannelSignal input, double threshold) {
    	if (sourceA == null) {throw new IllegalArgumentException("Source A cannot be null");}
    	if (sourceB == null) {throw new IllegalArgumentException("Source B cannot be null");}
    	if (input == null) {throw new IllegalArgumentException("Input signal cannot be null");}
    	
        this.sourceA = sourceA;
        this.sourceB = sourceB;
        this.input = input;
        this.threshold = threshold;
    }

    public ChannelSignal getSourceA() {
        return sourceA;
    }

    public ChannelSignal getSourceB() {
        return sourceB;
    }
    public ChannelSignal getInput() {
        return input;
    }

    public double getThreshold() {
        return threshold;
    }
    
    public double getValue(double u, double v) {
        if (calculateValue(u, v, input) > threshold) {
            return calculateValue(u, v, sourceA);
        } else {
            return calculateValue(u, v, sourceB);
        }
    }
}
