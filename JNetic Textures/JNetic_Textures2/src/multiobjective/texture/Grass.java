package multiobjective.texture;


import ec.*;
import ec.gp.*;
import ec.util.*;

import org.texgen.textures.AbstractTexture;
import org.texgen.utils.RGBAColor;
import dataobjects.*;


/**
 * This class represents a grass function in the function set.
 *
 * @author Steve Bergen
 */

public class Grass extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) {

        return "grass";

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

        AbstractTexture t1;

        Data rd = ((Data)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        double a = rd.resultd;

        rd.texture = new GrassClass(a);

    };


};


/**
 * This is a storage class which is necessary to override the getColor method.
 *
 * @author Steve Bergen
 */

class GrassClass extends AbstractTexture {


    double a;


    /**
     * Constructor which creates a grass object.
     *
     * @param r                     Alpha value
     */

    public GrassClass ( double a ) {

        super();

        this.a = a;

    };


    /**
     * Returns the color at supplied U and V values.
     *
     * @param u                     U value
     * @param v                     V value
     * @param value                 Color object
     */

    @Override
    public void getColor(double u, double v, RGBAColor value) {

        org.texgen.utils.TerrainTexture.getGrass().getColor(u, v, value);
        value.setAlpha(a);

    };


};