/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.color;

import org.texgen.textures.AbstractTexture;
import org.texgen.utils.RGBAColor;

/**
 *
 * Generates a red/green set of squares 10 X 10 on the surface
 * Used mostly for checking UV disturbance functions
 * 
 * @author Andy Gibson
 */
public class Bars extends AbstractTexture {

    public void getColor(double u, double v, RGBAColor value) {
        u = u * 10;
        v = v * 10;
        int iu = (int) u;
        int iv = (int) v;
        double c1 = noise.fbmNoise2(iu, iv, 10);
        double c2 = noise.fbmNoise2(iu + 839, iv + 743, 10);
        value.setColor(c1, c2, 0.0, 1);

    }
}
