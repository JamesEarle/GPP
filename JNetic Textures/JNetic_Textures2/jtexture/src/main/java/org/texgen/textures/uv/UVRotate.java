/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.uv;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Rotates the U,V co-ordinates around the 0,0 position.
 * 
 * @author Andy Gibson
 */
public class UVRotate extends AbstractTexture {

    private final double angle;
    private final Texture source;

    public UVRotate(Texture source,double angle) {
        this.angle = angle;
        this.source = source;
    }

    
    
    public void getColor(double u, double v,RGBAColor value) {
        double radAngle = Math.toRadians(angle);
        double ru = Math.cos(radAngle) * u - Math.sin(radAngle) * v;
        double rv = Math.sin(radAngle) * u + Math.cos(radAngle) * v;
        calculateColorFromTexture(ru, rv, source,value);
    }

    public double getAngle() {
        return angle;
    }

    public Texture getSource() {
        return source;
    }
}
