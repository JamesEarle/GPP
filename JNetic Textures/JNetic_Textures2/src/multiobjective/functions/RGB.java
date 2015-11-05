package multiobjective.functions;


import ec.*;
import ec.gp.*;
import ec.util.*;

import java.awt.*;

import dataobjects.*;


/**
 * This class represents an RGB function in the function set which contains a
 * tree returning R, G and B values.
 *
 * @author Steve Bergen
 */

public class RGB extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) { 

        return "rgb"; 
    
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
        
        if ( children.length != 3 )
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
        
        float r, g, b;

        Data rd = ((Data)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        r = (float)rd.resultd;

        children[1].eval(state,thread,input,stack,individual,problem);
        g = (float)rd.resultd;
        
        children[2].eval(state,thread,input,stack,individual,problem);
        b = (float)rd.resultd;
        
        if ( r < 0 ) r = 0;
        else if ( r > 1.0f ) r = 1.0f;
        
        if ( g < 0 ) g = 0;
        else if ( g > 1.0f ) g = 1.0f;
        
        if ( b < 0 ) b = 0;
        else if ( b > 1.0f ) b = 1.0f;
        
        rd.color = new Color(r, g, b);
        
    };
    
    
};