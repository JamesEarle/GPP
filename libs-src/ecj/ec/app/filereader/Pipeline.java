package ec.app.filereader;

import java.util.ArrayList;

/**
 *
 * @author James Earle
 */
public class Pipeline extends AbstractPipeline {
    
    int time;
    double value;
    ArrayList<Double> output;
    ArrayList<Double> input;
    
    @Override
    void setTime(int time) {
        this.time = time;
    }
    
    @Override
    void getTime(){}
    
    @Override
    void setValue(double value){}
    
    @Override
    void getValue(){}
    
}
