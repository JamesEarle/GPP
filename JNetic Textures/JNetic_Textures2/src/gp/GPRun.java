package gp;


import ec.Evolve;


/**
 * Thread wrapper class used to start a single GP run.
 *
 * @author Steve Bergen
 */

public class GPRun extends Thread  {


    // Arguments for ECJ regarding parameter files
    private String[] args = {
        "-file", param.Parameters.PATH + "parameters.params",
        "-p", "stat.gather-full=true"
        };
    

    /**
     * Constructor
     */

    public GPRun ( ) {
        
    };
    

    /**
     * Starts the GP run.
     */

    @Override
    public void run ( ) {
        
        Evolve.main(args);
        
    };
    
    
};