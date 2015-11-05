package multiobjective.functions;


import ec.*;
import ec.gp.*;
import ec.util.*;

import java.awt.image.BufferedImage;
import java.awt.geom.*;
import java.awt.*;

import dataobjects.*;


/**
 * This class represents a triangle function in the function set which draws a
 * triangle with a textured background.
 *
 * @author Steve Bergen
 */

public class Triangle extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) {

        return "triangle";

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

        if ( children.length != 9 )
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

        int x[] = new int[3];
        int y[] = new int[3];
        double a;
        int r;
        Color c;

        Data rd = ((Data)(input));

        for ( int i = 0; i < 6; i += 2 ) {
            children[i].eval(state,thread,input,stack,individual,problem);
            x[i / 2] = rd.resulti;
            children[i + 1].eval(state,thread,input,stack,individual,problem);
            y[i / 2] = rd.resulti;
            x[i / 2] = (int)((float)x[i / 2] * param.Parameters.SCALE);
            y[i / 2] = (int)((float)y[i / 2] * param.Parameters.SCALE);
        }

        children[6].eval(state,thread,input,stack,individual,problem);
        r = rd.resulti;
        r = (int)((float)r * param.Parameters.SCALE);

        children[7].eval(state,thread,input,stack,individual,problem);
        a = rd.resultd;

        newPoint(x, y, 1, r);
        newPoint(x, y, 2, r);

        draw(param.Parameters.GRAPHICS, x, y, r, a, state,thread,input,stack,
                individual,problem);

    };


    /**
     * Draws the shaoe object with a textured background. The texture portion
     * of the tree must be calculated first.
     *
     * @param g2d                   Graphics object
     * @param Xc                    X coordinate
     * @param Yc                    Y coordinate
     * @param Rc                    Radius
     * @param a                     Alpha value
     * @param state                 Evolution state (current)
     * @param thread                Thread number
     * @param input                 GPData storage object
     * @param stack                 ADF stack
     * @param individual            Current individual
     * @param problem               GP problem object
     */

    public void draw ( Graphics2D g2d, int[] Xc, int[] Yc, int Rc, double a,
                    final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem ) {

        Data rd = ((Data)(input));
        java.awt.Polygon polygon = new java.awt.Polygon();
        polygon.addPoint(Xc[0], Yc[0]);
        polygon.addPoint(Xc[1], Yc[1]);
        polygon.addPoint(Xc[2], Yc[2]);

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

                children[8].eval(state,thread,input,stack,individual,problem);
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
        g2d.fill(polygon);

    };


    /**
     * Calculates a new point for the current x and y, if it is outside the
     * bounds of radius r.
     *
     * @param x                 X coordinates
     * @param y                 Y coordinates
     * @param id                Index of coordinates to compare to [0]
     * @param r                 Radius
     */

    private void newPoint ( int[] x, int[] y, int id, double r ) {

        double radius = distance(x[0], y[0], x[id], y[id]);

        if ( radius > r ) {

            radius = r / radius;

            if ( x[id] > x[0] )
                x[id] = x[0] + (int)((double)(x[id] - x[0]) * radius);
            else x[id] = x[0] - (int)((double)(x[0] - x[id]) * radius);

            if ( y[id] > y[0] )
                y[id] = y[0] + (int)((double)(y[id] - y[0]) * radius);
            else y[id] = y[0] - (int)((double)(y[0] - y[id]) * radius);

        }

        radius = distance(x[0], y[0], x[id], y[id]);
        // Do nothing if radius is within limit r

    };


    /**
     * Returns the distance between two points
     * @param x                 X1
     * @param y                 Y1
     * @param x2                X2
     * @param y2                Y2
     * @return                  Distance value
     */
    
    private double distance ( int x, int y, int x2, int y2 ) {

        double xdiff = x - x2;
        double ydiff = y - y2;

        double d = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

        return d;

    };


};