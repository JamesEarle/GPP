package ec.app.filereader;

import ec.gp.*;
import ec.*;

/**
 * @author James Earle
 */
public class MinimumValue extends GPNode {
    
    @Override
    public String toString() { return "min"; }
    
    @Override
    public int expectedChildren() { return 0; }
    
    @Override
    public void eval(final EvolutionState state, final int thread, final GPData input,
        final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        PipelinePool pool = ((FileInputRegression)problem).pool;
        
        MinimumValuePipeline pipeline = (MinimumValuePipeline)pool.get("MinimumValuePipeline");
        DoubleData rd = ((DoubleData)input);
        
        rd.x = pipeline.getValue();
    }
    
}
