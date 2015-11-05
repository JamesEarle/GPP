/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.pattern;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 *
 * Implements a grid by dicing the texture and calling the source texture for 
 * each cell with values for 0..1 for u,v values in each cell. 
 * Also accomodates a margin for each cell.
 * 
 * @author Andy Gibson
 */
public class GridPattern extends AbstractTexture {

    private final int width;
    private final int height;
    private final double border;
    private final Texture source;

    public GridPattern(Texture source,int width, int height, double border) {
        this.width = width;
        this.height = height;
        this.border = border;
        this.source = source;
    }
    
    public GridPattern(Texture source,int width, int height) {
        this(source,width,height,0.1);        
    }
    

    public GridPattern(Texture source) {
        this(source,5,5,0.051);        
    }
    
    
        
    public void getColor(double u, double v, RGBAColor value) {
        u = u * width;
        v = v * height;
        u = u - (int)u;
        v = v - (int)v;        
        
        if (u > border && u < 1-border && v > border && v < 1-border && border < 0.5) {
            u = u - (border);
            v = v - (border);
            
            u = u / (1-(2 * border));
            v = v / (1-(2 * border));
            
            
            calculateColorFromTexture(u, v, source,value);
        } else {
            value.setColor(0,0,0,0);
        }
    }

}
