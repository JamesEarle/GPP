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
import org.texgen.utils.ColorGradient;
import org.texgen.utils.GraphUtils;
import org.texgen.utils.RGBAColor;

/**
 *
 * Texture to create a 2D map based on noise
 * 
 * @author Andy Gibson
 */
public class Map extends AbstractTexture {

    private final ColorGradient gradient;
    private final double scale;
    private final int octaves;
    private final double seaLevel;
    private final RGBAColor seaColor;

    public Map() {
        this(ColorGradient.buildMapGradient(), buildDefaultSeaColor());
    }

    public Map(ColorGradient gradient, RGBAColor seaColor, double scale, int octaves, double seaLevel) {
        this.gradient = gradient;
        this.seaColor = new RGBAColor(seaColor);
        this.scale = scale;
        this.octaves = octaves;
        this.seaLevel = seaLevel;
    }

    public Map(double scale,int octaves,double seaLevel) {
        this(ColorGradient.buildMapGradient(),buildDefaultSeaColor(),scale,octaves,seaLevel);
        
    }
    public Map(ColorGradient gradient,RGBAColor seaColor) {
        this(gradient, seaColor,5, 8, 0.33);
    }

    protected static RGBAColor buildDefaultSeaColor() {
        return new RGBAColor(70, 113, 180);
    }

    public void getColor(double u, double v, RGBAColor value) {
            double height = noise.fbmNoise2(u * scale, v * scale, octaves);
        height = height * 1.65;
        height = height - 0.25;
        height = GraphUtils.clamp(height);
                
        if (height < seaLevel) {
            value.setColor(seaColor);
        } else {
            value.setColor(gradient.interpolate(height));
        }
                

    }
}
