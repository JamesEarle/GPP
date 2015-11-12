package ec.app.filereader;

import ec.*;
import ec.gp.*;

/**
 * @author James Earle
 */
public class Sub extends GPNode {
    
    @Override
    public String toString() { return "-"; }

    @Override
    public int expectedChildren() { return 2; }

    @Override
    public void eval(final EvolutionState state, final int thread, final GPData input,
        final ADFStack stack, final GPIndividual individual, final Problem problem) {
        double result;
        DoubleData rd = ((DoubleData)(input));

        children[0].eval(state,thread,input,stack,individual,problem);
        result = rd.x;

        children[1].eval(state,thread,input,stack,individual,problem);
        rd.x = result - rd.x;
    }
}

