/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.complex;

import org.texgen.textures.Texture;
import org.texgen.textures.AbstractTexture;
import org.texgen.textures.color.SolidTexture;
import org.texgen.textures.pattern.BrickTexture;
import org.texgen.utils.RGBAColor;

/**
 * Returns a color based on brick texture with a dirty noise overlay on top
 * 
 * @author Andy Gibson
 */
public class DirtyBrick extends AbstractTexture {

    private final Texture brick;

    public DirtyBrick() {                        
        Texture mortar = new Dirty(new SolidTexture(new RGBAColor(200,200,200)),RGBAColor.black(),0.65,70,3); 
        Texture brickColor = new Dirty(new SolidTexture(new RGBAColor(128,32,16)),RGBAColor.black(),0.4,70,3); 
        Texture temp = new BrickTexture(brickColor,mortar);
        brick = new Dirty( temp,RGBAColor.black(),0.45,15,3);                        
    }

    public void getColor(double u, double v, RGBAColor value) {
        double nv = v * 30;
        double nu = u * 30;
        u = u + noise.fbmNoise2(nu, nv, 3)*0.003;
        v = v + noise.fbmNoise2(nu+47, nv+91, 3)*0.003;
        brick.getColor(u, v, value);
    }
}
