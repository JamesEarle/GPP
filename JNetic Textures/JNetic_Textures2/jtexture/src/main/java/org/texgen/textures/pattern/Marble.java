/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.pattern;

import org.texgen.textures.Texture;
import org.texgen.textures.color.SolidTexture;
import org.texgen.textures.AbstractTexture;
import org.texgen.utils.*;
import org.texgen.signals.MarbleSignal;

/**
 * Returns a single marble element that is mostly transparent. This should be 
 * used with other textures to produce an overall composite effect
 * 
 * Also see the ComplexMarble for an example.s
 * 
 * @author Andy Gibson
 */
public class Marble extends AbstractTexture {

    private final Texture source;
    private final MarbleSignal marbleSignal;

    public Marble() {
        this(RGBAColor.black());
    }

    /** Creates a new instance of Marble */
    public Marble(RGBAColor color) {
        this(new SolidTexture(color));
    }

    public Marble(RGBAColor color, double uOffset, double vOffset) {
        this(new SolidTexture(color), uOffset, vOffset);
    }

    public Marble(Texture texture) {
        this(texture, 0, 0, 4,8,8,1);
    }

    public Marble(Texture texture, double uOffset, double vOffset) {
        this(texture, uOffset, vOffset, 4,8,8,1);
    }

    public Marble(Texture texture, double uOffset, double vOffset, double sineScale, double speed, int octaves) {
        this(texture, uOffset, vOffset, sineScale, speed,octaves, 1);
    }

    public Marble(RGBAColor color, double uOffset, double vOffset, double sineScale, double speed,int octaves, double size) {
        this(new SolidTexture(color),uOffset,vOffset,sineScale,speed,octaves,size);
    }

    public Marble(Texture texture, double uOffset, double vOffset, double sineScale, double speed,int octaves, double size) {
        marbleSignal = new MarbleSignal(uOffset, vOffset, sineScale, speed, octaves, size);
        this.source = texture;
    }

    public double calculate(double u, double v) {
        return marbleSignal.getValue(u, v);
    //return noise.noisySine(u, v, speed, scale, octaves) * size;
    }

    public void getColor(double u, double v, RGBAColor value) {

        double coef = calculate(u, v);

        calculateColorFromTexture(u, v, source, value);

        value.scaleAlpha(coef);
    }

    public Texture getSource() {
        return source;
    }

}
