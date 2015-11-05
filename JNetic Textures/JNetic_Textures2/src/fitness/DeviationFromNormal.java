package fitness;


import java.awt.image.BufferedImage;
import eval.*;


/**
 * This class is used to calculate the DFN of response values of an image, and
 * extends the FitnessFunction class.
 *
 * @author Steve Bergen
 */

public class DeviationFromNormal extends FitnessFunction {


    /**
     * Constructor
     *
     * @param compare       Comparison value. If not needed, set to any number.
     */

    public DeviationFromNormal ( double compare ) {

        super(compare);

    };


    /**
     * Calculates the DFN of response values. If response hasn't been
     * initialized yet, then it does so here.
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

        if ( r == null )
            r = new Response(img);
        if ( r.STD == -1 )
            r.STD = new StandardDeviation(0).calculateFitness(r, img, base)[1];

        createHistogram(r);

        double total = 0.0;
        double pi, qi;

        if ( r.MEAN == 0 || r.STD == 0 ) return 100.0;

        for ( int i = 0; i < r.HISTOGRAM.length; i++ ) {

            pi = probObserved( (double)i * r.INCREMENT, r );
            qi = Math.abs(probExpected( (double)i * r.INCREMENT + r.INCREMENT,r)
                    - probExpected( (double)i * r.INCREMENT , r));

            if ( qi != 0.0 && pi != 0.0 )
                total += pi * Math.log(pi / qi);

        }

        //total *= 1000.0;
        if ( total <= 0.0000001 ) return 100.0;
        return total;

    };


    /**
     * Returns the name of this fitness function
     * @return              String name
     */

    @Override
    public String getName ( ) {

        return "DFN";

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


    /**
     * Creates histogram object for storage in the response object.
     *
     * @param r             Response
     */

    private static void createHistogram ( Response r ) {

        r.TOTAL      = 0;

        if ( r.MAX - r.MIN <= 0 || r.INCREMENT == 0 )
            r.HISTOGRAM  = new int[ 1 ];
        else
            r.HISTOGRAM  = new int[ (int)((r.MAX - r.MIN) / r.INCREMENT) + 1 ];

        int w           = r.RESPONSES.length;
        int h           = r.RESPONSES[0].length;

        for ( int i = 0; i < r.HISTOGRAM.length; i++ )
            r.HISTOGRAM[i] = 0;

        for ( int x = 0; x < w; x++ )
            for ( int y = 0; y < h; y++ ) {
                if ( r.RESPONSES[x][y] != r.IGNORE ) {
                    r.TOTAL++;
                    r.HISTOGRAM[(int)((r.RESPONSES[x][y]-r.MIN)/r.INCREMENT)]++;
                }
            }

    };


    /**
     * Returns the probability of expected value based on mean and stnd-dev.
     *
     * @param x             Current value
     * @param r             Response
     * @return              Expected probability of x
     */

    private static double probExpected ( double x, Response r ) {

        double exponent     = 1.0 / r.STD;
        double prob         = exponent * probDensity((x - r.MEAN) / r.STD);

        return prob;

    };


    /**
     * Returns the observed probability of value based on mean and stnd-dev.
     *
     * @param x             Current value
     * @param r             Response
     * @return              Observed probability of x
     */

    private static double probObserved ( double x, Response r ) {

        int index           = (int)(x / r.INCREMENT);
        double prob         = (double)r.HISTOGRAM[index] / (double)r.TOTAL;

        return prob;

    };


    /**
     * Calculates the probability density.
     *
     * @param x             Current value
     * @return              Density value
     */

    private static double probDensity ( double x ) {

        double exponent     = Math.pow(x, 2) / -2.0;
        double func         = (1.0 / Math.sqrt(2.0 * Math.PI)) * Math.exp(exponent);

        return func;

    };


};