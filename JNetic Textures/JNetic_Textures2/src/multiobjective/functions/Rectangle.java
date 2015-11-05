package multiobjective.functions;


import ec.*;
import ec.gp.*;
import ec.util.*;

import java.awt.image.BufferedImage;
import java.awt.geom.*;
import java.awt.*;

import dataobjects.*;


/**
 * This class represents a rectangle function in the function set which draws a
 * rectangle with a textured background.
 *
 * @author Steve Bergen
 */

public class Rectangle extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) {

        return "rectangle";

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

        if ( children.length != 6 )
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

        int x, y, w, h;
        double a;
        Color c;

        Data rd = ((Data)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        x = rd.resulti;

        children[1].eval(state,thread,input,stack,individual,problem);
        y = rd.resulti;

        children[2].eval(state,thread,input,stack,individual,problem);
        w = rd.resulti;

        children[3].eval(state,thread,input,stack,individual,problem);
        h = rd.resulti;

        children[4].eval(state,thread,input,stack,individual,problem);
        a = rd.resultd;

        x = x - (w / 2);
        y = y - (h / 2);

        x = (int)((float)x * param.Parameters.SCALE);
        y = (int)((float)y * param.Parameters.SCALE);
        w = (int)((float)w * param.Parameters.SCALE);
        h = (int)((float)h * param.Parameters.SCALE);

        draw(param.Parameters.GRAPHICS, x, y, w, h, a, state,thread,input,stack,
                individual,problem);

    };


    /**
     * Draws the shaoe object with a textured background. The texture portion
     * of the tree must be calculated first.
     *
     * @param g2d                   Graphics object
     * @param Xc                    X coordinate
     * @param Yc                    Y coordinate
     * @param W                     Width
     * @param H                     Height
     * @param a                     Alpha value
     * @param state                 Evolution state (current)
     * @param thread                Thread number
     * @param input                 GPData storage object
     * @param stack                 ADF stack
     * @param individual            Current individual
     * @param problem               GP problem object
     */

    public void draw ( Graphics2D g2d, int Xc, int Yc, int W, int H, double a,
                    final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem ) {

        Data rd = ((Data)(input));
        Rectangle2D rect = new Rectangle2D.Double(Xc, Yc, W, H);

        BufferedImage texture = new BufferedImage((int)param.Parameters.IMAGEW,
                (int)param.Parameters.IMAGEH, BufferedImage.TYPE_INT_ARGB);

        float x = param.Parameters.STARTU;
        float y = param.Parameters.STARTV;

        float dx    = Math.abs(param.Parameters.STARTU - param.Parameters.ENDU);
        dx          = dx / (float)param.Parameters.IMAGEW;

        float dy    = Math.abs(param.Parameters.STARTV - param.Parameters.ENDV);
        dy          = dy / (float)param.Parameters.IMAGEH;

        int X = 0;
        int Y = 0;

        while ( X < param.Parameters.IMAGEW ) {

            y = param.Parameters.STARTV;
            Y = 0;

            while ( Y < param.Parameters.IMAGEH ) {

                param.Parameters.PROBLEM.currentX = x;
                param.Parameters.PROBLEM.currentY = y;

                children[5].eval(state,thread,input,stack,individual,problem);
                Color c = rd.color;
                if ( param.Parameters.ALPHA )
                    c = new Color(c.getRed(), c.getGreen(), c.getBlue(),
                            (int)(a * 255));
                texture.setRGB(X, Y, c.getRGB());

                y = y + dy;
                Y++;

            }

            x = x + dx;
            X++;

        }

        Rectangle2D tr = new Rectangle2D.Double(0, 0,
        texture.getWidth(), texture.getHeight());

        TexturePaint tp = new TexturePaint(texture, tr);

        g2d.setPaint(tp);
        g2d.fill(rect);

    };


};