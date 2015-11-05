package fitness;


import java.awt.image.BufferedImage;
import eval.*;


/**
 * This class is used to calculate the color histogram distance between two
 * images, and extends the FitnessFunction class.
 *
 * @author Steve Bergen
 */

public class HistogramDistance extends FitnessFunction {


    /**
     * Constructor
     *
     * @param compare       Comparison value. If not needed, set to any number.
     */

    public HistogramDistance ( double compare ) {

        super(compare);

    };


    /**
     * Calculates the color histogram distance between two images
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

        ColorHistogram h1 = new ColorHistogram(img);

        return h1.colorHistDistance(param.Parameters.SOURCEHIST);

    };


    /**
     * Returns the name of this fitness function
     * @return              String name
     */

    @Override
    public String getName ( ) {

        return "HDist";

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

        return a <= b;

    }; 


};