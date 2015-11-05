/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.signals;

import org.texgen.signals.modifier.SignalUVRotate;
import org.texgen.textures.AbstractChannelSignal;
import org.texgen.textures.ChannelSignal;

/**
 *
 * AnimalStripe is a channel signal that returns the strength of an animal 
 * stripe for a given point on the texture.
 * 
 * @author Andy Gibson
 */

public class AnimalStripe extends AbstractChannelSignal {
    
    private final ChannelSignal sine1;
    private final ChannelSignal sine2;
    private final ChannelSignal sine3;
    
    private final double centralDisplacementSize;

    public AnimalStripe() {
        this(0.5);
    }
    
    
            

    /**
     * Constructs an animal stripe signal
     * 
     * @param centralDisplacementSize
     * Determines the strength of the horizontal displacement at the vertical 
     * center of the texture. This is because the stripes shift on the spine at 
     * animal on a flat laid texture. A displacement of 0 means there is no shift. 
     */
    public AnimalStripe(double centralDisplacementSize) {
        this.centralDisplacementSize = centralDisplacementSize;
        sine1 = new SineWave(10, 3, 1);
        sine2 = new SignalUVRotate(new SineWave(12, 4, 1), 30);
        sine3 = new SignalUVRotate(new SineWave(12, 4, 1), -30);

    }

    public double getValue(double u, double v) {

        //calculate the distance from the vertical center
        double dist = Math.abs(0.5-v);
        u = u + (dist * centralDisplacementSize);

        //scale our u,v
        u = u * 3.5;
        v = v * 3.5;
        
        //calcualte a couple of re-usable noise values
        double n1 = getNoise().fbmNoise2(u, v, 3) * 1;
        double n2 = getNoise().fbmNoise2(u + 37, v + 29, 3) * 1;

        //rescale u,v so we get an elongated set of stripes
        u = u * 1.5;
        v = v * 0.5;

        //calculate the sines and add/substract
        double s1 = sine1.getValue(u, v) * 0.5;
        double s2 = sine2.getValue(u + n2, v) * 1.4;
        double s3 = sine3.getValue(u + n2, v + n1);
        double value1 = s1 + s2 -s3;

        //clip the value so only 0.5 and above is a stripe
        return clip(value1);

    }

    private double clip(double value) {
        if (value >= 0.5) {
            if (value > 0.55) {
                return 1;
            } else {
                return (value - 0.5) * 10;
            }
        } else {
            return 0;
        }
    }

    public double getCentralDisplacementSize() {
        return centralDisplacementSize;
    }

}


