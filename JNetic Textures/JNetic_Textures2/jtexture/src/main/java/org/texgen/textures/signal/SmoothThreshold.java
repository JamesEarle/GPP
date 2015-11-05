/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.signal;

import org.texgen.textures.Texture;
import org.texgen.textures.ChannelSignal;
import org.texgen.utils.Gradient;
import org.texgen.utils.RGBAColor;

/**
 * Returns either sourceA or sourceB depending on the alpha value in the signal 
 * source. 
 * <pre>
 * 0..level-(size/2) = sourceA
 * level-(size/2) .. level+(size/2) = sourceA smoothly interpolated with sourceB
 * level+(size/2) = sourceB
 * </pre>
 * 
 * @author Andy Gibson
 */
public class SmoothThreshold extends Threshold {

    private final double size;

    public SmoothThreshold(Texture sourceA, Texture sourceB, ChannelSignal signal) {
        this(sourceA, sourceB, signal, 0.5, 0.1);
    }

    public SmoothThreshold(Texture sourceA, Texture sourceB, ChannelSignal signal,double level) {
        this(sourceA, sourceB, signal, level, 0.1);
    }
    

    public SmoothThreshold(Texture sourceA, Texture sourceB, ChannelSignal signal, double level, double size) {
        super(sourceA, sourceB, signal, level);
        this.size = size;
    }

    @Override
    public RGBAColor getColor(double u, double v) {
        double signal = calculateSignalFromSignal(u, v, getSignal());
        RGBAColor colA = calculateColorFromTexture(u, v, getSourceA());
        RGBAColor colB = calculateColorFromTexture(u, v, getSourceB());

        double dSize = size / 2;
        double mix = Gradient.smoothStep(signal, getLevel() - dSize, getLevel() + dSize);

        colB.setAlpha(mix);
        colA.merge(colB);
        return colA;

    }

    public double getSize() {
        return size;
    }

}
