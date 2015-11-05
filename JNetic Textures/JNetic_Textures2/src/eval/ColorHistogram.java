package eval;


import java.awt.Color;
import java.awt.image.*;


/**
 * This class is used both to store color histogram information and compare
 * its own values to that of other color histograms.
 *
 * @author Steve Bergen
 */

public class ColorHistogram {

    
    public static final double      MAX     = Math.sqrt(255 * 3);
    
    public int[]                    data    = null;
    public BufferedImage            img     = null;
    

    /**
     * Constructor which takes in an image as source for initial calculations.
     *
     * @param img           Source image
     */

    public ColorHistogram ( BufferedImage img ) {
        
        this.img = img;
        
        data    = new int[512];
        
        for ( int i = 0; i < 512; i++ )
            data[i] = 0;

        int start = 0;
        int inc = img.getHeight() / param.Parameters.THREADS;

        HistThread fit[] = new HistThread[param.Parameters.THREADS - 1];

        for (int i = 0; i < param.Parameters.THREADS - 1; i++) {
            fit[i] = new HistThread(img, start, start + inc, data.clone());
            start = start + inc;
            fit[i].start();
        }

       quantize(img, start, img.getHeight(), data);

        // Check for all finished threads, and quit when all are finished
        while (true) {
            boolean dead = true;
            for (int i = 0; i < param.Parameters.THREADS - 1; i++)
                if (fit[i].isAlive()) dead = false;
            if (dead) break;
        }

       for ( int i = 0; i < 512; i++ )
          for (int j = 0; j < param.Parameters.THREADS - 1; j++)
              data[i] += fit[j].data[i];

        
    };
    

    /**
     * Method called to quantize an image to 512 colors, and counts the
     * occurrences of each color in the image, storing them in an array.
     *
     * @param img           Source image
     */

    public static void quantize ( BufferedImage img, int y0, int yend,
            int[] d ) {
        
        for ( int x = 0; x < img.getWidth(); x++ )
            for ( int y = y0; y < yend; y++ )
                d[getIndex(new Color(img.getRGB(x, y)))]++;
        
    };
    

    /**
     * Returns an integer representing an index from 0 to 511, which is the
     * value of the color passed in.
     *
     * @param c             Color to be quantized
     * @return              Quantized integer
     */

    private static int getIndex ( Color c ) {
        
        int index = 0;

        index   = (c.getRed() / 32) * 64;
        index   += (c.getGreen() / 32) * 8;
        index   += c.getBlue() / 32;
        
        return index;
        
    };
    

    /**
     * Calculates the color index distance between two histograms at a single
     * index i.
     *
     * @param hist          Second histogram
     * @param i             Index
     * @return              Absolute distance between values.
     */

    private double colorDistanceI ( ColorHistogram hist, int i ) {
        
        return Math.abs(hist.data[i] - this.data[i]);
        
    };
    

    /**
     * Calculates the color similarity between two colors.
     *
     * @param a             First color
     * @param b             Second color
     * @return              Color similarity value
     */

    private double colorSimilarity ( Color a, Color b ) {
        
        double R    = Math.pow(a.getRed() - b.getRed(), 2);
        double G    = Math.pow(a.getGreen() - b.getGreen(), 2);
        double B    = Math.pow(a.getBlue() - b.getBlue(), 2);
        
        double t    = Math.sqrt(R + G + B);
        
        return Math.pow(1.0 - (t / MAX), 2);
        
    };
    

    /**
     * Converts an integer from 0..511 to a color (reverse-quantization).
     *
     * @param i             Integer to convert
     * @return              Color value of integer
     */

    private static Color getColorI ( int i ) {
      
        int R = (i / 64) * 32;
        int G = ((i % 64) / 8) * 32;
        int B = ((i % 64) % 8) * 32;
        
        return new Color(R, G, B);
        
    };
    

    /**
     * Returns the color distance between the histograms summed with absolute
     * color distance between two histogram images. An expensive call.
     *
     * @param hist          Second histogram
     * @return              Color distance
     */

    public double colorDS ( ColorHistogram hist ) {
        
        double total = 0.0;
        
        for ( int i = 0; i < 512; i++ )
            for ( int j = 0; j < 512; j++ ) 
                total += colorDistanceI(hist, i) * 
                        colorSimilarity(getColorI(i), getColorI(j)) * 
                        colorDistanceI(hist, j);
        
        return total;
            
    };
    

    /** Returns the color index difference between two histograms. Compares
     * values stored in the arrays.
     *
     * @param hist          Second histogram
     * @return              Color distance
     */

    public double colorHistDistance ( ColorHistogram hist ) {
        
        double total = 0.0;
        
        for ( int i = 0; i < 512; i++ ) {
            total += colorDistanceI(hist, i);
        }
        
        return total;
        
    };
    
    
};


class HistThread extends Thread {


    public int[]            data;
    public int              ystart, yend;
    private BufferedImage   img;


    public HistThread ( BufferedImage img, int ystart, int yend, int[] data ) {

        this.img        = img;
        this.ystart     = ystart;
        this.yend       = yend;
        this.data       = data;

    };


    @Override
    public void run ( ) {

        ColorHistogram.quantize(img, ystart, yend, data);

    };


};