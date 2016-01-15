/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.app.filereader;
import ec.util.*;
import ec.*;
import ec.gp.*;
import static ec.gp.GPProblem.P_DATA;
import ec.gp.koza.*;
import ec.simple.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *  -p gp.tree.print-style=dot, latex, c
 * @author James Earle
 */
public class FileInputRegression extends GPProblem implements SimpleProblemForm {
    
    private static final long serialVersionUID = 1;
    private static double MAX_VALUE = 0;
    private static final double PERCENT_VERIFY = 0.90;

    public PrintWriter pw;
    public double currentX;
    public double currentY;
//    public BufferedReader br;
    
    public InputFileEnum in;
    public LagSurrogate surrogate;
    public VerificationManager vm;
    public ArrayList<Double> inputData;
    
    @Override
    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);
        
        // Define the input data and time lag preferences.
        in = InputFileEnum.DJ_NORM;
        surrogate = new LagSurrogate(in);
        inputData = new ArrayList<>();
        
        try {
            // Set up the IOManager to keep track of output data
            IOManager io = new IOManager("out_files/", false);
            io.executionSetup();
            pw = io.makePrintWriter("_out.txt");
            
            // Remember to change this index based on which file is to be used.
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + in.v()));
            
            String next;
            while((next = br.readLine()) != null) {
                // Store the max value found in our input data to take an appropriate 
                // hits radius (2.5% the max value, we want this to be somewhat fuzzy)
                MAX_VALUE = Double.valueOf(next) >= MAX_VALUE ? Double.valueOf(next) : MAX_VALUE;
                inputData.add(Double.valueOf(next));
            }
            
            // The chosen boundary for verification will be a percentage of the given data set.
            vm = new VerificationManager(PERCENT_VERIFY, inputData.size());
                        
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(FileInputRegression.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // verify our input is the right class (or subclasses from it)
        if (!(input instanceof ec.app.filereader.DoubleData)) {
            state.output.fatal("GPData class must subclass from " + ec.app.tutorial4.DoubleData.class, base.push(P_DATA), null);
        }
    }
    
    @Override
    public void evaluate(final EvolutionState state,final Individual ind,final int subpopulation,final int threadnum) {
        if (!ind.evaluated) { // don't bother reevaluating 
            ec.app.filereader.DoubleData input = (ec.app.filereader.DoubleData)(this.input);
        
            int hits = 0;
            double sum = 0.0;
            double expectedResult;
            double result;
            
            // Sample all data points for currentX. 
            // We begin counting at the surrogate's index so the X_Lag begins at 0
            for (int i=surrogate.getLag();i<=vm.getRange();i++) {
                
                currentX = i;
                expectedResult = inputData.get(i-1);
                
                // The lagged X_Lag value is the raw input data point at time interval
                // "present - givenLag". Dow Jones lag is 5 days. S&P lag is 23.
//                surrogate.setLagResult(inputData.get(i - surrogate.getLag()));
                surrogate.setLagResult(i - surrogate.getLag());

//                System.out.println(surrogate.getLagResult() + "\n---------------");
                
                ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);
                
                // Save all input.x values into some dynamically growing array. 
                // Don't start evaluating fitness until we are at surrogate.getLag() index
                // Then we can compare input.x (now) to input.x (5 intervals ago), using 
                // the same form of access as the lag setup now (see XLag with LagSurrogate)
                result = Math.abs(expectedResult - input.x);
                
                /* 
                    Sum of Squared Residuals fitness is a tight band, but
                    seems more useful on less granular data. 
                */
                if(surrogate.dataIsDowJones(in)) {
                    result = Math.pow(result, 2);
                }

                // Hit radius as 2.5% of the max value
                if (result <= 0.025*MAX_VALUE) hits++;
                sum += result;  
            }
            
            // the fitness better be KozaFitness!
            KozaFitness f = ((KozaFitness)ind.fitness);
            f.writer = pw;
            f.setStandardizedFitness(state, sum);
            f.hits = hits;
            ind.evaluated = true;
        }
    }
    
//    public boolean dataIsDowJones() {
//        switch(in) {
//            case DJ_ORIG:
//            case DJ_NORM:
//            case DJ_NORM_2:
//            case DJ_NORM_5:
//                return true;
//            default:
//                return false;
//        }
//    }
    
    /* Post-execution cleanup. Close all writers, print out final values, etc. */
    @Override
    public void describe(EvolutionState state, Individual bestIndividual, int subpopulation, int threadnum, int log) {
        IOManager io = ec.Evolve.io;
//        IOManager io = new IOManager(); 
//        PrintWriterFactory factory = new PrintWriterFactory();
        
        ec.app.filereader.DoubleData input = (ec.app.filereader.DoubleData)(this.input);
        
        try {
//            factory.makePrintWriter("/best_ind_training.txt");      // 0
//            factory.makePrintWriter("/best_ind_verification.txt");  // 1
//            factory.makePrintWriter("/best_ind_tree.txt");          // 2

            PrintWriter bestIndFunction = io.makePrintWriter("/best_ind_training.txt");
            PrintWriter bestIndFunctionVerification = io.makePrintWriter("/best_ind_verification.txt");
            PrintWriter bestIndTree = io.makePrintWriter("/best_ind_tree.txt");
            
            for (int i=1;i<=inputData.size();i++) {

                // Sample all data points for currentX
                currentX = i;                
                ((GPIndividual)bestIndividual).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)bestIndividual),this);
                
                // Write to training text file if data is in training range
                if(!vm.inVerificationRange(i)) {
//                    factory.getPrinter(0).println(input.x);
                    bestIndFunction.println(input.x);
                } else { // else it is non-training data, write to separate text file.
//                    factory.getPrinter(1).println(input.x);
                    // the lag calculation will use non training data, but real data,
                    // if we don't save the input.x evaluations
                    bestIndFunctionVerification.println(input.x);
                }
            }
            
            // Print individual statistics and the actual GP tree.
//            bestIndividual.printIndividual(state, factory.getPrinter(2));
            bestIndividual.printIndividual(state, bestIndTree);

            // Finally, we close our output streams.
            bestIndFunctionVerification.close();
            bestIndFunction.close();
            bestIndTree.close();
            
//            factory.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputRegression.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pw.close();
    }
}
