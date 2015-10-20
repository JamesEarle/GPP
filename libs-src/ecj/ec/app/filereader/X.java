/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.app.filereader;

import ec.gp.*;
import ec.*;

/**
 *
 * @author James Earle
 */
public class X extends GPNode {
    
    @Override
    public String toString() { return "x"; }
    
    @Override
    public int expectedChildren() { return 0; }
    
    public void eval(final EvolutionState state, final int thread, final GPData input,
        final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        DoubleData rd = ((DoubleData)input);
        
        rd.x = ((FileInputRegression)problem).currentX;
    }
    
}
