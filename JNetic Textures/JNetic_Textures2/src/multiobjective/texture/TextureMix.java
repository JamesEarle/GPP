package multiobjective.texture;


import ec.*;
import ec.gp.*;
import ec.util.*;

import org.texgen.textures.composite.MixTexture;
import org.texgen.textures.AbstractTexture;
import org.texgen.utils.RGBAColor;

import dataobjects.*;


/**
 * This class represents an texture mixer function in the function set. It
 * has two children, textures, and it mixes the two based upon
 * their alpha values.
 *
 * @author Steve Bergen
 */

public class TextureMix extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) {

        return "texturemix";

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

        if ( children.length != 2 )
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

        AbstractTexture t1, t2;
        RGBAColor       c;

        Data rd = ((Data)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        t1 = rd.texture;

        children[1].eval(state,thread,input,stack,individual,problem);
        t2 = rd.texture;

        rd.texture = new MixTexture(t1, t2);

    };


};