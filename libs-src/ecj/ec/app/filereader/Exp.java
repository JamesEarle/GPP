package ec.app.filereader;

import ec.*;
import ec.gp.*;
/**
 * @author James Earle
 */
public class Exp extends GPNode {
    
    @Override
    public String toString() { return "exp"; }
    
    @Override
    public int expectedChildren() { return 1; }
    
    @Override
    public void eval (final EvolutionState state, final int thread, final GPData input,
            final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        DoubleData rd = ((DoubleData)(input));
        
        children[0].eval(state,thread,input,stack,individual,problem);
        
        double val = Math.exp(rd.x);
        
//        System.out.println("exp(" + rd.x + ") = " + val);
//        System.out.println(Double.isInfinite(val));
        
        if(!Double.isInfinite(val) && val != 0.0) {
            rd.x = Math.exp(rd.x);
            //System.out.println(rd.x);
        } //no else
    }
}
