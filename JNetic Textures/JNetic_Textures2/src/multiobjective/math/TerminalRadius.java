package multiobjective.math;


import ec.EvolutionState; 


/**
 * This class represents an int terminal from 0.0 to radius.
 *
 * @author Steve Bergen
 */

public class TerminalRadius extends ERCAdaptorInt {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String name ( ) {

        return "radius";

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

        return state.random[thread].nextInt(param.FunctionSet.RADIUS);

    };
 
    public void mutateERC(EvolutionState state, int thread) { 
        value = setNumber(state, thread); 
    } 

    
}
