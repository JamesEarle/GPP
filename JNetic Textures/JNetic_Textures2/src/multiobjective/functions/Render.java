package multiobjective.functions;


import ec.*;
import ec.gp.*;
import ec.util.*;

import java.awt.image.BufferedImage;
import java.awt.geom.*;
import java.awt.*;

import dataobjects.*;


/**
 * This class represents a rendering function which has two children; and RGB
 * value and a shape loop. The RGB value is for background color, and the shapes
 * are rendered over the background.
 *
 * @author Steve Bergen
 */

public class Render extends GPNode {


    /**
     * Returns the string representation of this node.
     *
     * @return                  String value
     */

    @Override
    public String toString ( ) { 
        
        return "render"; 
    
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

        draw(param.Parameters.GRAPHICS, state,thread,input,stack,individual,
                problem);
        
        children[1].eval(state,thread,input,stack,individual,problem);

        param.Parameters.GRAPHICS.dispose();
        
    };
    

    /**
     * This function is used to draw the rgb component to the background by
     * parsing the tree for every pixel (x, y).
     *
     * @param g2d                   Graphics object
     * @param state                 Evolution state (current)
     * @param thread                Thread number
     * @param input                 GPData storage object
     * @param stack                 ADF stack
     * @param individual            Current individual
     * @param problem               GP problem object
     */

    public void draw ( Graphics2D g2d,
                     final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem ) {
        
        Data rd = ((Data)(input));
        BufferedImage image = new BufferedImage((int)param.Parameters.IMAGEW,
                (int)param.Parameters.IMAGEH, BufferedImage.TYPE_INT_RGB);
                
        param.Parameters.IMAGE    = image;
        
        if ( param.Parameters.SVG == null )
            param.Parameters.GRAPHICS = image.createGraphics();
        else
            param.Parameters.GRAPHICS = param.Parameters.SVG;
        
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
                
                children[0].eval(state,thread,input,stack,individual,problem);
                Color c = rd.color;

                image.setRGB(X, Y, c.getRGB());
                
                y = y + dy;
                Y++;
                    
            }
            
            x = x + dx;
            X++;
            
        }

        if ( param.Parameters.SVG != null ) {
            Rectangle2D rect = new Rectangle2D.Double(0, 0,
                    (int)param.Parameters.IMAGEW, (int)param.Parameters.IMAGEH);
            Rectangle2D tr = new Rectangle2D.Double(0, 0,
            image.getWidth(), image.getHeight());

            TexturePaint tp = new TexturePaint(image, tr);

            param.Parameters.SVG.setPaint(tp);
            param.Parameters.SVG.fill(rect);
        }
        
    };
    
    
};