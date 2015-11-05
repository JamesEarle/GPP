/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.utils;

/**
 * Gradient contains static functions that can be used to interpolate values 
 * and colors.
 * 
 * @author Andy Gibson
 */
public class Gradient {
   
    
    static public double interpolate1(double a, double b, double t) {
        return (b * t) + (a * (1 - t));
    }

    static public double cosInterpolate(double a, double b, double t) {

//    	double f = t32Interpolate(t);
//    	return a * (1 - f) + b * f;
        double ft = t * Math.PI;
        double f = (1 - Math.cos(ft)) * 0.5;

        return a * (1 - f) + b * f;
    }

    static public double t32Interpolate(double a, double b,double t) {
    	t = t * t * (3. - 2. * t);
    	return (a*(1-t)) + (b*t);


    }

    static public double clip(double value) {
        if (value < 0) {
            value = 0;
        }
        if (value > 1) {
            value = 1;
        }
        return value;
    }

    static public double smoothStep(double value) {
        return smoothStep(value, 0, 1);
    }

    static public double smoothStep(double value, double min, double max) {
        if (value < min) return 0;
        if (value > max) return 1;
        double scale = max-min;
        if (scale != 0) return cosInterpolate(0,1,(value-min)/scale);
        return 0;
    }

}
