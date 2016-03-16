package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class Pipeline extends AbstractPipeline {
    
    int lag;
    double value;
    
    ArrayList<Double> values;
    ArrayList<Double> output;
    ArrayList<Double> input;
    
    public Pipeline() {
        this.lag = 5;
        this.value = 0;
        
        this.values = new ArrayList<>();
        this.input = new ArrayList<>();
    }
    
    @Override
    public ArrayList<Double> getValuesList() {
        return values;
    }
    
    @Override
    public double getValue() {
        return this.value;
    }
    
    @Override
    public double getValueAt(int index) {
        return values.get(index);
    }
    
    @Override
    public void setLag(int lag) {
        this.lag = lag;
    }
    
    @Override
    public int getLag() {
        return this.lag;
    }
    
    @Override
    public void setValue(double value) {
        this.value = value;
    }
    
}
