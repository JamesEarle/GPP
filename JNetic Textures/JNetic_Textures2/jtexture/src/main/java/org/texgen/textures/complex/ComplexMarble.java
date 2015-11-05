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
import org.texgen.textures.pattern.Marble;
import org.texgen.textures.uv.UVRotate;
import org.texgen.textures.color.SolidTexture;
import org.texgen.textures.composite.MergedTexture;
import org.texgen.textures.composite.MultiMergeTexture;
import org.texgen.utils.RGBAColor;

/**
 * Returns a complex marble color based on a number of composite textures
 * 
 * @author Andy Gibson
 */
public class ComplexMarble extends AbstractTexture {
    
    private final MergedTexture mixer;
    private final MultiMergeTexture marble;
    private final double scale;

    /**
     * Constructs an instance of Complex marble with a scale of 1
     */
    public ComplexMarble() {
        this(1);
    }

    /**
     * Constructs an instance of ComplexMarble
     * 
     * @param scale
     * The scale to use for the marble in controlling how fast the marble moves
     * across the texture
     */
    public ComplexMarble(double scale) {
        this(scale,45,23);        
    }
    
    /**
     * Constructs and instance of complex marble
     * 
     * @param scale
     *   The rate at which the marble moves across the surface
     * 
     * @param uOffset
     *   The offset of the u value on the surface - helps remove obvious repetitions
     * @param vOffset
     *   The offset of the v value on the surface - helps remove obvious repetitions
     */
    public ComplexMarble(double scale,double uOffset,double vOffset) {
        Texture blackMarble = new Marble(new RGBAColor(0,0,0,0.65),uOffset,vOffset,4.2,7,16,1);
        Texture coloredMarble = new Marble(new RGBAColor(128,64,0,0.68),uOffset,vOffset,3,9,19,1);
        Texture blueMarble = new Marble(new RGBAColor(32,64,96,0.55),uOffset,vOffset,0.945,9,12,1);
        //marble = new MergedTexture( new UVRotate(blackMarble,40), new UVRotate(coloredMarble,80));
        
        marble = new MultiMergeTexture();
        marble.getTextures().add(new UVRotate(blackMarble,10));
        marble.getTextures().add(new UVRotate(coloredMarble,80));
        marble.getTextures().add(new UVRotate(blueMarble,40));
        
        mixer = new MergedTexture(SolidTexture.whiteBackground(), marble);        
        this.scale = scale;
    }

    public final void getColor(double u, double v,RGBAColor value) {
        mixer.getColor(u * scale, v * scale,value);
        //blackMarble.getColor(u, v,value);        
    }
    
    
}

