package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public abstract class AbstractPipeline {
    
    int lag;
    double value;
    
    ArrayList<Double> output;
    ArrayList<Double> input;
    
    abstract void setLag(int lag);
    abstract void setValue(double value);

    abstract int getLag();
    abstract double getValue();
    
}
