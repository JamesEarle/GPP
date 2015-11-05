/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which contains a list of colors and methods to interpolate through the 
 * colors based on a value from 0 to 1
 * 
 * @author Andy Gibson
 */
public class ColorGradient {

    private final List<RGBAColor> colors = new ArrayList<RGBAColor>();

    /**
     * Helper method to return a pre-built spectrum gradient
     * 
     * @return
     * Color gradient containing spectrum colors
     */
    public static ColorGradient buildSpectrum() {
        ColorGradient result = new ColorGradient();
        result.add(new RGBAColor(255, 0, 0)).add(new RGBAColor(255, 128, 0)).add(new RGBAColor(255, 255, 0)).add(new RGBAColor(0, 255, 0)).add(new RGBAColor(0, 0, 255)).add(new RGBAColor(128, 0, 255)).add(new RGBAColor(0, 0, 0));
        return result;
    }

    /**
     * Helper method to return a pre-built gradient with fire colors.
     * 
     * @return
     *  Color gradient containing fire colors;
     */
    public static ColorGradient buildFire() {
        return new ColorGradient().add(RGBAColor.white()).add(new RGBAColor(255, 240, 192)).add(new RGBAColor(255, 192, 64)).add(new RGBAColor(255, 64, 64));
    }

    /**
     * Helper method to build a gradient containing map colors, from 
     * sea->beach->grass->snow
     * 
     * @return
     * Pre-built Color gradient with map colors.
     */
    public static ColorGradient buildMapGradient() {
        RGBAColor sea = new RGBAColor(70, 113, 180);
        RGBAColor beach = new RGBAColor(255, 232, 181);
        RGBAColor grass = new RGBAColor(77, 135, 52);
        RGBAColor darkGrass = new RGBAColor(35, 75, 26);
        RGBAColor snow = new RGBAColor(240, 240, 240);

        ColorGradient result = new ColorGradient();
        result.add(sea);
        result.add(beach);
        result.add(darkGrass);
        result.add(grass);



        result.add(snow);
        return result;
    }

    /**
     * Helper method to build a black and white gradient 
     * @return
     *  Color gradient with just black and white
     */
    public static ColorGradient buildBlackAndWhite() {
        return new ColorGradient().add(RGBAColor.black()).add(RGBAColor.white());
    }

    /** Creates a new instance of ColorGradient */
    public ColorGradient() {
    }

    /**
     * Method to add a new color to the gradient
     * 
     * @param col Color to add to the gradient
     * @return
     *  the ColorGradient instance so we can chain methods
     */
    public ColorGradient add(RGBAColor col) {
        getColors().add(new RGBAColor(col));
        return this;
    }

    /**
     * Returns a color based on the defined gradient and the interpolate value.
     * 
     * @param t
     * How far through the gradient are we (should be from 0..1)     * 
     * @return
     * A Color instance based on how far into the gradient we are.
     */
    public RGBAColor interpolate(double t) {
        return lerpColors(t);
    }

    /**
     * Interpolates the gradient based on t
     * 
     * @param t
     * The interpolator value ranging from 0..1
     * @param result
     * The RGBAColor instance that will be assigned the value
     */
    public void interpolate(double t, RGBAColor result) {
        lerpColors(t, result);
    }

    /**
     * CosInterpolate function interpolates but uses acceleration at the start 
     * and end to smooth out the values.
     * 
     * @param t
     * How far through the gradient are we (should be from 0..1)     * 
     * @return
     * A Color instance based on how far into the gradient we are.
     */
    public RGBAColor cosInterpolate(double t) {
        return lerpColors(Gradient.cosInterpolate(0, 1, t));
    }

    /**
     * Cos Interpolate that puts the value into the passed in RGBAColor instance
     * 
     * @param t
     * The interpolator value ranging from 0..1
     * @param result
     * The RGBAColor instance that will be assigned the value
     */
    public void cosInterpolate(double t, RGBAColor result) {
        lerpColors(Gradient.cosInterpolate(0, 1, t), result);
    }

    
    private RGBAColor lerpColors(double t) {
        RGBAColor result = new RGBAColor();
        lerpColors(t, result);
        return result;
    }

    private void lerpColors(double t, RGBAColor result) {
        if (getColors().size() == 0) {
            result.setColor(0, 0, 0, 1d);
            return;
        }

        if (getColors().size() == 1) {
            result.setColor(getColors().get(0));
            return;
        }

        if (t > 1) {
            t = 1;
        }
        if (t < 0) {
            t = 0;
        }

        if (t == 1) {
            result.setColor(getColors().get(getColors().size() - 1));
            return;
        }

        t = t * (getColors().size() - 1);
        int it = (int) Math.floor(t);
        double ft = t - it;

        result.setColor(getColors().get(it));
        RGBAColor c2 = getColors().get(it + 1);

        result.interpolate(c2, ft);
    }

    public List<RGBAColor> getColors() {
        return colors;
    }
}
