package ec.app.filereader;

import ec.*;
import ec.gp.*;
import ec.util.*;
import ec.simple.*;
import ec.gp.koza.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;
import static ec.gp.GPProblem.P_DATA;

/**
 *  -p gp.tree.print-style=dot, latex, c
 * @author James Earle
 */
public class FileInputRegression extends GPProblem implements SimpleProblemForm {

    private static double MAX_VALUE = 0;
    private static final long serialVersionUID = 1;
    private static final double PERCENT_VERIFY = 0.90;

    public PrintWriter pw;
    public double currentX;
    public double currentY;
//    public BufferedReader br;
    
    public InputFileEnum in;
    public LagSurrogate surrogate;
    public VerificationManager vm;
    
    public ArrayList<Double> inputData;
    public ArrayList<Double> evalDataHistory;
    
    @Override
    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);
        
        // Define the input data and time lag preferences.
        in = InputFileEnum.DJ_NORM;
        surrogate = new LagSurrogate(in);
        inputData = new ArrayList<>();
        evalDataHistory = new ArrayList<>();
        
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
            for (int i=0;i<=vm.getRange();i++) {
                
                if(i < surrogate.getLag()) {
                    ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);
                    
                    evalDataHistory.add(input.x);
                } else {
                    currentX = i;
                    expectedResult = inputData.get(i-1);

                    // Throws a NullPointerException in an unrelated part of code... (KozaFitness PrintWriter object is unset)
//                    surrogate.setLagResult(evalDataHistory.get(i - surrogate.getLag()));
                    
                    // Nonsense solution, e.g. the tree is just "x_t"
//                    surrogate.setLagResult(inputData.get(i - surrogate.getLag()));
                    
                    // Works...?
                    surrogate.setLagResult(i - surrogate.getLag()); 

                    ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);

                    // Save all input.x values into some dynamically growing array. 
                    // Don't start evaluating fitness until we are at surrogate.getLag() index
                    // Then we can compare input.x (now) to input.x (5 intervals ago), using 
                    // the same form of access as the lag setup now (see XLag with LagSurrogate)
                    result = Math.abs(expectedResult - input.x);

                    /* 
                        Sum of Squared Residuals fitness is a tight band, but
                        seems more useful on less granular data. Thus, we 
                        only use on Dow Jones data. 
                    */
                    if(surrogate.dataIsDowJones(in)) {
                        result = Math.pow(result, 2);
                    }

                    // Hit radius as 2.5% of the max value
                    if (result <= 0.025*MAX_VALUE) hits++;
                    sum += result;  
                } // else
            } // for 
            
            
            // the fitness better be KozaFitness!
            KozaFitness f = ((KozaFitness)ind.fitness);
            f.writer = pw;
            f.setStandardizedFitness(state, sum);
            f.hits = hits;
            ind.evaluated = true;
        }
    }
    
    /**
     * Post-execution cleanup. Here we do final processing of output to a 
     * run specific, time stamped directory. After this, we close all open 
     * writers and exit.
     * 
     * @param state
     * @param bestIndividual
     * @param subpopulation
     * @param threadnum
     * @param log 
     */
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
