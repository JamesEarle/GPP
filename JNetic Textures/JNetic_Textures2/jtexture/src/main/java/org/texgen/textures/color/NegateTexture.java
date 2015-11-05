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
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 *
 *  Returns the negative value of the input color.
 * 
 * @author Andy Gibson
 */
public class NegateTexture extends AbstractTexture {

    private final Texture source;

    /**
     * Constructs a texture that negates the input color.
     * @param source
     * Determines the texture source that will be negated
     */
    public NegateTexture(Texture source) {
        this.source = source;
    }

    
    public void getColor(double u, double v,RGBAColor value) {
        calculateColorFromTexture(u, v, source, value);
        value.negate();        
    }

    public Texture getSource() {
        return source;
    }

}
