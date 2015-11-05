package fitness;


import java.awt.image.BufferedImage;
import eval.*;


/**
 * This class is used to calculate the mean response value of an image, and
 * extends the FitnessFunction class.
 *
 * @author Steve Bergen
 */

public class Mean extends FitnessFunction {


    /**
     * Constructor
     *
     * @param compare       Comparison value. If not needed, set to any number.
     */

    public Mean ( double compare ) {

        super(compare);

    };


    /**
     * Calculates the mean response value. If response hasn't been initialized
     * yet, then it does so here.
     *
     * @param r             Response class (possibly null)
     * @param img           Source image (of texture)
     * @param base          Source image (user-selected)
     * @return              Double value representing fitness
     */

    @Override
    public double[] calculateFitness ( Response r, BufferedImage img,
            BufferedImage base ) {

        double raw = getRaw(r, img, base);
        double fit[] = {Math.abs(raw - compare), raw};

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

        if ( r == null ) r = new Response(img);

        double sumA     = 0;
        double sumB     = 0;
        double MEAN;

        int w           = r.RESPONSES.length;
        int h           = r.RESPONSES[0].length;

        for ( int x = 0; x < w; x++ )
            for ( int y = 0; y < h; y++ ) {
                if ( r.RESPONSES[x][y] != r.IGNORE ) {
                    sumA += r.RESPONSES[x][y];
                    sumB += Math.pow(r.RESPONSES[x][y], 2);
                }
            }

        if ( sumB == 0.0 || sumA == 0.0 )
            MEAN = 0;
        else
            MEAN = sumB / sumA;

        r.MEAN = MEAN;

        return r.MEAN;

    };


    /**
     * Returns the name of this fitness function
     * @return              String name
     */

    @Override
    public String getName ( ) {

        return "Mean";

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