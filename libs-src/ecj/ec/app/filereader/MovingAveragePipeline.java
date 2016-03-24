package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class MovingAveragePipeline extends Pipeline {
    
    public MovingAveragePipeline(ArrayList<Double> input) {
        super();
        this.input = input;
        calculateMovingAverages();
    }
    
    public final void calculateMovingAverages() {
        for(int i=0;i<input.size();i++) {
            double sum = 0.0;
            
            for(int j=0;j<lag;j++) {
                if(i+j < input.size()) {
                    sum += input.get(i+j);
                }
            }
            values.add(sum / lag);
        }
    }
}
