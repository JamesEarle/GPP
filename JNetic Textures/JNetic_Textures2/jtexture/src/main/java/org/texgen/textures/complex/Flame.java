/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.complex;

import org.texgen.signals.MarbleSignal;
import org.texgen.textures.signal.GradientSignalTexture;
import org.texgen.utils.ColorGradient;
import org.texgen.utils.RGBAColor;

/**
 * Creates a firey marble type texture. Comes with 3 different gradients to use.
 * 
 * @author Andy Gibson
 */
public class Flame extends GradientSignalTexture {

	public static final ColorGradient redFlame = new ColorGradient().add(RGBAColor.black()).add(new RGBAColor(255, 64, 64)).add(new RGBAColor(255, 192, 64)).add(new RGBAColor(255, 240, 192)).add(RGBAColor.white());
	public static final ColorGradient greenFlame = new ColorGradient().add(RGBAColor.black()).add(new RGBAColor(64, 255, 64)).add(new RGBAColor(128, 255, 128)).add(new RGBAColor(200, 255, 200)).add(RGBAColor.white());
	public static final ColorGradient blueFlame = new ColorGradient().add(RGBAColor.black()).add(new RGBAColor(64, 64, 255)).add(new RGBAColor(128, 128, 255)).add(new RGBAColor(200, 200, 255)).add(RGBAColor.white());

    public Flame() {
        super(new MarbleSignal(), redFlame);
    }

    /**
     * 
     * @param gradient
     *   Color gradient to use (defaults to Flame.redFlame)
     * 
     * @param uOffset
     *   offset for the u components (default to 0)
     * 
     * @param vOffset
     * offset for the v component (defualts to 0)
     * @param speed
     *   How fast the texture moves acrosst the surface (default is 3)
     * @param octaves
     * Number of octaves used to generate the marble effect (default is 7)
     * @param size
     * Size of the marble effect (default 1.2)  
     * 
     */
    public Flame(ColorGradient gradient, double uOffset, double vOffset,double speed, int octaves, double size) {
        super(new MarbleSignal(uOffset,vOffset,speed,1,octaves,size),gradient);
        
        

    }

    public Flame(ColorGradient gradient) {
        this(gradient,0,0,4,10,1);
    }
}
