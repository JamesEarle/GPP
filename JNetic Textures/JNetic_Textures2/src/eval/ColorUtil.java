package eval;


import java.awt.image.BufferedImage;
import java.awt.Color;


/**
 * This class contains functions for retrieving color data from a pixel in 
 * a BufferedImage, as well as calculating color distance between two colors.
 *
 * @author Steve Bergen
 */

public class ColorUtil {
    
    /** Maximum possible color distance between two colors */
    public static double        maxDistance = Math.sqrt(3.0);
    
    
    /**
     * Return the color of the pixel at (x, y) of supplied image.
     * 
     * @param x       X index
     * @param y       Y index
     * @param img     Supplied BufferedImage
     * @return        Color object representing pixel color
    */
    
    public static Color getPixel ( int x, int y, BufferedImage img ) {

        Color c = new Color(img.getRGB(x, y));
        return c;
        
    };
    
    
    /**
     * Return the "distance" between two colors. The rgb entries are taken
     * to be coordinates in a 3D space [0.0-1.0], and this method returnes
     * the distance between the coordinates for the first and second color.
     * This method also squares the distance for better results
     * 
     * @param   r1      First red
     * @param   g1      First green
     * @param   b1      First blue
     * @param   r2      Second red
     * @param   g2      Second green
     * @param   b2      Second blue
     * @return  Distance between colors.
    */
    
    public static double colorDistance (double r1, double g1, double b1,
                                      double r2, double g2, double b2) {
      
        double a = r2 - r1;
        double b = g2 - g1;
        double c = b2 - b1;

        return Math.pow((1.0 - ((Math.sqrt(a*a + b*b + c*c))/maxDistance)), 2);
    
    };
  
  
    /**
     * Return the "distance" between two colors.
     * 
     * @param color1  First color [r,g,b]
     * @param color2  Second color [r,g,b]
     * @return        Distance bwetween colors
    */
    
    public static double colorDistance (double[] color1, double[] color2) {
      
        return colorDistance (color1[0], color1[1], color1[2],
                                    color2[0], color2[1], color2[2]);
    
    };


    /**
     * Return the "distance" between two colors.
     * 
     * @param color1  First color
     * @param color2  Second color
     * @return        Distance between colors
    */
    
    public static double colorDistance (Color color1, Color color2) {
      
        float rgb1[] = new float[3];
        float rgb2[] = new float[3];    

        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);    

        return colorDistance (rgb1[0], rgb1[1], rgb1[2], 
                rgb2[0], rgb2[1], rgb2[2]);
    
    };
    
    
};
