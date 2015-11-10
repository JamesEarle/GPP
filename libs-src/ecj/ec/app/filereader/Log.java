package ec.app.filereader;

import ec.*;
import ec.gp.*;
/**
 *
 * @author JamesEarle
 */
public class Log extends GPNode {
    
    @Override
    public String toString() { return "log"; }
    
    @Override
    public int expectedChildren() { return 1; }
    
    @Override
    public void eval (final EvolutionState state, final int thread, final GPData input,
            final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        DoubleData rd = ((DoubleData)(input));
        
        children[0].eval(state,thread,input,stack,individual,problem);
        
        // Log of value less than 1 results in negative number, errored fitness.
        if(1.0 <= rd.x) {
            rd.x = Math.log(rd.x);
        }
    }
}
