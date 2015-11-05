/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.texgen.textures.AbstractTexture;
import org.texgen.utils.RGBAColor;

/**
 * Loads an image file and uses it to provide texture colors.
 * 
 * @author Andy Gibson
 */
public class ImageTexture extends AbstractTexture {

    private BufferedImage image;

    public ImageTexture(String fileName) {
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void getTexel(double u, double v, RGBAColor value) {

        double x = u * (double) image.getWidth();
        double y = v * (double) image.getHeight();



        int ix = (int) x;
        int iy = (int) y;
        double fx = (double) x - ix;
        double fy = (double) y - iy;

        if (ix <0 || iy < 0) {
            value.setColor(0,0,0,0);
            return;            
        }
        if (ix >= image.getWidth()-1 || iy >= image.getHeight()-1) {
            value.setColor(0,0,0,0);
            return;            
        }
        
        
        int dx = 1;
        if (ix == image.getWidth() - 1) {
            dx = 0;
        }

        int dy = 1;
        if (iy == image.getHeight() - 1) {
            dy = 0;
        }

        
        
        RGBAColor c1 = new RGBAColor(image.getRGB(ix, iy));
        RGBAColor c2 = new RGBAColor(image.getRGB(ix + dx, iy));
        RGBAColor c3 = new RGBAColor(image.getRGB(ix, iy + dy));
        RGBAColor c4 = new RGBAColor(image.getRGB(ix + dx, iy + dy));


        //use billinear interpolation from c1 to c2 and c3 to c4 to 
        //generate mid-points c1 and c3, then interpolate between those two
        c1.interpolate(c2, fx);
        c3.interpolate(c4, fx);
        
        c1.interpolate(c3, fy);
        value.setColor(c1);
    }

    public void getColor(double u, double v, RGBAColor value) {
        getTexel(u, v, value);
    }

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
