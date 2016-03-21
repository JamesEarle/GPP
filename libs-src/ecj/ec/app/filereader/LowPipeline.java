package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class LowPipeline extends Pipeline {

    public LowPipeline(ArrayList<Double> input) {
        super();
        this.input = input;
        addDailyLow();
    }
    
    // The pipeline generally requires data to exist in the values array for 
    // access. There are no calculations to be made here, but should just 
    // copy the values over so that the pre-existing PipelinePool setting works
    // the same across values that do (and values that don't) require calculations.
    public final void addDailyLow() {
        
        // Add a null value at first index so every index represents yesterdays volume trade.
        // We shouldn't be able to look at todays value.
        // DOUBLE CHECK THAT THIS IS LOGICALLY RIGHT, VERIFY LAG INDEX 
        this.values.add(0.0);
        
        for(int i=0;i<input.size();i++) {
            this.values.add(input.get(i));
        }
    } 
}
