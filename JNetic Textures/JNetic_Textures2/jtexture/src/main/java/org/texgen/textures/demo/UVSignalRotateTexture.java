/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.demo;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.ChannelSignal;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Demo texture indicating how we could use a signal source to drive the rotation
 * value for a texture.
 * 
 * @author Andy Gibson
 */
public class UVSignalRotateTexture extends AbstractTexture {
        
    private final ChannelSignal signal;
    private final Texture source;

    public UVSignalRotateTexture(Texture source, ChannelSignal signal) {                
        this.signal = signal;
        this.source = source;
    }

    public void getColor(double u, double v, RGBAColor value) {
        double radAngle = calculateSignalFromSignal(u, v, signal);
        double ru = Math.cos(radAngle) * u - Math.sin(radAngle) * v;
        double rv = Math.sin(radAngle) * u + Math.cos(radAngle) * v;
        calculateColorFromTexture(ru, rv, source,value);
    }
}
