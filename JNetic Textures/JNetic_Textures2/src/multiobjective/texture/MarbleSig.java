package multiobjective.texture;


import ec.*;
import ec.gp.*;
import ec.util.*;

import org.texgen.signals.*;
import org.texgen.textures.*;
import org.texgen.textures.AbstractTexture;
import org.texgen.utils.RGBAColor;

import dataobjects.*;


/**
 * This class represents a marble function in the function set.
 *
 * @author Steve Bergen
 */

public class MarbleSig extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) {

        return "marblesignal";

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

        rd.texture = new MarbleSigClass(a, b, c, d, rd.resultd);

    };


};


/**
 * This is a storage class which is necessary to override the getColor method.
 *
 * @author Steve Bergen
 */

class MarbleSigClass extends AbstractTexture {


    private ChannelSignal   signal  = new MarbleSignal(0,0,8,5,5,10);
    private double          r;


    /**
     * Constructor which creates a marble object.
     *
     * @param scale                 Scale value
     * @param speed                 Speed value
     * @param oct                   Octaves
     * @param size                  Size value
     * @param r                     Alpha value
     */

    public MarbleSigClass ( int scale, int speed, int oct, int size, double r ) {

        super();

        signal = new MarbleSignal(0,0,scale,speed,oct,size);
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

        double val = signal.getValue(u, v);

        if ( val > 1.0 ) val = 1.0;
        if ( val < 0.0 ) val = 0.0;

        value.setColor(val,val,val,r);

    };


};