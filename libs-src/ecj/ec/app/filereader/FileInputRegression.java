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

    public BufferedReader br;
    public PrintWriter pw;
    public double currentX;
    public double currentY;
    public InputFileEnum in;
    
    public ArrayList<Double> inputData;
    
    @Override
    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);
        
        inputData = new ArrayList<>();
        
        try {
            IOManager io = new IOManager("out_files/", false);
            io.executionSetup();
            pw = io.makePrintWriter("_out.txt");
            
            in = InputFileEnum.DJ_NORM;
            
            // Remember to change this index based on which file is to be used.
            br = new BufferedReader(new FileReader(System.getProperty("user.dir") + in.v()));
            
            String next;
            while((next = br.readLine()) != null) {
                
                // Store the max value found in our input data to take an appropriate 
                // hits radius (2.5% the max value, we want this to be somewhat fuzzy)
                MAX_VALUE = Double.valueOf(next) >= MAX_VALUE ? Double.valueOf(next) : MAX_VALUE;
                inputData.add(Double.valueOf(next));
            }
                        
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
            
            // Sample all data points for currentX
            for (int i=1;i<=inputData.size();i++) {
                
                currentX = i;
                expectedResult = inputData.get(i-1);

                ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);
                
                result = Math.abs(expectedResult - input.x);
                
                /* 
                    Sum of Squared Residuals fitness is a tight band, but
                    only useful on less granular data. 
                */
                if(dataIsDowJones()) {
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
    
    public boolean dataIsDowJones() {
        switch(in) {
            case DJ_ORIG:
            case DJ_NORM:
            case DJ_NORM_2:
            case DJ_NORM_5:
                return true;
            default:
                return false;
        }
    }
    
    /* Post-execution cleanup. Close all writers, print out final values, etc. */
    @Override
    public void describe(EvolutionState state, Individual bestIndividual, int subpopulation, int threadnum, int log) {
        IOManager io = ec.Evolve.io;
        
        ec.app.filereader.DoubleData input = (ec.app.filereader.DoubleData)(this.input);

        try {
            
            PrintWriter bestFunction = io.makePrintWriter("/best_ind.txt");
            PrintWriter bestFunctionTree = io.makePrintWriter("/best_ind_tree.txt");
            
            // Sample all data points for currentX
            for (int i=1;i<=inputData.size();i++) {
                currentX = i;                
                ((GPIndividual)bestIndividual).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)bestIndividual),this);

                bestFunction.println(input.x);
            }
            
            bestIndividual.printIndividual(state, bestFunctionTree);
            
            bestFunction.close();
            bestFunctionTree.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputRegression.class.getName()).log(Level.SEVERE, null, ex);
        }
        pw.close();
    }
}
