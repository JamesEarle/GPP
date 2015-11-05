package multiobjective.texture;


import ec.*;
import ec.gp.*;
import ec.util.*;

import org.texgen.textures.AbstractTexture;
import org.texgen.utils.RGBAColor;

import java.awt.*;
import dataobjects.*;


/**
 * This class converts a texture to an rgb value as a function in the function
 * set.
 *
 * @author Steve Bergen
 */

public class TexturetoRGB extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) {

        return "texturetoRGB";

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

        double x = param.Parameters.PROBLEM.currentX;
        double y = param.Parameters.PROBLEM.currentY;

        AbstractTexture t;
        RGBAColor       c = new RGBAColor();

        Data rd = ((Data)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        t = rd.texture;

        t.getColor(x, y, c);

        rd.color = new Color(c.getRed(), c.getGreen(), c.getBlue());

    };


};