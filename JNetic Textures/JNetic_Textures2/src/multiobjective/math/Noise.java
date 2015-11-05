package multiobjective.math;


import ec.*;
import ec.gp.*;
import ec.util.*;

import org.texgen.utils.PerlinNoise;

import dataobjects.*;


/**
 * This class represents a noise function in the function set.
 *
 * @author Steve Bergen
 */

public class Noise extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) {

        return "noise";

    };


    /**
     * Checks the number of children of this node are accurate.
     *
     * @param state                 Evolution state (current)
     * @param tree                  Tree number
     * @param typicalIndividual     Individual to test
     * @param individualBase        Parameter set
     */

    @Override
    public void checkConstraints ( final EvolutionState state,
                                 final int tree,
                                 final GPIndividual typicalIndividual,
                                 final Parameter individualBase ) {

        super.checkConstraints(state,tree,typicalIndividual,individualBase);

        if ( children.length != 1 )
            state.output.error("Incorrect number of children for node " +
                               toStringForError() + " at " +
                               individualBase);

    };


    /**
     * Evaluates this node.
     *
     * @param state                 Evolution state (current)
     * @param thread                Thread number
     * @param input                 GPData storage object
     * @param stack                 ADF stack
     * @param individual            Current individual
     * @param problem               GP problem object
     */

    public void eval (final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem) {

        Data rd = ((Data)(input));

        PerlinNoise p = new PerlinNoise();

        children[0].eval(state,thread,input,stack,individual,problem);
        int length = (int)(rd.resultd * 6.0);
        rd.resultd = p.noise2(param.Parameters.PROBLEM.currentX*length,
                param.Parameters.PROBLEM.currentY*length);

    };

};