/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.color;

import org.texgen.signals.USignal;
import org.texgen.textures.signal.GradientSignalTexture;
import org.texgen.utils.ColorGradient;

/**
 * Given a color gradient, this texture returns a color from the gradient based 
 * on the value of u.
 * <pre>
 * Result = gradient.interpolate(u)
 * </pre>
 * @author Andy Gibson
 */
public class VerticalGradient extends GradientSignalTexture {

    public VerticalGradient(ColorGradient gradient) {
        super(new USignal(), gradient);        
    }
}
