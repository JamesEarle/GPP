/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.color;

import org.texgen.signals.VSignal;
import org.texgen.textures.signal.GradientSignalTexture;
import org.texgen.utils.*;

/**
 * Given a color gradient, this texture returns a color from the gradient based 
 * on the value of v.
 * 
 * Result = gradient.interpolate(v)
 * 
 * @author Andy Gibson
 */
public class HorizontalGradient extends GradientSignalTexture {

    public HorizontalGradient(ColorGradient gradient) {
        super(new VSignal(), gradient);        
    }
}
