package ec.app.filereader;

import ec.gp.*;
import ec.*;

/**
 * @author James Earle
 */
public class High extends GPNode {
    
    @Override
    public String toString() { return "high"; }
    
    @Override
    public int expectedChildren() { return 0; }
    
    @Override
    public void eval(final EvolutionState state, final int thread, final GPData input,
        final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        PipelinePool pool = ((FileInputRegression)problem).pool;        
        HighPipeline pipeline = (HighPipeline)pool.get("HighPipeline");

        DoubleData rd = ((DoubleData)input);
        rd.x = pipeline.getValue();
    }
    
}
