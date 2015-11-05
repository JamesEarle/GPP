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
import org.texgen.utils.GraphUtils;

/**
 * Emulates the signal of a light based on distance from a point, and strength 
 * of signal
 * 
 * @author Andy Gibson
 */
public class LightSignal extends AbstractChannelSignal {

    private final double originX;
    private final double originY;
    private final double strength;

    public LightSignal(double originX, double originY, double strength) {
        this.originX = originX;
        this.originY = originY;
        this.strength = strength;
    }
    
    public double getValue(double u, double v) {
        double dist = GraphUtils.distance(u, v, originX, originY);
        dist = dist * dist;
        if (dist != 0) {
            dist = strength/dist;
        }
        dist = GraphUtils.clamp(dist);
        return dist;        
    }

	public double getOriginX() {
		return originX;
	}

	public double getOriginY() {
		return originY;
	}

	public double getStrength() {
		return strength;
	}
    
}
