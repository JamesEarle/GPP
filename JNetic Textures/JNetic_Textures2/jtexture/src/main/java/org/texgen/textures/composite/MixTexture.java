/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.composite;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Takes two texture and mixes them based on a coefficient
 * 
 * @author Andy Gibson
 */
public class MixTexture extends AbstractTexture {

    private final Texture sourceA;
    private final Texture sourceB;
    private final RGBAColor temp = new RGBAColor();
    private final double level;

    public MixTexture(Texture sourceA, Texture sourceB) {
        this(sourceA, sourceB, 0.5);
    }

    public MixTexture(Texture sourceA, Texture sourceB, double level) {
        this.sourceA = sourceA;
        this.sourceB = sourceB;
        this.level = level;
    }

    public void getColor(double u, double v, RGBAColor value) {
        calculateColorFromTexture(u, v, sourceA, temp);
        calculateColorFromTexture(u, v, sourceB, value);        
        value.merge(temp,level);
    }

    public Texture getSourceA() {
        return sourceA;
    }

    public Texture getSourceB() {
        return sourceB;
    }
    public double getLevel() {
        return level;
    }
}
