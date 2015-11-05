package gp;


import ec.util.*;
import ec.*;

import param.Parameters;
import multiobjective.MOData;
import interfaces.*;
import fitness.*;
import java.text.*;
import param.FitnessStat;


/**
 * An extension of the SimpleEvolutionState object in ECJ. Small changes were
 * made which enabled the monitoring and updating of the population, as well as
 * the ability to implement a custom fitness evaluation.
 *
 * @author Steve Bergen
 */

public class SimpleEvolutionState extends EvolutionState {

    private DecimalFormat   df      = new DecimalFormat("#.######");

    private double[][]      STATS;
    private double[][]      ESTATS;

    public boolean          PAUSED  = false;
    

    @Override
    public void startFresh ( ) {

        output.message("Setting up");

        setup(this,null);  // a garbage Parameter

        param.Parameters.STATE  = this;
        
        // POPULATION INITIALIZATION
        output.message("Initializing Generation 0");
        statistics.preInitializationStatistics(this);
        population = initializer.initialPopulation(this, 0); // unthreaded
        statistics.postInitializationStatistics(this);

        // INITIALIZE CONTACTS -- done after initialization to allow
        // a hook for the user to do things in Initializer before
        // an attempt is made to connect to island models etc.
        exchanger.initializeContacts(this);
        evaluator.initializeContacts(this);

    };


    /**
     * Method called every generation to evolve and evaluate a population.
     *
     * @return              Success or failure
     */

    @Override
    public int evolve ( ) {
        
        if (generation > 0) 
            output.message("Generation " + generation);

        // EVALUATION
        statistics.preEvaluationStatistics(this);
        evaluator.evaluatePopulation(this);

        // -----------------------------------------
        // PRINT ANY POPULATION STUFF HERE AND PAUSE
        // -----------------------------------------

        MOData[] pop = getPopulation();
        fitness.Rank.rank(pop);
        Parameters.FRAME.updatePopulation(pop);
        printStats(pop);

        // PAUSED
        if ( PAUSED ) {
            Parameters.FRAME.enableall();
            Parameters.FRAME.playButton.setEnabled(true);
            Parameters.FRAME.playButton.setIcon(
                    new javax.swing.ImageIcon(
                    getClass().getResource("/images/playButton.png")));
        }
        while (PAUSED);
        
        statistics.postEvaluationStatistics(this);

        // SHOULD WE QUIT?
        if (evaluator.runComplete(this) && quitOnRunComplete)
            {
            output.message("Found Ideal Individual");
            return R_SUCCESS;
            }

        // SHOULD WE QUIT?
        if (generation >= numGenerations-1)
            {
            return R_FAILURE;
            }

        // PRE-BREEDING EXCHANGING
        statistics.prePreBreedingExchangeStatistics(this);
        population = exchanger.preBreedingExchangePopulation(this);
        statistics.postPreBreedingExchangeStatistics(this);

         
        String exchangerWantsToShutdown = exchanger.runComplete(this);
        if (exchangerWantsToShutdown!=null)
            { 
            output.message(exchangerWantsToShutdown);
            
            return R_SUCCESS;
            }

        // BREEDING
        statistics.preBreedingStatistics(this);

        population = breeder.breedPopulation(this);
        
        // POST-BREEDING EXCHANGING
        statistics.postBreedingStatistics(this);
            
        // POST-BREEDING EXCHANGING
        statistics.prePostBreedingExchangeStatistics(this);
        population = exchanger.postBreedingExchangePopulation(this);
        statistics.postPostBreedingExchangeStatistics(this);

        // INCREMENT GENERATION AND CHECKPOINT
        generation++;
        if (checkpoint && generation%checkpointModulo == 0) 
            {
            output.message("Checkpointing");
            statistics.preCheckpointStatistics(this);
            Checkpoint.setCheckpoint(this);
            statistics.postCheckpointStatistics(this);
            }

        return R_NOTDONE;

    };


    /**
     * Method called when run completes.
     *
     * @param result            Result value
     */

    @Override
    public void finish ( int result ) {

        /* finish up -- we completed. */
        statistics.finalStatistics(this,result);
        finisher.finishPopulation(this,result);
        exchanger.closeContacts(this,result);
        evaluator.closeContacts(this,result);

        PAUSED = false;
        param.Parameters.FRAME.running = false;
        Parameters.FRAME.playButton.setIcon(
                new javax.swing.ImageIcon(
                getClass().getResource("/images/playButton.png")));
        param.Parameters.FRAME.enableEverything();
        System.out.println("DONE");

    };

      
    /**
     * Converts a population of GPIndividual to a population of MOData,
     * and calculates the fitnesses of each.
     *
     * @return                  New population
     */

    public MOData[] getPopulation ( ) {
        
        Individual[]    POP         = population.subpops[0].individuals;
        MOData[]        MOpop       = new MOData[POP.length];

        Parameters.FRAME.resetProgress(POP.length);

        for ( int i = 0; i < POP.length; i++ ) {
            MOpop[i] = new MOData(POP[i], i);
            Parameters.FRAME.incrementProgress();
        }

        return MOpop;
        
    };


    /**
     * Prints the statistics to the displayer at the bottom of the window.
     *
     * @param pop               Population
     */

    private void printStats ( MOData[] pop ) {

        Console out = param.Parameters.FRAME.consolewriter;

        STATS = new double[param.Parameters.FITNESS_FUNCTIONS.size()][2];
        ESTATS = new double[param.Parameters.FITNESS_FUNCTIONS.size()][2];

        out.clear();
        out.writeSuccess("Generation " + generation + " completed!\n");
        for ( int i = 0; i < param.Parameters.FITNESS_FUNCTIONS.size(); i++ )
            out.writeMsg(getBestandAve(i, pop));

        param.Parameters.STATS.add(
                new FitnessStat(STATS.clone(), ESTATS.clone()));
        
    };


    /**
     * Calculates the best and average values for a fitness function and both
     * stores their values and returns them as a String object for printing.
     *
     * @param index             Index of fitness function
     * @param pop               Population
     * @return                  String representation of best and average values
     */
    
    private String getBestandAve ( int index, MOData[] pop ) {

        FitnessFunction f   = (FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(index);
        String s = f.getName() + " :\t\t";

        double  total       = 0;
        double  etotal      = 0;
        double  best        = 0;
        int     besti       = -1;

        best = pop[0].fitness[index];
        besti = 0;
        total += pop[0].raw[index];

        for ( int i = 1; i < pop.length; i++ ) {
            total += pop[i].raw[index];
            etotal += pop[i].fitness[index];
            if ( f.isBetter(pop[i].fitness[index], best) ) {
                best = pop[i].fitness[index];
                besti = i;
            }
        }

        total /= pop.length;
        etotal /= pop.length;

        ESTATS[index][0] = best;
        ESTATS[index][1] = etotal;

        best = pop[besti].raw[index];

        STATS[index][0] = best;
        STATS[index][1] = total;

        s += "Best = " + df.format(best) + "\t";
        s += "Ave. = " + df.format(total);

        return s;

    };


};