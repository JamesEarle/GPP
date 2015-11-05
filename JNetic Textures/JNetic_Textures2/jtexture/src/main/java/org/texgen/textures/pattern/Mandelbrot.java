/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.pattern;

import org.texgen.signals.MandelbrotSignal;
import org.texgen.textures.AbstractTexture;
import org.texgen.utils.ColorGradient;
import org.texgen.utils.RGBAColor;

/**
 *
 * Creates a mandelbrot texture with a gradient coloring. Uses the default 
 * parameters of<br/>
 * StartX, StartY = -2.025,-1.125<br/>
 * EndX, EndY = 0.6, 1.125 
 * @author Andy Gibson
 */
public class Mandelbrot extends AbstractTexture {

    private final ColorGradient gradient;
    private final MandelbrotSignal signal;

    public Mandelbrot() {
        this(ColorGradient.buildSpectrum(), -2.025, -1.125, 0.6, 1.125);
    }

    public Mandelbrot(double startX, double startY, double endX, double endY) {
        this(ColorGradient.buildSpectrum(), startX, startY, endX, endY);

    }

    public Mandelbrot(ColorGradient gradient, double startX, double startY, double endX, double endY) {
        signal = new MandelbrotSignal(startX,startY,endX,endY);
        this.gradient = gradient;
    }

    public void getColor(double u, double v, RGBAColor value) {
        double val = calculateSignalFromSignal(u, v, signal);
        gradient.interpolate(val,value);
        
    }

    public ColorGradient getGradient() {
        return gradient;
    }

}

