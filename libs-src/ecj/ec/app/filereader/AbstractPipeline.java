package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public abstract class AbstractPipeline {
    
    int time;
    double value;
    ArrayList<Double> output;
    ArrayList<Double> input;
    
    abstract void setTime(int time);
    abstract void setValue(double value);

    abstract void getTime();
    abstract void getValue();
    
}
