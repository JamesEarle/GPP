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
    
    public InputFileEnum in;
    public LagSurrogate surrogate;
    public VerificationManager vm;
    
    public ArrayList<Double> inputData;
    public ArrayList<Double> evalDataHistory;
    
    @Override
    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);
        
        // Define the input data and time lag preferences.
        in = InputFileEnum.MSFT_VOLUME_1;
        surrogate = new LagSurrogate(in);
        inputData = new ArrayList<>();
        evalDataHistory = new ArrayList<>();
        
        try {
            // Set up the IOManager to keep track of output data
            IOManager io = new IOManager("out_files/", false);
            io.executionSetup();
            pw = io.makePrintWriter("_out.txt");
            
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
            for (int i=1;i<=vm.getRange();i++) {
                
                currentX = i;
                expectedResult = inputData.get(i-1);
                
//                surrogate.setLagResult(inputData.get(i - surrogate.getLag()));

                ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);
                result = Math.abs(expectedResult - input.x);

                // Sum of Squared Residuals
                if(surrogate.dataIsDowJones(in)) {
                    result = Math.pow(result, 2);
                }

                // Hit radius as 2.5% of the max value
                if (result <= 0.025*MAX_VALUE) hits++;
                sum += result;  
            }
            
            
            // the fitness better be KozaFitness!
            KozaFitness f = ((KozaFitness)ind.fitness);
            if(pw == null) System.out.println("pw is null!!!");
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
        PrintWriterFactory factory = new PrintWriterFactory(ec.Evolve.io);
        ec.app.filereader.DoubleData input = (ec.app.filereader.DoubleData)(this.input);
        
        try {
            factory.makePrintWriter("/best_ind_training.txt");      // 0
            factory.makePrintWriter("/best_ind_verification.txt");  // 1
            factory.makePrintWriter("/best_ind_tree.txt");          // 2
            
            for(int i=1;i<=inputData.size();i++) {

                // Sample all data points for currentX
                currentX = i;                
                ((GPIndividual)bestIndividual).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)bestIndividual),this);
                
                if(!vm.inVerificationRange(i)) { 
                    // Training Data
                    factory.getPrinter(0).println(input.x);
                } else { 
                    // Verification Data
                    factory.getPrinter(1).println(input.x);
                }
            }
            
            // Print individual statistics and the actual GP tree.
            bestIndividual.printIndividual(state, factory.getPrinter(2));
            factory.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputRegression.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pw.close();
    }
}
