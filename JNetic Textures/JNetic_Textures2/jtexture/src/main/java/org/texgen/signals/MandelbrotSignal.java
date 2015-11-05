/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.signals;

import org.texgen.textures.AbstractChannelSignal;
import org.texgen.utils.Gradient;

/**
 * Signal that returns values calculated from the Mandelbrot set of Fractals
 * 
 * @author Andy Gibson
 */
public class MandelbrotSignal extends AbstractChannelSignal {

    private static final int MAX = 256;
    private final double startX;
    private final double startY;
    private final double endX;
    private final double endY;

    public MandelbrotSignal() {
        this(-2.025, -1.125, 0.6, 1.125);
    }

    public MandelbrotSignal(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    private float calcValue(double xwert, double ywert) // color value from 0.0 to 1.0 by iterations
    {
        double r = 0.0, i = 0.0, m = 0.0;
        int j = 0;

        while ((j < MAX) && (m < 4.0)) {
            j++;
            m = r * r - i * i;
            i = 2.0 * r * i + ywert;
            r = m + xwert;
        }
        return (float) j / (float) MAX;
    }

    public double getValue(double u, double v) {
        double nu = Gradient.interpolate1(startX, endX, u);
        double nv = Gradient.interpolate1(startY, endY, v);
        
        return calcValue(nu,nv);
    }

	public double getStartX() {
		return startX;
	}

	public double getStartY() {
		return startY;
	}

	public double getEndX() {
		return endX;
	}

	public double getEndY() {
		return endY;
	}
}
