package ec.app.filereader;

import ec.*;
import ec.gp.*;
/**
 *
 * @author JamesEarle
 */
public class Div extends GPNode {
    
    @Override
    public String toString() { return "/"; }
    
    @Override
    public int expectedChildren() { return 2; }
    
    @Override
    public void eval (final EvolutionState state,
                     final int thread,
                     final GPData input,
                     final ADFStack stack,
                     final GPIndividual individual,
                     final Problem problem) {

        DoubleData rd = ((DoubleData)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        double result = rd.x;

        children[1].eval(state,thread,input,stack,individual,problem);
        
        // Protected division for 0 denominator
        if (rd.x != 0) {
            rd.x = result / rd.x;
        }
    }
}
