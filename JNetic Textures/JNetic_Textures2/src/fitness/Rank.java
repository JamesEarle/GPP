package fitness;


import multiobjective.*;


/**
 * Class used to rank a population of MOData individuals. The ranking is done
 * based upon the sum of the ranks of each separate fitness function.
 *
 * @author Steve Bergen
 */

public class Rank {


    /**
     * Method which is called to rank a population of individuals based on
     * fitness values.
     *
     * @param pop               Population to rank.
     */

    public static void rank ( MOData[] pop ) {

        int length = param.Parameters.FITNESS_FUNCTIONS.size();

        // For each fitness function, sort population and set ranks
        for ( int i = 0; i < length; i++ ) {
            FitnessFunction f =
                    ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(i));
            pop = sort(i, pop, f);
            pop = setRanks(i, pop);
        }

        // Set fitness values (rank) to population
        for ( int i = 0; i < pop.length; i++ )
            pop[i].updateFitness();

    };


    /**
     * Sorts the population based on a fitness function.
     *
     * @param index             Index of the fitness function
     * @param pop               Population
     * @param f                 Fitness function
     * @return                  Sorted population
     */

    public static MOData[] sort ( int index, MOData[] pop, FitnessFunction f ) {

        boolean swapped;

        // Perform bubblesort (best to worst)
        do {
            swapped = false;
            for (int i = 0; i < pop.length - 1; i++) {
                if (f.isBetter(pop[i].fitness[index], pop[i+1].fitness[index]) && pop[i].fitness[index] != pop[i+1].fitness[index]){
                    MOData temp = pop[i];
                    pop[i] = pop[i+1];
                    pop[i+1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        return pop;

    };


    /**
     * Set the ranks of a sorted population.
     *
     * @param index             Index of the fitness function
     * @param pop               Population
     * @return                  Ranked population
     */
    
    public static MOData[] setRanks ( int index, MOData[] pop ) {

        int rank = 0;

        for (int i = pop.length - 1; i >= 0  ; i--) {
            pop[i].ranks[index] = rank;
            pop[i].fit += rank;
            if ( i > 0 &&
                    (pop[i].raw[index] != pop[i-1].raw[index]))
                rank++;
        }

        return pop;

    };


};