/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.complex;

import org.texgen.signals.NoiseSignal;
import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.textures.color.SolidTexture;
import org.texgen.textures.signal.SmoothThreshold;
import org.texgen.textures.uv.UVRotate;
import org.texgen.textures.uv.UVScale;
import org.texgen.utils.RGBAColor;

/**
 * Camouflage texture used as the demo texture in the documentation
 *  
 * @author Andy Gibson
 */
public class Camouflage extends AbstractTexture {

    private final Texture texture;

    public Camouflage() {        
        SolidTexture green = new SolidTexture(new RGBAColor(119,139,111));       
        SolidTexture brown = new SolidTexture(new RGBAColor(119,110,100));
        SolidTexture beige = new SolidTexture(new RGBAColor(166,150,116));        
        
        Texture cam1 = new SmoothThreshold(green,beige, new NoiseSignal(4,10,1,10),0.42);
        Texture cam2 = new SmoothThreshold(beige,brown, new NoiseSignal(4,10,1,10),0.25);
        
        
        Texture rotatedCam1 = new UVRotate(new UVScale(cam1,1,4),20);
        Texture rotatedCam2 = new UVRotate(new UVScale(cam2,1,6),34);
        Texture temp = new SmoothThreshold(rotatedCam1, rotatedCam2, new NoiseSignal(6,4,1));       
        
        texture = new Dirty(temp,RGBAColor.black(),0.32);

    }
    
    public void getColor(double u, double v, RGBAColor value) {
        texture. getColor(u*1.3, v*1.3,value);
    }
}





















