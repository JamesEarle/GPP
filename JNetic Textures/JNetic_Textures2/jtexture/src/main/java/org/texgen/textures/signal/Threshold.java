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
import org.texgen.textures.AbstractTexture;
import org.texgen.textures.ChannelSignal;
import org.texgen.utils.RGBAColor;

/**
 * Switches the output based on the input from the source signal in comparison 
 * to the threshold level. 
 * <pre>
 * result = signal &lt;= level ? sourceA : sourceB;
 * </pre>
 * 
 * @author Andy Gibson
 */
public class Threshold extends AbstractTexture {

    private final Texture sourceA;
    private final Texture sourceB;
    private final ChannelSignal signal;
    private final double level;

    public Threshold(Texture sourceA, Texture sourceB, ChannelSignal signal) {
        this(sourceA, sourceB, signal, 0.5);
    }

    public Threshold(Texture sourceA, Texture sourceB, ChannelSignal signal, double level) {
        this.sourceA = sourceA;
        this.sourceB = sourceB;
        this.signal = signal;
        this.level = level;
    }

    public void getColor(double u, double v, RGBAColor value) {
        double signalValue = calculateSignalFromSignal(u, v, signal, 0);

        if (signalValue > level) {
            calculateColorFromTexture(u, v, sourceB, value);

        } else {
            calculateColorFromTexture(u, v, sourceA, value);
        }
    }

    public Texture getSourceA() {
        return sourceA;
    }

    public Texture getSourceB() {
        return sourceB;
    }

    public ChannelSignal getSignal() {
        return signal;
    }

    public double getLevel() {
        return level;
    }
}
