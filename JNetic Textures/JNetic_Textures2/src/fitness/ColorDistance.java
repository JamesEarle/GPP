package fitness;


import java.awt.image.BufferedImage;
import eval.*;


/**
 * This class is used to calculate the color distance between two images, and
 * extends the FitnessFunction class.
 *
 * @author Steve Bergen
 */

public class ColorDistance extends FitnessFunction {


    /**
     * Constructor
     *
     * @param compare       Comparison value. If not needed, set to any number.
     */

    public ColorDistance ( double compare ) {

        super(compare);

    };


    /**
     * Calculates the color distance between two images. Returns the value
     * as an array of length two, holding the raw and error fitness based on the
     * compare value (if any).
     *
     * @param r             Response class (possibly null)
     * @param img           Source image
     * @return              Double value representing fitness
     */

    @Override
    public double[] calculateFitness ( Response r, BufferedImage img,
            BufferedImage base ) {

        double raw = getRaw(r, img, base);
        double fit[] = {raw, raw};

        return fit;

    };


    /**
     * Calculates and returns the raw fitness value of this fitness function.
     * A Response object, as well as two images are required, but their use
     * depends on implementation.
     *
     * @param r             Response object
     * @param img           Source image 1
     * @param base          Source image 2
     * @return              Raw fitness value
     */

    @Override
    public double getRaw ( Response r, BufferedImage img,
            BufferedImage base ) {

        double  total       = 0.0;
        double  dist;
        int     k           = 1;

        // Go through the entire image, calculating pixel distance if needed
        for ( int i = 0; i < base.getWidth(); i++ ) {
            for ( int j = 0; j < base.getHeight(); j++ ) {

                k++;
                if ( k % param.Parameters.PIXEL == 0 ) {
                    dist = ColorUtil.colorDistance(ColorUtil.getPixel(i, j,base)
                            ,ColorUtil.getPixel(i, j, img));
                    total += dist;
                }

            }
        }

        return total / ((double)(base.getWidth() * base.getHeight() ) /
                (double)param.Parameters.PIXEL);

    };


    /**
     * Returns the name of this fitness function
     * @return              String name
     */

    @Override
    public String getName ( ) {

        return "CDist";

    };


    /**
     * Returns true if a is better than b.
     *
     * @param a             First fitness value
     * @param b             Second fitness value
     * @return              Boolean a is better than b
     */

    @Override
    public boolean isBetter ( double a, double b ) {

        return a >= b;

    };


};