package multiobjective.texture;


import ec.*;
import ec.gp.*;
import ec.util.*;

import org.texgen.textures.color.RadialGradient;
import org.texgen.utils.ColorGradient;
import org.texgen.textures.uv.UVNoiseTranslate;
import org.texgen.textures.*;
import org.texgen.textures.AbstractTexture;
import org.texgen.utils.RGBAColor;

import dataobjects.*;


/**
 * This class represents a fire function in the function set.
 *
 * @author Steve Bergen
 */

public class Fire extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) {

        return "fireball";

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

        if ( children.length != 5 )
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

        int a, b, c, d;

        children[0].eval(state,thread,input,stack,individual,problem);
        a = rd.resulti;

        children[1].eval(state,thread,input,stack,individual,problem);
        b = rd.resulti;

        children[2].eval(state,thread,input,stack,individual,problem);
        c = rd.resulti;

        children[3].eval(state,thread,input,stack,individual,problem);
        d = rd.resulti;

        children[4].eval(state,thread,input,stack,individual,problem);

        rd.texture = new FireballClass(a, b, c, d, rd.resultd);

    };


};


/**
 * This is a storage class which is necessary to override the getColor method.
 *
 * @author Steve Bergen
 */

class FireballClass extends AbstractTexture {


    private Texture     signal;
    private double      r;


    /**
     * Constructor which creates a fireball object.
     *
     * @param scale                 Scale value
     * @param speed                 Speed value
     * @param oct                   Octaves
     * @param size                  Size value
     * @param r                     Alpha value
     */

    public FireballClass ( int scale, int speed, int oct, int size, double r ) {

        super();

        signal = new RadialGradient(buildFireGradient());
        signal = new UVNoiseTranslate(signal, speed + 1, oct + 1, size + 1);

        this.r = r;

    };


    /**
     * Creates a fire gradient.
     *
     * @return                      Fire gradient
     */

    protected static ColorGradient buildFireGradient() {

        ColorGradient gradient = ColorGradient.buildFire();

        gradient.add(RGBAColor.black());
        gradient.add(RGBAColor.black());
        gradient.add(RGBAColor.black());
        gradient.add(RGBAColor.black());

        return gradient;

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

        signal.getColor(u, v, value);
        value.setAlpha(r);

    };


};