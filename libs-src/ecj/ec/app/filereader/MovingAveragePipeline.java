package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class MovingAveragePipeline extends Pipeline {
    
    private final ArrayList<Double> values;
    
    public MovingAveragePipeline(ArrayList<Double> input) {
        super();
        this.values = new ArrayList<>();
        this.input = input;
    }
    
    public void calculateMovingAverages() {
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
    
    public ArrayList<Double> getValues() {
        return values;
    }
    
    public double getValueAt(int index) {
        return values.get(index);
    }
}
