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
 * Returns a fixed color based on the color passed in to the constructor
 *
 * @author Andy Gibson
 */
public class SolidTexture extends AbstractTexture {

    private final RGBAColor color;

    /**
     * Helper function to construct a black background
     * @return a Solid black texture 
     */
    public static SolidTexture blackBackground() {
        return new SolidTexture(new RGBAColor(0, 0, 0, 1));
    }

    /**
     * Helper function to construct a white background
     * @return a Solid white texture 
     */
    public static SolidTexture whiteBackground() {
        return new SolidTexture(new RGBAColor(255, 255, 255, 1));
    }

    /**
     * Constructs a solid texture with black as the color
     */
    public SolidTexture() {
        this(new RGBAColor(0, 0, 0, 1));
    }

    /**
     * Constructs a solid texture of the specified color
     * 
     * @param color
     * Color to use for this texture
     */
    public SolidTexture(RGBAColor color) {
        this.color = new RGBAColor(color);
    }

    public void getColor(double u, double v, RGBAColor value) {
        value.setColor(color);
    }

    public RGBAColor getColor() {
        return color;
    }
}
