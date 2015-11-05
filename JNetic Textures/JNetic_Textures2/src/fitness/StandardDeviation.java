package fitness;


import java.awt.image.BufferedImage;
import eval.*;


/**
 * This class is used to calculate the standard deviation of response values of
 * an image, and extends the FitnessFunction class.
 *
 * @author Steve Bergen
 */

public class StandardDeviation extends FitnessFunction {


    /**
     * Constructor
     *
     * @param compare       Comparison value. If not needed, set to any number.
     */

    public StandardDeviation ( double compare ) {

        super(compare);

    };


    /**
     * Calculates the standard deviation of response values. If response hasn't
     * been initialized yet, then it does so here.
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
        if ( r.MEAN == -1 ) r.MEAN = new Mean(0).calculateFitness(r, img, base)[1];

        double sumA     = 0;
        double sumB     = 0;
        double STND_DEV;

        int w           = r.RESPONSES.length;
        int h           = r.RESPONSES[0].length;

        for ( int x = 0; x < w; x++ )
            for ( int y = 0; y < h; y++ ) {
                if ( r.RESPONSES[x][y] != r.IGNORE ) {
                    sumA += r.RESPONSES[x][y];
                    sumB += r.RESPONSES[x][y] *
                            Math.pow(r.RESPONSES[x][y] - r.MEAN, 2);
                }
            }

        if ( sumB == 0.0 || sumA == 0.0 ) STND_DEV = 0;
        else STND_DEV    = sumB / sumA;

        r.INCREMENT     = STND_DEV / 100.0;
        r.STD           = STND_DEV;

        return r.STD;

    };


    /**
     * Returns the name of this fitness function
     * @return              String name
     */

    @Override
    public String getName ( ) {

        return "STD";

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