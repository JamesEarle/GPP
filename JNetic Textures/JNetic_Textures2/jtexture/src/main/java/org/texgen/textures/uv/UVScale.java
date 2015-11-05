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
import org.texgen.textures.color.SolidTexture;
import org.texgen.utils.RGBAColor;

/**
 * Takes the input U,V values, scales them and fetches the result from the 
 * source signal
 * 
 * @author Andy Gibson
 */
public class UVScale extends AbstractTexture {

    private final double uScale;
    private final double vScale;
    private final Texture source;

    public UVScale() {
        this(SolidTexture.blackBackground());
    }
    
    public UVScale(Texture source) {
        this(source, 1, 1);
    }

    public UVScale(Texture source, double uScale, double vScale) {
        this.uScale = uScale;
        this.vScale = vScale;
        this.source = source;
    }

    public void getColor(double u, double v, RGBAColor value) {
        calculateColorFromTexture(u * getUScale(), v * getVScale(),getSource(), value);
    }

    public double getUScale() {
        return uScale;
    }

    public double getVScale() {
        return vScale;
    }

    public Texture getSource() {
        return source;
    }
}
