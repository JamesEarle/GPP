package multiobjective.texture;


import ec.*;
import ec.gp.*;
import ec.util.*;

import org.texgen.textures.AbstractTexture;
import org.texgen.utils.RGBAColor;
import dataobjects.*;


/**
 * This class represents a mandelbrot function in the function set.
 *
 * @author Steve Bergen
 */

public class Mandelbrot extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) {

        return "mandelbrot";

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

        Data rd = ((Data)(input));

        double a, b;

        children[0].eval(state,thread,input,stack,individual,problem);
        a = rd.resultd;

        children[1].eval(state,thread,input,stack,individual,problem);
        b = rd.resultd;

        children[2].eval(state,thread,input,stack,individual,problem);

        rd.texture = new MandelbrotClass(a, b, rd.resultd);

    };


};


/**
 * This is a storage class which is necessary to override the getColor method.
 *
 * @author Steve Bergen
 */

class MandelbrotClass extends AbstractTexture {


    private org.texgen.textures.pattern.Mandelbrot      texture;

    private static double[]     values = {0.35, 0.35, 0.3507, 0.3507};
    private double              r;


    /**
     * Constructor which creates a mandelbrot object.
     *
     * @param a                     Start value
     * @param b                     End value
     * @param r                     Alpha value
     */

    public MandelbrotClass ( double a, double b, double r ) {

        super();

        values[0] = a;
        values[1] = a;
        values[2] = b;
        values[3] = b;

        texture = new org.texgen.textures.pattern.Mandelbrot(values[0],
                values[1], values[2], values[3]);
        //texture = new org.texgen.textures.pattern.Mandelbrot(a, a, b, b);

        this.r = r;

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

        texture.getColor(u, v, value);
        value.setAlpha(r);

    };


};