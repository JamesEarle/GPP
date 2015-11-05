/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.color;

import org.texgen.signals.LightSignal;
import org.texgen.textures.AbstractTexture;
import org.texgen.textures.ChannelSignal;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * A light-like texture that shades points based on how far they are from the 
 * position of the light in terms of u,v co-ordinates. 
 * 
 * @author Andy Gibson
 */
public class LightTexture extends AbstractTexture {

    private final Texture lightTexture;
    private final ChannelSignal lightSignal;

    public LightTexture(double originX, double originY) {
        this(RGBAColor.white(), originX, originY, 10);
    }
    
    public LightTexture(double originX, double originY,double strength) {
        this(RGBAColor.white(), originX, originY, strength);
    }
    

    public LightTexture(RGBAColor color, double originX, double originY, double strength) {
        this(new SolidTexture(color), originX, originY, strength);
    }

    public LightTexture(Texture lightTexture, double originX, double originY, double strength) {
        this.lightTexture = lightTexture;
        lightSignal = new LightSignal(originX, originY, strength);        
    }

    public void getColor(double u, double v, RGBAColor value) {
        double signal = calculateSignalFromSignal(u, v,getLightSignal());
        calculateColorFromTexture(u, v,getLightTexture(), value);
        value.setAlpha(signal);
        
    }

    public Texture getLightTexture() {
        return lightTexture;
    }

    public ChannelSignal getLightSignal() {
        return lightSignal;
    }

}
