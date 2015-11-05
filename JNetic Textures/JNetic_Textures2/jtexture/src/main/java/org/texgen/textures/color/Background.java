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
import org.texgen.textures.composite.MergedTexture;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Works as a general colored background texture onto which a texture is merged. 
 * The textures are merged based on the overlay alpha value. The default 
 * background color is white. 
 * <pre>
 * alpha = overlay.alpha;
 * result = (background * (1-alpha)) + (overlay * alpha)
 * </pre>
 * 
 * @author Andy Gibson
 * 
 */
public class Background extends AbstractTexture {

    private final MergedTexture mixer;

    /**
     * Constructs a Background with a texture and a background color
     * 
     * @param texture
     * Texture to mix with the background
     */
    public Background(Texture texture) {
        this(texture, new RGBAColor(255, 255, 255, 1));
    }

    /**
     * Constructs a Background with a texture and a background color
     * @param texture
     * Texture to mix over the background
     * @param color
     * Color of the background
     */
    public Background(Texture texture, RGBAColor color) {
        mixer = new MergedTexture(color, texture);
    }

    public void getColor(double u, double v, RGBAColor value) {
     
        mixer.getColor(u, v, value);
    }

    public Texture getSource() {
        return mixer.getOverlay();
    }
}
