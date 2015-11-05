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
 * Abstract texture that contains a ColorGradient member. This is the base class for 
 * the different types of textures that require a color gradient.
 * 
 * @author Andy Gibson
 */
public abstract class AbstractGradient extends AbstractTexture {

    private final ColorGradient gradient;
   

    /**
     * 
     * @param gradient
     * Gradient to be used in this texture
     */
    public AbstractGradient(ColorGradient gradient) {
        this.gradient = gradient;
    }

    /**
     * @return the gradient assigned to this texture
     */
    public ColorGradient getGradient() {
        return gradient;
    }

}
