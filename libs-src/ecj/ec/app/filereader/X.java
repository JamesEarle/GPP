package ec.app.filereader;

import ec.gp.*;
import ec.*;

/**
 * @author James Earle
 */
public class X extends GPNode {
    
    @Override
    public String toString() { return "x"; }
    
    @Override
    public int expectedChildren() { return 0; }
    
    @Override
    public void eval(final EvolutionState state, final int thread, final GPData input,
        final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        DoubleData rd = ((DoubleData)input);
        rd.x = ((FileInputRegression)problem).currentX;
    }
    
}
