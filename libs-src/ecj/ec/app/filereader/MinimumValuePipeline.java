package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class MinimumValuePipeline extends Pipeline {

    public MinimumValuePipeline(ArrayList<Double> input) {
        super();
        this.input = input;
        values.add(0.0);
        calculateMaxValue();
    }
    
    public final void calculateMaxValue() {
        for(int i=0;i<input.size();i++) {
            double min = Double.MAX_VALUE;
            
            for(int j=0;j<lag;j++) {
                if(i+j < input.size()) {
                    min = input.get(i+j) < min ? input.get(i+j) : min;
                }
            }
            values.add(min);
        }
    }
}
