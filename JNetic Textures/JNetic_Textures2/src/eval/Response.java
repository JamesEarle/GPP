package eval;


import java.awt.image.BufferedImage;
import java.awt.Color;


/**
 * This class is used to calculate the response values of an image, and is
 * called for each image in fitness calculation. This class is also used as a
 * storage class for many fitness functions in order to pass information around.
 *
 * @author Steve Bergen
 */

public class Response {


    private final double    S0                  = 2.0;
    public  final double    IGNORE              = -1000.0;
    private final boolean   USE_NEG_SD          = true;

    public  double[][]      RESPONSES;

    public  double          MAX;
    public  double          MIN;
    
    // These 3 values can be previously calculated in other fitness functions
    // If they are not, they will ahve to be calculated by the calling functions
    public  double          MEAN                = -1;
    public  double          STD                 = -1;
    public  double          INCREMENT           = -1;
    public  int[]           HISTOGRAM           = null;
    public  int             TOTAL               = -1;


    /**
     * Constructor which calculates initial respons values.
     *
     * @param src           Source image
     */

    public Response ( BufferedImage src ) {

        calculateResponses(src);

    };


    // ---------------------------------------------------------------------- //
    // ----------------------- UTILITY FUNCTIONS ---------------------------- //
    // ---------------------------------------------------------------------- //


    /**
     * Returns the red value of a color in an image at pixel (x, y).
     *
     * @param x             X coordinate
     * @param y             Y coordinate
     * @param img           Source image
     * @return              Color value
     */

    private int getRed ( int x, int y, BufferedImage img ) {

        Color c = new Color(img.getRGB(x, y));
        return c.getRed();

    };


    /**
     * Returns the green value of a color in an image at pixel (x, y).
     *
     * @param x             X coordinate
     * @param y             Y coordinate
     * @param img           Source image
     * @return              Color value
     */

    private int getGreen ( int x, int y, BufferedImage img ) {

        Color c = new Color(img.getRGB(x, y));
        return c.getGreen();

    };


    /**
     * Returns the blue value of a color in an image at pixel (x, y).
     *
     * @param x             X coordinate
     * @param y             Y coordinate
     * @param img           Source image
     * @return              Color value
     */

    private int getBlue ( int x, int y, BufferedImage img ) {

        Color c = new Color(img.getRGB(x, y));
        return c.getBlue();

    };


    /**
     * Returns the scaling factor of an image.
     *
     * @param img           Source image
     * @return              Scaling value
     */

    private double getScalingFactor ( BufferedImage src ) {

        double d = Math.sqrt( Math.pow(src.getWidth(), 2) +
                Math.pow(src.getHeight(), 2) );

        return d * 0.001;

    };


    /**
     * This function returns the red, green and blue changes between values
     * in both diagonal directions from pixel (x, y) in the source image. Each
     * value is squared, added, then divided by the diagonal distance of the
     * image squared.
     *
     * @param src       Source image
     * @param x         Pixel X coordinate
     * @param y         Pixel Y coordinate
     * @param d         Diagonal distance
     * @return          Gradient change represented by an RGB vector.
     */

    private double[] getPixelChange ( BufferedImage src, int x, int y,
            double d ) {

        // Red value
        double Rxy =   
                ( Math.pow( getRed(x, y, src) - getRed(x + 1, y + 1, src), 2) )
                + (Math.pow( getRed(x + 1, y, src) - getRed(x, y + 1, src), 2));

        Rxy /= Math.pow(d, 2);

        // Green Value
        double Gxy =   
                (Math.pow( getGreen(x, y, src)-getGreen(x + 1, y + 1, src), 2))
                + (Math.pow(getGreen(x+1, y, src)-getGreen(x, y + 1, src), 2));
        Gxy /= Math.pow(d, 2);

        // Blue value
        double Bxy =
                ( Math.pow( getBlue(x, y, src) - getBlue(x + 1, y + 1, src), 2))
                + (Math.pow(getBlue(x + 1, y, src)-getBlue(x, y + 1, src), 2));
        Bxy /= Math.pow(d, 2);

        double RGB[] = {Rxy, Gxy, Bxy};

        return RGB;

    };


    /**
     * Calculates the stimulus of a pixel's change.
     *
     * @param src       Source image
     * @param x         X coordinate
     * @param y         Y coordinate
     * @return          A double value representing the stimulus
     */

    private double getStimulus ( BufferedImage src, int x, int y ) {

        double d        = getScalingFactor(src);
        double rgb[]    = getPixelChange(src, x, y, d);

        return Math.sqrt( rgb[0] + rgb[1] + rgb[2] );

    };


    /**
     * Calculates the response value of a pixel.
     *
     * @param src       Source image
     * @param x         X coordinate
     * @param y         Y coordinate
     * @return          Response value
     */

    private double getResponse ( BufferedImage src, int x, int y ) {

        double stimulus = getStimulus(src, x, y);

        // Ignore value if stimulus = 0
        if ( stimulus == 0 || (Math.log( stimulus / S0) < 0) && USE_NEG_SD)
            return IGNORE;
        else
            return Math.log( stimulus / S0 );

    };


    /**
     * Calculates the response values of every pixel in the source image, and
     * saves these values to an array.
     *
     * @param src       Source image
     */

    private void calculateResponses ( BufferedImage src ) {

        int w       = src.getWidth() - 1;
        int h       = src.getHeight() - 1;

        double max  = -1;
        double min = 9999;

        double responses[][] = new double[w][h];

        for ( int x = 0; x < w; x++ )
            for ( int y = 0; y < h; y++ ) {
                responses[x][y] = getResponse(src, x, y);
                if ( responses[x][y] > max )
                    max = responses[x][y];
                if ( responses[x][y] < min && responses[x][y] != IGNORE )
                    min = responses[x][y];
            }

        RESPONSES   = responses;
        MAX         = max;
        MIN         = min;

    };


};