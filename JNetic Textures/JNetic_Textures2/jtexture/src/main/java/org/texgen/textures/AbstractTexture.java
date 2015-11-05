/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures;

import org.texgen.textures.Texture;
import org.texgen.utils.PerlinNoise;
import org.texgen.utils.RGBAColor;

/**
 *
 * Abstract implementation of the Texture interface. Includes a number of helper
 * methods.
 * 
 * @author Andy Gibson
 */
public abstract class AbstractTexture implements Texture {

    protected static final PerlinNoise noise = new PerlinNoise();
    
    /**     
     * Helper method to take a U,V value, a texture and determine the color and 
     * load it into the RGBAColor target. This function checks for null textures
     * and returns black (0,0,0,1) if the texture is null.
     * 
     * @param u 
     * U Component used to determine the color from the texture
     * @param v
     * V Component used to determine the color from the texture
     * @param texture
     * Texture to use to calculate the color
     * @param target
     * RGBAColor object to receive the final color
     */
    protected void calculateColorFromTexture(double u, double v, Texture texture, RGBAColor target) {
        if (texture != null) {
            target.setColor(texture.getColor(u, v));
            return;
        }
        target.setColor(0, 0, 0, 1);
        return;
    }

    /**     
     * Helper method to take a U,V value, a texture and determine the color and 
     * returns it in a new RGBAColor. This function checks for null textures.
     * 
     * @param u 
     * U Component used to determine the color from the texture
     * @param v
     * V Component used to determine the color from the texture
     * @param texture
     * Texture to use to calculate the color
     * @return the RGBAColor for this texture at this U,V point
     */
    protected RGBAColor calculateColorFromTexture(double u, double v, Texture texture) {
        RGBAColor res = new RGBAColor();
        calculateColorFromTexture(u, v, texture, res);
        return res;
    }

    /**     
     * Helper method to take a U,V value, a channel signal and return the 
     * signal value. This function checks for null signals, and returns a 
     * default value of 0 if it is null.
     * 
     * @param u 
     * U Component used to determine the color from the texture
     * @param v
     * V Component used to determine the color from the texture
     * @param signal
     * The channel signal to use to calculate the value at u,v
     * @return the value for this signal at point u,v
     */
    protected double calculateSignalFromSignal(double u, double v, ChannelSignal signal) {
        return calculateSignalFromSignal(u, v, signal, 0);
    }

    /**
     * Helper method to take a U,V value, a channel signal and return the 
     * signal value. This function checks for null signals, and returns the
     * default value the signal is null.
     * 
     * @param u 
     * U Component used to determine the color from the texture
     * @param v
     * V Component used to determine the color from the texture
     * @param signal
     * The channel signal to use to calculate the value at u,v
     * @param defaultValue
     * The default value to use if the signal is null
     * @return the value for this signal at point u,v
     */
    protected double calculateSignalFromSignal(double u, double v, ChannelSignal signal, double defaultValue) {
        if (signal == null) {
            return defaultValue;
        }
        return signal.getValue(u, v);

    }

    /**
     * 
     * This function takes a double as a parameter and returns the value limited
     * to ranges between 0..1. This can be important when calcualting a cyclical 
     * texture that depends on the input values being between 0 and 1 (i.e 
     * Brick, or checker patterns). The returned value is based on the 
     * fractional part of the input value.
     * 
     * e.g. 
     *  
     *   normalize(0.3) = 0.3
     *   normalize(5.3) = 0.3
     *   normalize(-0.3) = 0.7
     *   normalize(-5.3) = 0.7
     * 
     * @param value
     * The value to be normalized
     * @return
     * The normalized input value
     */
    public static double normalize(double value) {
        if (value < 0) {
            value = Math.ceil(value) + value;
        }
        value = value - Math.floor(value);
        return value;

    }

    public RGBAColor getColor(double u, double v) {
        RGBAColor value = new RGBAColor();
        getColor(u, v, value);
        return value;
    }
    
    public static PerlinNoise getNoise() {
    	return noise;
    }
    
    
}
