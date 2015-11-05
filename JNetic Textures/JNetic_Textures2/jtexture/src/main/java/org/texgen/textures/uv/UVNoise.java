/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.uv;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * UVNoise takes u,v input and maps it to new u,v values based on a noise function.
 * It then uses the new U,V values and gets the value from the source texture.<br/>
 * <br/>
 *   Scale - Determines how fast the noise moves across the surface<br/>
 *   Octaves - Determine how many octaves of noise to use
 * 
 * @author Andy Gibson
 */
public class UVNoise extends AbstractTexture {

    private final double speed;    
    private final int octaves;    
    private final Texture source;

    public UVNoise(Texture source, double speed, int octaves) {
        this.source = source;
        this.speed = speed;        
        this.octaves = octaves;
    }

    public UVNoise(Texture source) {
        this(source,1, 8);
    }

    public void getColor(double u, double v,RGBAColor value) {
        double mu = noise.fbmNoise2(u * speed, v * speed, octaves);
        double mv = noise.fbmNoise2(73623 + (u * speed),7392+(v * speed), octaves);
        calculateColorFromTexture(mu, mv, source,value);
    }
}
