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
 * Texture that allows you to scale, rotate and transform the source texture
 * 
 * @author Andy Gibson
 */
public class UVTransformer extends AbstractTexture {

    private final UVTranslate translate;
    private final UVRotate rotate;
    private final UVScale scale;

    public UVTransformer(Texture source, double x, double y) {
        this(source, x, y, 0, 0, 0);
    }
    
    public UVTransformer(Texture source, double x, double y, double uScale, double vScale) {
        this(source,x,y,uScale,vScale,0);
    }    

    public UVTransformer(Texture source, double x, double y, double uScale, double vScale, double angle) {
        Texture centerTranslate = new UVTranslate(source, -0.5, -0.5);
        rotate = new UVRotate(centerTranslate, angle);
        scale = new UVScale(rotate, uScale, vScale);
        Texture reCenterTranslate = new UVTranslate(scale, 0.5, 0.5);
        translate = new UVTranslate(reCenterTranslate, x, y);
    }

    public void getColor(double u, double v, RGBAColor value) {
        calculateColorFromTexture(u, v, translate, value);
    }
}
