package multiobjective.math;


import ec.EvolutionState;


/**
 * This class represents an int terminal from 0 to 10.
 *
 * @author Steve Bergen
 */

public class LargeTerminalInt extends ERCAdaptorInt {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String name ( ) {

        return "LInt";

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

        return state.random[thread].nextInt(11);

    };


    /**
     * Mutates an ERC of this type.
     *
     * @param state                 Current evolution state
     * @param thread                Thread number
     * @return                      Value (random)
     */

    @Override
    public void mutateERC ( EvolutionState state, int thread ) {

        value = setNumber(state, thread);

    };


};