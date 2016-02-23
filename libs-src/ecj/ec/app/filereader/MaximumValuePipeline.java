package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class MaximumValuePipeline extends Pipeline {

    public MaximumValuePipeline(ArrayList<Double> input) {
        super();
        this.input = input;
        calculateMaxValue();
    }
    
    public final void calculateMaxValue() {
        for(int i=0;i<input.size();i++) {
            double max = Double.MIN_VALUE;
            
            for(int j=0;j<lag;j++) {
                if(i+j < input.size()) {
                    max = input.get(i+j) > max ? input.get(i+j) : max;
                }
            }
            
            values.add(max);
        }
    }
    
}
