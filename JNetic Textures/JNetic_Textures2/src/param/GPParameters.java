package param;


import multiobjective.overrides.*;


/**
 * This class stores information which is used to create the parameter file for
 * each project, as well as the base GP parameters.
 *
 * @author Steve Bergen
 */

public class GPParameters {


    public static CrossoverPipeline         CROSS_OBJECT        = null;
    public static ReproductionPipeline      REP_OBJECT          = null;
    public static TournamentSelection       TSELECT_OBJECT      = null;
    public static KozaNodeSelector          NODE_OBJECT         = null;
    public static MutationPipeline          MUTATE_OBJECT       = null;

    public static double                    MUTATION_RATE       = 0.1;
    public static int                       MUTATION_MAX_DEPTH  = 17;

    public static double                    CROSSOVER_RATE      = 0.9;
    public static int                       CROSSOVER_MAX_DEPTH = 10;

    public static int                       T_SIZE              = 7;

    public static double                    GROW_PROB           = 0.5;
    public static int[]                     GROW                = {5, 5};
    public static int[]                     HALF                = {5, 12};

    public static double                    CHOOSE_TERMINAL     = 0.1;

    public static int                       GENERATIONS         = 2;
    public static int                       POP_SIZE            = 10;

    public static int                       ADFS                = 0;

    //public static int                       ELITE               = 10;
    

    /**
     * Prints the stats in this file to a string object for file writing.
     *
     * @return                  String representation
     */

    public static String print ( ) {

        String s = "";

        s += "seed.0 = " + param.Parameters.SEED + "\n\n";

        s += "pop.subpop.0.size = " + POP_SIZE + "\n";
        s += "generations = " + GENERATIONS + "\n\n";

        s += "select.tournament.size = " + T_SIZE + "\n\n";

        s += "pop.subpop.0.species.pipe.source.0.source.0.prob = " +
                CROSSOVER_RATE + "\n";
        s += "pop.subpop.0.species.pipe.source.0.source.1.prob = " +
                (1.0 - CROSSOVER_RATE) + "\n";
        s += "gp.koza.xover.maxdepth = " + CROSSOVER_MAX_DEPTH + "\n\n";

        s += "gp.koza.mutate.prob = " + MUTATION_RATE + "\n";
        s += "gp.koza.mutate.maxdepth = " + MUTATION_MAX_DEPTH + "\n\n";

        for ( int i = 0; i <= GPParameters.ADFS; i++ )
            s += "gp.tc." + i + ".init = ec.gp.koza.HalfBuilder\n";

        s += "gp.koza.grow.min-depth = " + GROW[0] + "\n";
        s += "gp.koza.grow.max-depth = " + GROW[1] + "\n";
        s += "gp.koza.half.min-depth = " + HALF[0] + "\n";
        s += "gp.koza.half.max-depth = " + HALF[1] + "\n";
        s += "gp.koza.half.growp = " + GROW_PROB + "\n\n";

        s += "gp.koza.ns.terminals = " + CHOOSE_TERMINAL + "\n";
        s += "gp.koza.ns.nonterminals = " + (1.0 - CHOOSE_TERMINAL) + "\n";
        s += "gp.koza.ns.root = 0.0\n\n";
        
        return s;

    };


};