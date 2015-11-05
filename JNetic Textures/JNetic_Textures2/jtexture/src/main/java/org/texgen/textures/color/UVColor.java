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
import org.texgen.utils.GraphUtils;
import org.texgen.utils.RGBAColor;

/**
 *
 * Maps u,v values from 0..1 to red and green components in the final texture
 * Used to test UV transformations
 * 
 * @author Andy Gibson
 */
public class UVColor extends AbstractTexture {

    public void getColor(double u, double v, RGBAColor value) {
        u = GraphUtils.clamp(u);
        v = GraphUtils.clamp(v);
        value.setColor(u,v,0,1);
    }

}
