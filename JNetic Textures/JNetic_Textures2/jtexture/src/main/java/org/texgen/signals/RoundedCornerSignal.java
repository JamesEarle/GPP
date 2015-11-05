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
 * Channel Signal for producing a square with a rounded corner
 * 
 * @author Andy Gibson
 */
public class RoundedCornerSignal extends AbstractChannelSignal {

    private final double radius;

    public RoundedCornerSignal() {
        this(0.1);
    }

    public RoundedCornerSignal(double radius) {
        this.radius = radius;
    }
        
    public double getValue(double u, double v) {
        double nu = u;
        double nv = v;

        // mirror the u,v around the center so we only have to deal with the top 
        // left corner.
        if (nu > 0.5) {
            nu = 1 - nu;
        }

        if (nv > 0.5) {
            nv = 1 - nv;
        }

        //how far are we from the center of the 'corner' sphere
        double dist = GraphUtils.distance(nu, nv, radius, radius);
        
        //check we are in the corner sphere, or beyond it, and not negative
        if ((dist < radius || nu > radius || nv > radius) && (nu > 0 && nv > 0)) {
            return 1;
        } else {
            return 0;
        }
        
    }

	public double getRadius() {
		return radius;
	}

}
