/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.uv;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Takes the input U,V values, scales them and fetches the result from the 
 * source signal
 * 
 * @author Andy Gibson
 */
public class UVTranslate extends AbstractTexture {

    private final double uOffset;
    private final double vOffset;
    private final Texture source;

    public UVTranslate(Texture source) {
        this(source, 0, 0);
    }

    public UVTranslate(Texture source, double uOffset, double vOffset) {
        this.uOffset = uOffset;
        this.vOffset = vOffset;
        this.source = source;
    }

    public void getColor(double u, double v, RGBAColor value) {
        calculateColorFromTexture(u - uOffset, v - vOffset, source, value);
    }
}
