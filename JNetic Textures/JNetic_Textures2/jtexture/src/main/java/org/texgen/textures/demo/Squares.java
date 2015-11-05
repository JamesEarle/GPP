/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.demo;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.textures.color.Background;
import org.texgen.textures.color.HorizontalGradient;
import org.texgen.textures.composite.MultiMergeTexture;
import org.texgen.textures.pattern.RoundedCornerTexture;
import org.texgen.textures.signal.AlphaSignal;
import org.texgen.textures.uv.UVTransformer;
import org.texgen.utils.ColorGradient;
import org.texgen.utils.RGBAColor;

/**
 * Texture demonstrating the demo using multiple transformed squares 
 * 
 * @author Andy Gibson
 */
public class Squares extends AbstractTexture {

    private final Texture finalTexture;

    public Squares() {
        //make the color a gradient
        Texture gradient = new HorizontalGradient(ColorGradient.buildFire());
        //Put an alpha filter texture over it
        gradient = new AlphaSignal(gradient, 0.75);

        //make a rounded corner that uses our gradient as the color
        RoundedCornerTexture box = new RoundedCornerTexture(gradient, 0.3);

        //create the final merging texture
        MultiMergeTexture mixer = new MultiMergeTexture();

        for (int i = 0; i < 20; i++) {
            //calcualte the scale and offset for this box
            double offset = ((double) i / 22) - 0.5;
            double scale = 14 - (i / 2);
            //create the new transformer texture wrapped around the box
            UVTransformer transformer = new UVTransformer(box, offset, 0, scale, scale, i * 3);
            //add it to the final merged texture
            mixer.getTextures().add(transformer);
        }
        //display it on a black background.
        finalTexture =  new Background(mixer, RGBAColor.black());

    }

    public void getColor(double u, double v, RGBAColor value) {
        calculateColorFromTexture(u, v, finalTexture,value);
    }
}
