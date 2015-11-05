/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.pattern;

import org.texgen.textures.color.SolidTexture;
import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 *
 * Simple brick texture that uses 2 textures for the brick and mortar elements.
 * 
 * @author Andy Gibson
 */
public class BrickTexture extends AbstractTexture {

    private final Texture brick;
    private final Texture mortar;
    private final double scale;

    public BrickTexture() {
        this(new SolidTexture(new RGBAColor(128, 32, 16)), new SolidTexture(new RGBAColor(200, 200, 200)));
    }

    public BrickTexture(Texture brick, Texture mortar) {
        this(brick, mortar, 8);
    }

    public BrickTexture(Texture brick, Texture mortar, double scale) {
        this.brick = brick;
        this.mortar = mortar;
        this.scale = scale;
    }

    public Texture getBrick() {
        return brick;
    }

    public Texture getMortar() {
        return mortar;
    }

    public double getScale() {
        return scale;
    }

    public void getColor(double u, double v, RGBAColor value) {

        u = normalize(u);
        v = normalize(v);

        double modu = Math.abs(u * scale);
        double modv = Math.abs(v * scale);

        int iv = (int) modv;
        double fv = modv - iv;


        //if bottom half, then move brick on
        if (fv > 0.5) {
            modu = modu + 0.5;
            fv = fv - 0.5;
        }
        int iu = (int) modu;
        double fu = modu - iu;

        if (iu < 0) {
            fu = 1 - fu;
        }


        Texture texture = brick;
        if (Math.abs(fu) > 0.9) {
            texture = mortar;
        } else {
            if (Math.abs(fv) > 0.4) {
                texture = mortar;
            }
        }

        calculateColorFromTexture(u, v, texture, value);

    }
}
