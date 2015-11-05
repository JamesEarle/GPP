package eval;


import java.awt.image.*;


/**
 * This class is used to calculate color distance between two images.
 *
 * @author Steve Bergen
 */

public class ColorDistance {


    /**
     * Calculates the color distance between images, and returns a value from
     * [0.0 .. 1.0] representing this distance.
     *
     * @param base              Image 1
     * @param img               Image 2
     * @param pixel             Per-pixel distance
     * @return                  Color distance value
     */

    public static double calculate ( BufferedImage base, BufferedImage img, 
            int pixel ) {
        
        double  total       = 0.0;       
        double  dist;
        int     k           = 1;
        
        // Go through the entire image, calculating pixel distance if needed
        for ( int i = 0; i < base.getWidth(); i++ ) {
            for ( int j = 0; j < base.getHeight(); j++ ) {
                
                k++;
                if ( k % pixel == 0 ) {
                    dist = ColorUtil.colorDistance(
                            ColorUtil.getPixel(i, j, base)
                            ,ColorUtil.getPixel(i, j, img));
                    total += dist;            
                }
                
            }
        }
        
        return total /
                ((double)(base.getWidth() * base.getHeight() ) / (double)pixel);
        
    };
    
    
};