package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class StandardDeviationPipeline extends Pipeline {

    public StandardDeviationPipeline(ArrayList<Double> input) {
        super();
        this.input = input;
        calculateStandardDeviations();
    }
    
    public final void calculateStandardDeviations() {
        for(int i=0;i<input.size();i++) {
            double sum = 0.0;
            
            for(int j=0;j<lag;j++) {
                if(i+j < input.size()) {
                    sum += input.get(i+j);
                }
            }
            
            double avg = sum / lag;
            sum = 0.0;         
            
            for(int j=0;j<lag;j++) {
                if(i+j < input.size()) {
                    sum += Math.pow(Math.abs(input.get(i+j) - avg), 2);
                }
            }
            
            values.add(sum / lag);
        }
    }
    
}
