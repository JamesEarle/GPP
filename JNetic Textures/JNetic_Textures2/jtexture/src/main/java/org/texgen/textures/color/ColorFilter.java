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
 * Filters the source image by a color. Color can either be fixed or it can be 
 * based on another texture. 
 * 
 * @author Andy Gibson
 */
public class ColorFilter extends AbstractTexture {

    private final Texture source;
    private final Texture filter;

    /**
     * Constructs a filter for the source texture using a single color
     * 
     * @param source
     * The texture to filter
     * @param color
     * The color to filter it with
     */
    public ColorFilter(Texture source, RGBAColor color) {
        this(source, new SolidTexture(color));
    }

    /**
     * Constructs a filter for the source texture using another text for the 
     * filter color.
     * 
     * @param source
     * The texture to filter
     * @param filter
     * The texture to use as the color filter
     */
    public ColorFilter(Texture source, Texture filter) {
        this.source = source;
        this.filter = filter;
    }

    public void getColor(double u, double v, RGBAColor value) {
        calculateColorFromTexture(u, v, source,value);
        RGBAColor col = calculateColorFromTexture(u, v, filter);        
        value.mulitply(col);

    }

    public Texture getSource() {
        return source;
    }

    public Texture getFilter() {
        return filter;
    }
}
