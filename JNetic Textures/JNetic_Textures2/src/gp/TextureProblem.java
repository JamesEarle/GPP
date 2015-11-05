package gp;


import ec.util.*;
import ec.*;
import ec.gp.*;
import ec.simple.*;
import dataobjects.*;

import java.awt.image.BufferedImage;
import param.Parameters;
import ec.gp.GPProblem;

/* This class is an extension of the GPProblem class which is used to store and
 * set tree variables X and Y, as well as in fitness calculation.
 *
 * @author Steve Bergen
 */

public class TextureProblem extends GPProblem implements SimpleProblemForm {


    public static final String  P_DATA = "data";

    public BufferedImage        src;
    
    public double               currentX;
    public double               currentY;
    
    public Data                 input;


    /**
     * Clones this object.
     *
     * @return                  Cloned object
     */

    @Override
    public Object clone ( ) {

        TextureProblem newobj = (TextureProblem) (super.clone());
        newobj.input = (Data)(input.clone());
        return newobj;

    };


    /**
     * Called to setup this Problem object.
     *
     * @param state             Evolution state (current)
     * @param base              Parameter set
     */

    @Override
    public void setup ( final EvolutionState state, final Parameter base ) {
        
        // very important, remember this
        super.setup(state,base);
        
        src = param.Parameters.SOURCE;
        param.Parameters.PROBLEM = this;
        
        // set up our input -- don't want to use the default base, it's unsafe here
        input = (Data) state.parameters.getInstanceForParameterEq(
            base.push(P_DATA), null, Data.class);
        input.setup(state,base.push(P_DATA));
        
    };
   
    

    /**
     * Called to evaluate an individual (kept empty!).
     *
     * @param state             Evolution state
     * @param ind               Individual tree
     * @param subpopulation     Subpopulation value
     * @param threadnum         Thead value
     */

    @Override
    public void evaluate ( final EvolutionState state, final Individual ind,
                         final int subpopulation, final int threadnum ) {

    };


    /**
     * Function called to create the image produced by a tree. DO NOT CALL
     * THIS FUNCTION IN MULTIPLE THREADS!.
     *
     * @param state             Evolution state
     * @param ind               Individual tree
     * @param subpopulation     Subpopulation value
     * @param threadnum         Thead value
     * @return                  Image created by tree
     */
    
    public BufferedImage getData ( final EvolutionState state,
            final Individual ind, final int subpopulation, final int threadnum){

                ((GPIndividual)ind).trees[0].child.eval(
                        state,threadnum,input,stack,((GPIndividual)ind),this);

                return Parameters.IMAGE;
                
    };


};