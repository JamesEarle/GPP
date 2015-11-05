/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.pattern;

import org.texgen.signals.RoundedCornerSignal;
import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.textures.color.SolidTexture;
import org.texgen.utils.RGBAColor;

/**
 * Creates a square with rounded corners (based on the radius) in the 0..1 space 
 * @author Andy Gibson
 */
public class RoundedCornerTexture extends AbstractTexture {

    private final Texture foreground;
    private final RoundedCornerSignal signal;

    public RoundedCornerTexture() {
        this(SolidTexture.whiteBackground(),0.1);

    }

    public RoundedCornerTexture(RGBAColor color) {
        this(new SolidTexture(color));
        
    }
    public RoundedCornerTexture(Texture foreground) {
        this(foreground, 0.1);
    }

    public RoundedCornerTexture(Texture foreground, double radius) {      
        this.foreground = foreground;
        signal = new RoundedCornerSignal(radius);
    }

    public Texture getForeground() {
        return foreground;
    }

    public void getColor(double u, double v, RGBAColor value) {
        calculateColorFromTexture(u, v, foreground,value);        
        double val = calculateSignalFromSignal(u, v, signal);
        val = val * value.getAlpha();
        value.setAlpha(val);

    }
}


