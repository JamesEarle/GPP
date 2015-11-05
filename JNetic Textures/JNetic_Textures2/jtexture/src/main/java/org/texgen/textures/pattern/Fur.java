/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.pattern;

import org.texgen.signals.NoiseSignal;
import org.texgen.signals.modifier.SignalUVRotate;
import org.texgen.signals.modifier.SignalUVScale;
import org.texgen.textures.AbstractTexture;
import org.texgen.textures.ChannelSignal;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 *
 * Fur is a texture which takes a source texture and gives it a furry texture.
 * @author Andy Gibson
 */
public class Fur extends AbstractTexture {

    private final Texture furColor;
    private final ChannelSignal noiseSignal;    
       

    public Fur(Texture furColor) {
        this.furColor = furColor;
        ChannelSignal temp = new NoiseSignal(1,4,0.5);
        noiseSignal = new SignalUVScale(temp, 14,128);        
       
        
    }

    public void getColor(double u, double v, RGBAColor value) {    
                
        double angle = noise.fbmNoise2(u*3, v*3, 3);
        
        SignalUVRotate rotationSignal = new SignalUVRotate(noiseSignal,angle * 10);
        
        double val = calculateSignalFromSignal(u, v, rotationSignal);
        //make it fall off quickly
        val = val * val;
        
        //get the fur color
        calculateColorFromTexture(u, v,getFurColor(), value);
        //invert the coefficient value
        value.scale(1-val);
        //set the alpha to 1, this shouldn't be scaled.
        value.setAlpha(1);        
    }

    public Texture getFurColor() {
        return furColor;
    }
}
