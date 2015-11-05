/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.color;

import org.texgen.signals.RadialSignal;
import org.texgen.textures.signal.GradientSignalTexture;
import org.texgen.utils.ColorGradient;

/**
 * Given a gradient this texture produces a circular gradient pattern centered 
 * at 0.5,0.5, with the gradient stretched to the corners of the texture.
 * <pre>
 * maxDistance = distance from center to corner;
 * d = distance of (u,v) from the center of the texture 
 * pos = d/maxDistance;
 * result = gradient.interpolate( pos);
 * </pre>
 * 
 * @author Andy Gibson
 */
public class RadialGradient extends GradientSignalTexture {

    public RadialGradient(ColorGradient gradient) {
        super(new RadialSignal(),gradient);
    }

}
