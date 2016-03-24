package ec.app.filereader;

import java.util.ArrayList;

/**
 * The sum of the last X days of values, where X is the time lag.
 * 
 * @author James Earle
 */
public class MovingSumPipeline extends Pipeline {

    public MovingSumPipeline(ArrayList<Double> input) {
        super();
        this.input = input;
        addMovingSum();
    }
    
    public final void addMovingSum() {
        for(int i=0;i<input.size();i++) {
            double sum = 0.0;
            
            for(int j=0;j<lag;j++) {
                if(i+j < input.size()) {
                    sum += input.get(i+j);
                }
            }
            values.add(sum);
        }
    } 
}
