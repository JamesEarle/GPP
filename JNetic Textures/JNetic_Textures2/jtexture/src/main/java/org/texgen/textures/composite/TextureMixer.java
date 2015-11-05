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
 * Takes a list of textures and mixes them equally<br/> 
 * <pre>
 * count = number of textures 
 * result = color(0,0,0,0);
 * 
 * for each texture  : result = result + texture.getColor(u,v)
 * result.scale(1/count);
 * </pre>
 * 
 * @author Andy Gibson
 */
public class TextureMixer extends AbstractTexture {

    private final List<Texture> textures = new ArrayList<Texture>();

    public void getColor(double u, double v, RGBAColor value) {
        RGBAColor col = new RGBAColor(0, 0, 0, 0);        
        value.setColor(0, 0, 0, 0);
        for (Texture elem : textures) {            
            calculateColorFromTexture(u, v, elem, col);            
            value.add(col);
        }

        if (textures.size() != 0) {
            value.scale(1.0 / textures.size());
        }
        value.clamp();
    }

    public List<Texture> getTextures() {
        return textures;
    }

    public TextureMixer add(Texture texture) {
        this.textures.add(texture);
        return this;
    }

}
