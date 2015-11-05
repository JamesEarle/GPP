package fitness;


import java.awt.image.BufferedImage;
import eval.*;


/**
 * This class is used as a simple object which can be extended for further use.
 * It acts as a base fitness function which can be used to compare values
 * of the same fitness function, without knowing the actual meaning behind
 * the values.
 *
 * @author Steve Bergen
 */

public class FitnessFunction {


    public double compare;              // Comparison value (not necessary)


    /**
     * Constructor
     *
     * @param compare       Comparison value. If not needed, set to any number.
     */

    public FitnessFunction ( double compare ) {

        this.compare = compare;

    };


    /**
     * Calculates the fitness with this fitness function. Returns the value
     * as an array of length two, holding the raw and error fitness based on the
     * compare value (if any).
     *
     * @param r             Response class (possibly null)
     * @param img           Source image
     * @return              Double value representing fitness
     */

    public double[] calculateFitness ( Response r, BufferedImage img,
            BufferedImage base ) {

        return null;

    };


    /**
     * Returns the name of this fitness function
     * @return              String name
     */

    public String getName ( ) {

        return null;

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

    public double getRaw ( Response r, BufferedImage img,
            BufferedImage base ) {

        return 0.0;

    };


    /**
     * Returns true if a is better than b.
     *
     * @param a             First fitness value
     * @param b             Second fitness value
     * @return              Boolean a is better than b
     */

    public boolean isBetter ( double a, double b ) {

        return a < b;

    };


};