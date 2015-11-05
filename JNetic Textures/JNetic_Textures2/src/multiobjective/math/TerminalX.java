package multiobjective.math;


import ec.EvolutionState;


/**
 * This class represents an X terminal for coordinates from 0 to width.
 *
 * @author Steve Bergen
 */

public class TerminalX extends ERCAdaptorInt {


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

        return state.random[thread].nextInt((int)param.Parameters.IMAGEW);

    };

    public void mutateERC(EvolutionState state, int thread) {
        value = setNumber(state, thread);
    }


}
