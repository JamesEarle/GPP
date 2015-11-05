package multiobjective.math;


import ec.EvolutionState; 


/**
 * This class represents an int terminal from 0 to image height or width.
 *
 * @author Steve Bergen
 */

public class TerminalInt extends ERCAdaptorInt {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String name ( ) {

        return "Int";

    };


    /**
     * Sets the value for this int terminal.
     *
     * @param state                 Current evolution state
     * @param thread                Thread number
     * @return                      Value (random)
     */

    @Override
    public double setNumber ( final EvolutionState state, final int thread ) {

        if ( param.Parameters.IMAGEH > param.Parameters.IMAGEW )
            return state.random[thread].nextInt(param.Parameters.IMAGEH);
        else
            return state.random[thread].nextInt(param.Parameters.IMAGEW);

    };
 
    public void mutateERC(EvolutionState state, int thread) { 
        value = setNumber(state, thread); 
    } 

    
}
