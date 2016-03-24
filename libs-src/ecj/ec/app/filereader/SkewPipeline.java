package ec.app.filereader;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author James Earle
 */
public class SkewPipeline extends Pipeline {
    
    public SkewPipeline(ArrayList<Double> input) {
        super();
        this.input = input;
        calculateSkew();
    }
    
    public final void calculateSkew() {
        for(int i=0;i<input.size();i++) {
            double sum = 0.0;
            double avg, median, stdev;

            // Create a sample dataset the length of the lag
            double[] sampleDataSeries = new double[lag];
            
            for(int j=0;j<lag;j++) {
                if(i+j < input.size()) {
                    sum += input.get(i+j);
                    sampleDataSeries[j] = input.get(i+j);
                }
            }
            
            avg = sum / lag;
            sum = 0.0;         
            
            // Recalculate difference from mean squared, for standard deviation
            for(int j=0;j<lag;j++) {
                if(i+j < input.size()) {
                    sum += Math.pow(Math.abs(input.get(i+j) - avg), 2);
                }
            }
            
            stdev = sum / lag;
            
            // To find the median, we must sort the sample data set.
            Arrays.sort(sampleDataSeries);
            
            if (sampleDataSeries.length % 2 == 0) {
                double a = sampleDataSeries[(int)Math.ceil(sampleDataSeries.length / 2)];
                double b = sampleDataSeries[(int)Math.floor(sampleDataSeries.length / 2)];
                
                // Average of the two middle numbers.
                median = (a + b) / 2;
            } else {
                // The middle number.
                median = sampleDataSeries[(int)Math.floor(sampleDataSeries.length / 2)];
            }
             
            // Skewness as 3(Mean - Median)/St. Dev
            values.add(3 * (avg - median) / stdev);
        }
    }
}
