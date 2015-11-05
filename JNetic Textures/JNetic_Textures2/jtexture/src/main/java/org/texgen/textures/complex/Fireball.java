/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.complex;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.textures.color.RadialGradient;
import org.texgen.textures.uv.UVNoiseTranslate;
import org.texgen.utils.ColorGradient;
import org.texgen.utils.RGBAColor;

/**
 * Takes a gradient and centers it, and moves through the gradient as u,v moves
 * away from the center of the texture.
 * 
 * @author Andy Gibson
 */
public class Fireball extends AbstractTexture {

	private final RadialGradient gradientTexture;
    private final Texture texture;
       
	public Fireball() {
        this(buildFireGradient(), 14, 6, 0.2);
    }

    protected static ColorGradient buildFireGradient() {
        ColorGradient gradient = ColorGradient.buildFire();
       
        gradient.add(RGBAColor.black());
        gradient.add(RGBAColor.black());
        gradient.add(RGBAColor.black());
        gradient.add(RGBAColor.black());

        return gradient;

    }

    public Fireball(double speed, int octaves, double size) {
        this(buildFireGradient(), speed, octaves, size);
    }

    public Fireball(ColorGradient gradient, double speed, int octaves, double size) {
    	gradientTexture = new RadialGradient(gradient);    
        texture = new UVNoiseTranslate(gradientTexture,14,5,0.2);
    }

    public void getColor(double u, double v, RGBAColor value) {
        texture.getColor(u, v,value);       
    }
}
