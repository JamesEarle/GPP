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
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Takes one or two signal sources and uses them to generate new U,V values 
 * based on the U,V signals. These UV values
 * are then used to obtain the texture value from the source Texture.
 *
 *@author Andy Gibson
 */
public class UVSignalTexture extends AbstractTexture {

    private final ChannelSignal uSignal;
    private final ChannelSignal vSignal;
    private final Texture source;

    public UVSignalTexture(Texture source, ChannelSignal signal) {
        this(source, signal, signal);
    }

    public UVSignalTexture(Texture source, ChannelSignal uSignal, ChannelSignal vSignal) {
        this.uSignal = uSignal;
        this.vSignal = vSignal;
        this.source = source;
    }

    public void getColor(double u, double v, RGBAColor value) {
        double nu = calculateSignalFromSignal(u,v,uSignal,u);
        double nv = calculateSignalFromSignal(u,v,vSignal,v);
        
        calculateColorFromTexture(nu, nv, source,value);
        
    }
}
