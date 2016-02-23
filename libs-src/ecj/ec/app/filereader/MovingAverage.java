package ec.app.filereader;

import ec.gp.*;
import ec.*;

/**
 * @author James Earle
 */
public class MovingAverage extends GPNode {
    
    @Override
    public String toString() { return "avg"; }
    
    @Override
    public int expectedChildren() { return 0; }
    
    @Override
    public void eval(final EvolutionState state, final int thread, final GPData input,
        final ADFStack stack, final GPIndividual individual, final Problem problem) {
        
        PipelinePool pool = ((FileInputRegression)problem).pool;
//        MovingAveragePipeline pipeline = (MovingAveragePipeline)pool.pipelines[0];
//          MovingAveragePipeline pipeline = (MovingAveragePipeline)pool.pipelines2.get(0);
        MovingAveragePipeline pipeline = (MovingAveragePipeline)pool.pipelines.get("MovingAveragePipeline");

        
//        MovingAveragePipeline pipeline = ((FileInputRegression)problem).movingAverage;
        DoubleData rd = ((DoubleData)input);
        
        rd.x = pipeline.getValue();
    }
    
}
