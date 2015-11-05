/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.complex;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.textures.color.SolidTexture;
import org.texgen.utils.RGBAColor;

/**
 * A texture that uses a dot and background texture to create a polkdot texture.
 * 
 * @author Andy Gibson
 */
public class Polkadot extends AbstractTexture {

    private final Texture backgroundTexture;
    private final Texture dotTexture;
    private final double dotSize;
    private final int scale;

    public Polkadot() {
        this(SolidTexture.whiteBackground(), SolidTexture.blackBackground());
    }

    public Polkadot(Texture backgroundTexture, Texture dotTexture) {
        this(backgroundTexture, dotTexture, 8, 0.4);
    }

    public Polkadot(Texture backgroundTexture, Texture dotTexture, int scale, double dotSize) {
        this.backgroundTexture = backgroundTexture;
        this.dotTexture = dotTexture;        
        this.scale = scale;
        this.dotSize = dotSize;
    }

    public void getColor(double u, double v, RGBAColor value) {

        RGBAColor dotColor = new RGBAColor();

        calculateColorFromTexture(u, v, backgroundTexture, value);
        calculateColorFromTexture(u, v, dotTexture, dotColor);

        //make it an 8 X 8 grid
        u = u * scale;
        v = v * scale;


        //get the integer and fractional parts
        int iu = (int) u;
        int iv = (int) v;
        double fu = u - iu;
        double fv = v - iv;

        //iu,iv determines which square we are on
        //noise(iu,iv) determines the center of the
        //dot for that square.
        double du = noise.noise2(iu, iv);
        double dv = noise.noise2(iu + 565, iv + 273);

        //center the dot in the center are of the square
        //we have to place the dot at least dotSize/2 away 
        //from the edge of the square.
        du = du * (1 - dotSize);
        dv = dv * (1 - dotSize);
        du = du + (dotSize * 0.5);
        dv = dv + (dotSize * 0.5);

        //du,dv = location of dot center in this square
        du = du - fu;
        dv = dv - fv;

        //calculate the distance of fu,fv from the center
        //of the dot
        double dist = Math.sqrt((du * du) + (dv * dv));

        //if the distance is less than dotsize/2 then 
        //it is in the dot
        if (dist < (dotSize * 0.5)) {
            //check if it is on the dot edge
            if (dist < (dotSize * 0.45)) {
                //if not, just merge the dot color
                value.merge(dotColor);
            } else {
                //this point is on the dot edge so we need 
                //to alias it.
                
                //dist is in the range dotSize * (0.45 to 0.5)
                dist = dist - 0.45;
                double alpha = dotColor.getAlpha();

                alpha = alpha * (dist * 20);
                dotColor.setAlpha(1 - alpha);
                value.merge(dotColor);
            }

        }
    }
}
