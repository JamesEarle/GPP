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
import org.texgen.textures.composite.MergedTexture;
import org.texgen.utils.RGBAColor;

/**
 * Composite texture that uses the fireball to build a fireball with a planet
 * eclipsing it.
 *  
 * @author Andy Gibson
 */
public class SunEclipse extends AbstractTexture {

    private final Texture texture;

    public SunEclipse() {
        Texture planet = buildPlanet();
        Texture fireball = new Fireball();
        texture = new MergedTexture(fireball, planet);
    }

    public void getColor(double u, double v, RGBAColor value) {
        texture.getColor(u, v, value);
    }

    private Texture buildPlanet() {

        //simple radial  gradient with black and transparent

        return new AbstractTexture() {

            public void getColor(double u, double v, RGBAColor value) {
                u = u - 0.5;
                v = v - 0.5;
                double dist = Math.sqrt((u * u) + (v * v));
                dist = dist * 50;
                if (dist < 10) {
                    value.setColor(0, 0, 0, 1);
                } else {
                    if (dist > 10.5) {
                        value.setColor(0, 0, 0, 0);
                    } else {
                        //in between                        
                        double fd = dist - (int) dist;
                        fd = fd * 2;

                        value.setColor(0, 0, 0, 1 - fd);
                    }
                }
            }
        };

    }
}
