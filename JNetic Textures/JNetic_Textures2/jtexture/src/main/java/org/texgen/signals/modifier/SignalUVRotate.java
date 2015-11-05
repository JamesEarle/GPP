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
 * Rotates the u,v coordinates around the 0,0 axis and calcualtes the value from 
 * the source signal.
 * 
 * @author Andy Gibson
 */
public class SignalUVRotate extends AbstractChannelSignal {

    private final ChannelSignal source;
    private final double angle;

    public SignalUVRotate(ChannelSignal source, double angle) {
        this.source = source;
        this.angle = angle;
    }

    public double getValue(double u, double v) {
        double radAngle = Math.toRadians(angle);
        double ru = Math.cos(radAngle) * u - Math.sin(radAngle) * v;
        double rv = Math.sin(radAngle) * u + Math.cos(radAngle) * v;
        return calculateValue(ru, rv, source);
    }

    public ChannelSignal getSource() {
        return source;
    }

    public double getAngle() {
        return angle;
    }
}
