package ec.app.filereader;

import ec.gp.*;
import ec.*;

/**
 * @author James Earle
 */
public class Skew extends GPNode {
    
    @Override
    public String toString() { return "skew"; }
    
    @Override
    public int expectedChildren() { return 0; }
    
    @Override
    public void eval(final EvolutionState state, final int thread, final GPData input,
        final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        PipelinePool pool = ((FileInputRegression)problem).pool;
        SkewPipeline pipeline = (SkewPipeline)pool.get("SkewPipeline");
        
        DoubleData rd = ((DoubleData)input);
        
        rd.x = pipeline.getValue();
    }
    
}
