/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.signal;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.ChannelSignal;
import org.texgen.utils.ColorGradient;
import org.texgen.utils.RGBAColor;

/**
 * Takes a gradient and a signal, and uses the signal to determine where we 
 * interpolate the gradient.
 * 
 * @author Andy Gibson
 */
public class GradientSignalTexture extends AbstractTexture {

    private final ChannelSignal signal;
    private final ColorGradient gradient;

    public GradientSignalTexture() {
        this(null,null);
    }

    
    public GradientSignalTexture(ChannelSignal signal, ColorGradient gradient) {
        this.signal = signal;
        this.gradient = gradient;
    }

    public void getColor(double u, double v, RGBAColor value) {
        double signalValue = calculateSignalFromSignal(u, v,getSignal());
        if (getGradient() != null) {
            getGradient().interpolate(signalValue,value);
        }
    }

    public ChannelSignal getSignal() {
        return signal;
    }

    public ColorGradient getGradient() {
        return gradient;
    }
}
