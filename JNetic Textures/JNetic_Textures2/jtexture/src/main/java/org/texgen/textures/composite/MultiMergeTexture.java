/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.composite;

import java.util.ArrayList;
import java.util.List;
import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Takes a list of textures and merges them down to one color based on 
 * transparency and the color of the textures.
 * 
 * @author Andy Gibson
 */
public class MultiMergeTexture extends AbstractTexture {

    private final List<Texture> textures = new ArrayList<Texture>();

    public void getColor(double u, double v, RGBAColor value) {
        
        value.setColor(0,0,0,0);
        RGBAColor temp = new RGBAColor();
        
        for (Texture texture : getTextures()) {
            calculateColorFromTexture(u, v, texture, temp);                        
            value.merge(temp);
        }        
        value.clamp();
        
    }

    public List<Texture> getTextures() {
        return textures;
    }
}
