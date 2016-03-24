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
    
    // Should pass parameter to python sheets file based on this value.
    private static final double PERCENT_VERIFY = 0.50;

    public PrintWriter pw;
    public double currentX;
    
    public InputFileEnum in;
    public VerificationManager vm;
    public ArrayList<Double> inputData;
    
    // Declare our Pipelines to time dependent terminals.    
    public Pipeline generalPipeline;
    public PipelinePool pool;
    
    @Override
    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);
        
        in = InputFileEnum.DJIA_CLOSE;
//        in = InputFileEnum.DJ_NORM_1;
        inputData = new ArrayList<>();
        
        // Create a PipelinePool, expecting the number of Pipelines it will manage.
        generalPipeline = new Pipeline();
        pool = new PipelinePool();
        
        try {
            // Set up the IOManager to keep track of output data
            IOManager io = new IOManager("out_files/", false);
            io.executionSetup();
            pw = io.makePrintWriter("_out.txt");
            
            FinancialDataReader fdr = new FinancialDataReader();
//            inputData = fdr.read(InputFileEnum.DJIA_CLOSE);
            
            // Find the file specified by the enumerator above.
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
            
            // Add all Pipelines for terminal values to the PipelinePool
            pool.add("MovingAveragePipeline", new MovingAveragePipeline(inputData));
            pool.add("StandardDeviationPipeline", new StandardDeviationPipeline(inputData));
            pool.add("SkewPipeline", new SkewPipeline(inputData));
            
            // Min and Max exhibit really weird convergence behaviour...
            pool.add("MinimumValuePipeline", new MinimumValuePipeline(inputData));
            pool.add("MaximumValuePipeline", new MaximumValuePipeline(inputData));
            
            // Add all Pipelines for financial functions that read from special files.
            pool.add("VolumeTradedPipeline", new VolumeTradedPipeline(fdr.read(InputFileEnum.DJIA_VOL)));
            pool.add("HighPipeline", new HighPipeline(fdr.read(InputFileEnum.DJIA_HIGH)));
            pool.add("LowPipeline", new LowPipeline(fdr.read(InputFileEnum.DJIA_LOW)));
            pool.add("OpenPipeline", new OpenPipeline(fdr.read(InputFileEnum.DJIA_OPEN)));
            
//            br.close();
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
            int start = generalPipeline.getLag() + 1;
            int finish = vm.getRange();
            
            for (int i=start;i<=finish;i++) {
                
                currentX = i;
                expectedResult = inputData.get(i - 1);
                
                // Pass values to time-dependent terminals.
                pool.setValue(i - start);

                ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);
                result = Math.abs(expectedResult - input.x);

                // Sum of Squared Residuals
                result = Math.pow(result, 2);

                // Hit radius as 2.5% of the max value
                if (result <= 0.01*MAX_VALUE) hits++;
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
        
        double sum = 0.0;
        
        try {
            factory.makePrintWriter("/" + ec.Evolve.runNumber + "_best_ind_training.txt");      // 0
            factory.makePrintWriter("/" + ec.Evolve.runNumber + "_best_ind_verification.txt");  // 1
            factory.makePrintWriter("/" + ec.Evolve.runNumber + "_best_ind_tree.txt");          // 2
            
            int start = generalPipeline.getLag() + 1;
            for(int i=start;i<=inputData.size();i++) {

                // Sample all data points for currentX
                currentX = i;                
                
                // Update all pipelines in the PipelinePool
                pool.setValue(i - start);
                
                ((GPIndividual)bestIndividual).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)bestIndividual),this);
                
                if(!vm.inVerificationRange(i)) { 
                    // Training Data
                    factory.getPrinter(0).println(input.x);
                } else { 
                    // Verification Data
                    factory.getPrinter(1).println(input.x);
                    
                    // Calculate fitness of verification data.
                    double expectedResult = inputData.get(i-1);
                    double result = Math.abs(expectedResult - input.x);
                    
                    result = Math.pow(result, 2);
                    sum += result;
                }
            }

            // Print individual statistics and the actual GP tree.
            KozaFitness f = ((KozaFitness)bestIndividual.fitness);
            f.writer = pw;
//            f.setStandardizedFitness(state, sum);
            bestIndividual.evaluated = true;
            
            factory.getPrinter(2).println("STD: " + sum + "\n\n");
            factory.getPrinter(2).println("ADJ: " + f.adjustedFitness());
            factory.getPrinter(2).println("HIT: " + f.hits + "\n\n");
            
            bestIndividual.printIndividual(state, factory.getPrinter(2));

            // Close all writers in the factory
            factory.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputRegression.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pw.close();
    }
}
