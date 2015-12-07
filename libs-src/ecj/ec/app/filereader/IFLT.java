package ec.app.filereader;

import ec.*;
import ec.gp.*;
/**
 * @author JamesEarle
 */
public class IFLT extends GPNode {
    
    @Override
    public String toString() { return "IFLT"; }
    
    @Override
    public int expectedChildren() { return 4; }
    
    @Override
    public void eval (final EvolutionState state, final int thread, final GPData input,
            final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        DoubleData rd = ((DoubleData)(input));
        
        children[0].eval(state,thread,input,stack,individual,problem);
        double a = rd.x;
        
        children[1].eval(state,thread,input,stack,individual,problem);
        double b = rd.x;
        
        if(a < b) {
            children[2].eval(state,thread,input,stack,individual,problem);
        } else {
            children[3].eval(state,thread,input,stack,individual,problem);
        }
    }
}
