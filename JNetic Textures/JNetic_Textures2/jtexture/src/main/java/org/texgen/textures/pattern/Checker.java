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
 * Simple checker textures that toggles between two textures based on a 
 * checker pattern.
 * <br/>
 * Scale determines how many rows and columns the checker pattern has
 * 
 * @author Andy Gibson
 */
public class Checker extends AbstractTexture {

    private final double scale;
    private final Texture sourceA;
    private final Texture sourceB;

    public Checker() {
        this(new RGBAColor(0,0,0,1),new RGBAColor(255,255,255,1));
    }
    public Checker(RGBAColor colorA, RGBAColor colorB) {
        this(new SolidTexture(colorA), new SolidTexture(colorB));
    }

    public Checker(Texture sourceA, Texture sourceB) {
        this(sourceA, sourceB, 4);
    }

    public Checker(Texture sourceA, Texture sourceB, double scale) {
        this.scale = scale;
        this.sourceA = sourceA;
        this.sourceB = sourceB;
    }

    public double getScale() {
        return scale;
    }

    public void getColor(double u, double v,RGBAColor value) {

        u = normalize(u);
        v = normalize(v);
        
        double modu = u * scale;
        double modv = v * scale;

        int iu = (int) modu;
        int iv = (int) modv;
        double fu = modu - iu;
        double fv = modv - iv;

        Texture texture = null;

        if (fu < 0.5) {
            if (fv < 0.5) {
                texture = sourceA;
            } else {
                texture = sourceB;
            }
        } else {
            if (fv < 0.5) {
                texture = sourceB;
            } else {
                texture = sourceA;
            }
        }

        calculateColorFromTexture(u, v, texture,value);

    }

    public Texture getSourceA() {
        return sourceA;
    }

    public Texture getSourceB() {
        return sourceB;
    }
}
