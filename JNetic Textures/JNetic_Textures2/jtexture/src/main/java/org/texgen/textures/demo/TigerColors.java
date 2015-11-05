/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.demo;

import org.texgen.signals.AnimalStripe;
import org.texgen.signals.modifier.NoisySignal;
import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.textures.color.HorizontalGradient;
import org.texgen.textures.composite.MergedTexture;
import org.texgen.textures.signal.AlphaSignal;
import org.texgen.textures.uv.UVNoiseTranslate;
import org.texgen.utils.ColorGradient;
import org.texgen.utils.RGBAColor;

/**
 * Combines fur colors and a set of stripes to produce a tiger skin texture
 * 
 * @author Andy Gibson
 */
public class TigerColors extends AbstractTexture {

    private final ColorGradient baseFurGradient = new ColorGradient();    
    private final Texture mixer;
    

    public TigerColors() {
        
        baseFurGradient.add(new RGBAColor(201,203,202));
        baseFurGradient.add(new RGBAColor(171,118,40));
        baseFurGradient.add(new RGBAColor(108,53,0));
        baseFurGradient.add(new RGBAColor(173,103,43));
        baseFurGradient.add(new RGBAColor(200,205,198));        
        
        Texture temp = new AlphaSignal(new RGBAColor(0,0,0,0.5),new NoisySignal(new AnimalStripe(),15,3,0.7));
        
        
        
        Texture background = new HorizontalGradient(baseFurGradient);
        background = new UVNoiseTranslate(background,10,4,0.2);
        
        
        mixer = new MergedTexture(background, temp);
    }

    public void getColor(double u, double v, RGBAColor value) {
        mixer.getColor(u, v,value);
    }
}
