package ec.app.filereader;

import ec.*;
import ec.gp.*;
/**
 * @author JamesEarle
 */
public class Sin extends GPNode {
    
    @Override
    public String toString() { return "sin"; }
    
    @Override
    public int expectedChildren() { return 1; }
    
    @Override
    public void eval (final EvolutionState state, final int thread, final GPData input,
            final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        DoubleData rd = ((DoubleData)(input));
        
        children[0].eval(state,thread,input,stack,individual,problem);
        
        // Log of value less than 1 results in negative number, errored fitness.
        rd.x = Math.sin(rd.x);
    }
}
