package param;


/**
 * This class stores statistics about the fitnesses of the population. It
 * represents a single generation.
 *
 * @author Steve Bergen
 */

public class FitnessStat {


    public double STATS[][];
    public double ESTATS[][];


    /**
     * Constructor.
     *
     * @param STATS             Fitness statistics (raw)
     * @param ESTATS            Error fitness statistics
     */
    
    public FitnessStat ( double[][] STATS, double[][] ESTATS ) {
        
        this.STATS = STATS;
        this.ESTATS = ESTATS;
        
    };


};