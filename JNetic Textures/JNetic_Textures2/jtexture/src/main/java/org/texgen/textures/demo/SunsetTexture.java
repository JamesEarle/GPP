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
import org.texgen.utils.ColorGradient;
import org.texgen.utils.RGBAColor;

/**
 * Uses a sunset color gradient to produce a horizontal sunset gradient
 * 
 * @author Andy Gibson
 */
public class SunsetTexture extends AbstractTexture {

    private final ColorGradient gradient;

    public SunsetTexture() {
        gradient = new ColorGradient();
        gradient.add(new RGBAColor(16, 32, 64))
                .add(new RGBAColor(32, 64, 128))
                .add(new RGBAColor(128, 160, 255))                
                .add(new RGBAColor(192, 210, 255))                               
                .add(new RGBAColor(250, 240, 192));                
    }

    public void getColor(double u, double v, RGBAColor value) {
        gradient.interpolate(v, value);
    }
}
