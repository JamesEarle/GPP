package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class MovingAveragePipeline {
    
    private final int duration;
    private double value;
    private ArrayList<Double> values;
    private ArrayList<Double> input;
    
    //TODO name this consistently with the LagSurrogate
    
    /**
     * 
     * @param input 
     */
    public MovingAveragePipeline(ArrayList<Double> input) {
        this.duration = 5;
        this.value = 0;
        this.values = new ArrayList<>();
        this.input = input;
    }
    
    /**
     * 
     */
    public void calculateMovingAverages() {
        for(int i=0;i<input.size();i++) {
            double sum = 0.0;
            
            for(int j=0;j<duration;j++) {
                if(i+j < input.size()) {
                    sum += input.get(i+j);
                }
            }
            
            values.add(sum);
        }
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Double> getValues() {
        return values;
    }
    
    /**
     * 
     * @param index
     * @return 
     */
    public double getValueAt(int index) {
        return values.get(index);
    }
    
    /**
     * 
     * @param value 
     */
    public void setValue(double value) {
        this.value = value;
    }
    
    /**
     * 
     * @return 
     */
    public int getDuration() {
        return duration;
    }
    
    public double getValue() {
        return value;
    }
}
