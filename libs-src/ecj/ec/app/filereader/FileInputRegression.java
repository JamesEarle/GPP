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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author James Earle
 */
public class FileInputRegression extends GPProblem implements SimpleProblemForm {
    
    private static final long serialVersionUID = 1;

    public BufferedReader br;
    public double currentX;
    public double currentY;
    
    @Override
    public void setup(final EvolutionState state, final Parameter base) {
        super.setup(state, base);
        
        try {
            br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\testinput.txt"));
        } catch (FileNotFoundException ex) {
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
        if (!ind.evaluated) {  // don't bother reevaluating
            ec.app.filereader.DoubleData input = (ec.app.filereader.DoubleData)(this.input);
        
            int hits = 0;
            double sum = 0.0;
            double expectedResult;
            double result;
            
            for (int y=0;y<10;y++) {
                //Old currentX
                currentX = state.random[threadnum].nextDouble();
                currentY = state.random[threadnum].nextDouble();
                
                //New currentX is read from file.
                try {
                    String next = br.readLine();
                    if (next != null && !next.equals("")) {
                        currentX = Double.valueOf(br.readLine());
                        System.out.println(currentX);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FileInputRegression.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //currentX = state.random[threadnum].nextDouble();
                //currentY = state.random[threadnum].nextDouble();
                
                //new: 
                //expectedResult = Math.pow(Math.PI, currentX * currentX * currentY + currentX * currentY + currentY);
                // old: 
                
                expectedResult = currentX * currentX + currentX * state.random[threadnum].nextDouble()  + Math.PI * currentX;
                //expectedResult = currentX * currentX + currentX * currentY + currentX;
                ((GPIndividual)ind).trees[0].child.eval(state,threadnum,input,stack,((GPIndividual)ind),this);

                result = Math.abs(expectedResult - input.x);
                if (result <= 0.01) hits++;
                sum += result;                  
            }

            // the fitness better be KozaFitness!
            KozaFitness f = ((KozaFitness)ind.fitness);
            f.setStandardizedFitness(state, sum);
            f.hits = hits;
            ind.evaluated = true;
        }
    }
}
