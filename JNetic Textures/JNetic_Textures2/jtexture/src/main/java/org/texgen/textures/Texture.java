/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures;

import org.texgen.utils.*;

/**
 *
 * Texture interface used to calculate the texture color based on the input u,v
 * values
 * 
 * @author Andy Gibson
 */
public interface Texture {

    /**
     * Returns the color for co-ordinates u,v as a new RGBAColor object
     * 
     * @param u
     *  U component used to generate the texture
     * @param v
     *   V Component used to generate the texture
     */
    RGBAColor getColor(double u, double v);

    /**
     * Calculates the color for co-ordinates u,v and puts the result in the 
     * RGBAColor value object passed in.
     * 
     * @param u
     *  U component used to generate the texture
     * @param v
     *   V Component used to generate the texture
     * @param value
     *   RGBAColor object used to receive the result
     */
    void getColor(double u, double v, RGBAColor value);
}
