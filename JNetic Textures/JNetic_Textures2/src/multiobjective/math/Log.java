package multiobjective.math;


import dataobjects.Data;
import ec.*;
import ec.gp.*;
import ec.util.*;


/**
 * This class represents a logarithm function in the function set.
 *
 * @author Steve Bergen
 */

public class Log extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) { 
        
        return "log"; 
    
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
    public void checkConstraints (final EvolutionState state,
                                 final int tree,
                                 final GPIndividual typicalIndividual,
                                 final Parameter individualBase) {
        
        super.checkConstraints(state,tree,typicalIndividual,individualBase);
        
        if (children.length!=1)
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

    public void eval  (final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem) {
        
        Data rd = ((Data)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        rd.resultd = Math.log(rd.resultd);
        
    };
    
    
};