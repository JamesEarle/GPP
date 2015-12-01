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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *  -p gp.tree.print-style=dot, latex, c
 * @author James Earle
 */
public class FileInputRegression extends GPProblem implements SimpleProblemForm {
    
    private static final long serialVersionUID = 1;
    private static double MAX_VALUE = 0;

    public BufferedReader br;
    public PrintWriter pw;
    public double currentX;
    public double currentY;
    
    public ArrayList<Double> inputData;
    
    @Override
    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);
        
        inputData = new ArrayList<>();
        
        try {
            
            // The directory structure here is where every individual run's stats get processed to.
            // You should never really need to access these, as read.py will process them into 
            // the appropriate folder in the docs-img/ directory.
            Date date = new Date();
            Format forFiles = new SimpleDateFormat("HH-mm-ss-" + Double.toString(Math.random())); // Hour, Minute, Second, Random (for sorted uniqueness)
            Format forDirs =  new SimpleDateFormat("yyyy-MM-dd"); // Day
            
            // Create the directory if it doesn't already exist.
            new File("out_files/" + forDirs.format(date)).mkdir();
            
            File file = new File("out_files/" + forDirs.format(date) + "/" + new File(forFiles.format(date)) + "_out.txt");
            
            // Permissions good in Windows, but need to be altered in Linux. 
            // The below methods set them to true, but doesn't ALWAYS work, it seems...
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
            
            // as a cheap work around, we can also execute a chmod command instead. 
            Runtime.getRuntime().exec("chmod 777 " + file.getAbsolutePath());
            pw = new PrintWriter("out_files/" + forDirs.format(date) + "/" + new File(forFiles.format(date)) + "_out.txt");
            
            // Get the data input file being used for this input.
            String dataInputFile = "/sp500_2005_2015_daily_normalized.txt";
            br = new BufferedReader(new FileReader(System.getProperty("user.dir") + dataInputFile));
            
            String next;
            while((next = br.readLine()) != null) {
                
                // Store the max value found in our input data to take an appropriate 
                // hits radius (2.5% the max value, we want this to be somewhat fuzzy)
                MAX_VALUE = Double.valueOf(next) >= MAX_VALUE ? Double.valueOf(next) : MAX_VALUE;
                inputData.add(Double.valueOf(next));
            }
                        
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
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
            
            // Sample all data points for currentX
            for (int i=1;i<=inputData.size();i++) {
                currentX = i;
                expectedResult = inputData.get(i-1);
                
                ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);

                result = Math.abs(expectedResult - input.x);
                
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
    
    /* Post-execution cleanup. Close all writers, print out final values, etc. */
    @Override
    public void describe(EvolutionState state, Individual bestIndividual, int subpopulation, int threadnum, int log) {
        String destinationDirectory = "docs-img/" + ec.Evolve.dayDir + "/" + ec.Evolve.hourDir;
        
        if(ec.Evolve.isItLong) {
            ec.app.filereader.DoubleData input = (ec.app.filereader.DoubleData)(this.input);

            try {
                PrintWriter bestFunction = new PrintWriter(destinationDirectory + "/best_ind.txt");

                // Sample all data points for currentX
                for (int i=1;i<=inputData.size();i++) {
                    currentX = i;                
                    ((GPIndividual)bestIndividual).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)bestIndividual),this);

                    bestFunction.println(input.x);
                }
                
                bestFunction.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileInputRegression.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            PrintWriter bestFunctionTree = new PrintWriter(destinationDirectory + "best_ind_tree.txt");
            bestFunctionTree.println(bestIndividual.genotypeToStringForHumans());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputRegression.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        pw.close();
    }
}
