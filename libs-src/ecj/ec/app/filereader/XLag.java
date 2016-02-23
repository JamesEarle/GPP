package ec.app.filereader;

import ec.gp.*;
import ec.*;

/**
 * @author James Earle
 */
public class XLag extends GPNode {
    
    @Override
    public String toString() { return "x_t"; }
    
    @Override
    public int expectedChildren() { return 0; }
    
    @Override
    public void eval(final EvolutionState state, final int thread, final GPData input,
        final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
//        LagSurrogate surrogate = ((FileInputRegression)problem).surrogate;
        DoubleData rd = ((DoubleData)input);
        
//        rd.x = surrogate.getLagResult();
    }
    
}
