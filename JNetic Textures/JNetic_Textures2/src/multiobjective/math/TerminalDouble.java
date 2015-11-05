package multiobjective.math;


import ec.EvolutionState; 


/**
 * This class represents a double terminal from 0.0 to 1.0.
 *
 * @author Steve Bergen
 */

public class TerminalDouble extends ERCAdaptorDouble {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String name ( ) {

        return "Float";

    };

 
    /**
     * Sets the value for this double terminal.
     *
     * @param state                 Current evolution state
     * @param thread                Thread number
     * @return                      Value (random)
     */

    @Override
    public double setNumber ( final EvolutionState state, final int thread ) {

        return state.random[thread].nextFloat();

    };
 
    public void mutateERC(EvolutionState state, int thread) { 
        value = setNumber(state, thread); 
    } 

    
}
