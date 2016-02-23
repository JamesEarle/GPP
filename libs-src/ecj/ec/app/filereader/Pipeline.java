package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class Pipeline extends AbstractPipeline {
    
    int lag;
    double value;
    
    ArrayList<Double> output;
    ArrayList<Double> input;
    
    public Pipeline() {
        this.lag = 5;
        this.value = 0;
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
    
    @Override
    public double getValue() {
        return this.value;
    }
    
}
