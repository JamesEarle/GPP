package param;


import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import gp.*;
import interfaces.*;
import java.util.List;
import java.util.LinkedList;
import fitness.*;
import eval.*;

import org.apache.batik.svggen.*;


/**
 * This class holds the basic parameters used in the program run, such as image
 * information and GP objects.
 *
 * @author Steve Bergen
 */

public class Parameters {

    
    public static Frame                     FRAME       = null;
    public static SimpleEvolutionState      STATE       = null;
    public static TextureProblem            PROBLEM     = null;
    
    public static float                     STARTU      = 0.0f;
    public static float                     STARTV      = 0.0f;
    public static float                     ENDU        = 1.0f;
    public static float                     ENDV        = 1.0f;
    
    public static int                       IMAGEW      = 200;
    public static int                       IMAGEH      = 200;

    public static float                     SCALE       = 1.0f;
    public static SVGGraphics2D             SVG         = null;

    public static BufferedImage             SOURCE      = null;
    public static ColorHistogram            SOURCEHIST  = null;

    public static int                       PIXEL       = 2;
    public static BufferedImage             IMAGE       = null;
    public static Graphics2D                GRAPHICS    = null;
    public static boolean                   ALPHA       = true;

    public static List                      STATS       = null;

    public static int                       TRIES       = 20;


    // -------------------------------------------------------------------------
    // --------------------- FITNESS EVALUATION --------------------------------
    // -------------------------------------------------------------------------

    public static List                      FITNESS_FUNCTIONS;

    public static Mean                      FITNESS_MEAN;


    // MULTITHREADING MESSES WITH IND. IMAGE FITNESSES!!!
    // THREAD IMAGE FCT INSTEAD (color matching like in JNetic)!
    public static int                       THREADS     = 1;
    public static int                       SEED        = 2346;
    public static String                    PATH        = "";


    /**
     * This method initializes the fitness functions and a few other key
     * variables. It must be called prior to the GP run, but only after a source
     * image has been selected.
     */

    public static void init ( ) {

        SOURCEHIST          = new ColorHistogram(SOURCE);

        IMAGEW              = SOURCE.getWidth();
        IMAGEH              = SOURCE.getHeight();


        FITNESS_FUNCTIONS = new LinkedList<FitnessFunction>();

        FITNESS_FUNCTIONS.add(new Mean(3.75));
        FITNESS_FUNCTIONS.add(new StandardDeviation(0.75));

        FunctionSet.init();

    };


    /**
     * This function is called to reset the STATS list (new gp run).
     */

    public static void reset ( ) {

        STATS = new LinkedList<FitnessStat>();
        
    };


};