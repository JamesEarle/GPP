package param;


import java.io.*;


/**
 * This class is used to create the parameter file for ECJ based upon the values
 * here and in the GPParameters.java file and the FunctionSet.java file.
 *
 * @author Steve Bergen
 */

public class WriteParam {


    public static String            filename = "parameters.params";

    private static final String ec =
            "verbosity = 0\n" +
            "evalthreads = 1\n" +
            "breedthreads = 1\n\n" +

            "checkpoint = false\n" +
            "checkpoint-modulo = 1\n" +
            "prefix = ec\n\n";

    private static final String simple =
            "state = ec.simple.SimpleEvolutionState\n" +
            "init = ec.simple.SimpleInitializer\n" +
            "finish = ec.simple.SimpleFinisher\n" +
            "exch = ec.simple.SimpleExchanger\n" +
            "breed = ec.simple.SimpleBreeder\n" +
            "eval = ec.simple.SimpleEvaluator\n" +
            "stat = ec.simple.SimpleStatistics\n" +
            "quit-on-run-complete = false\n\n" +

            "pop = ec.Population\n" +
            "pop.subpops = 1\n" +
            "pop.subpop.0 = ec.Subpopulation\n" +
            "pop.subpop.0.duplicate-retries = 0\n\n";

    private static String pops =
            "pop.subpop.0.species = ec.gp.GPSpecies\n" +
            "pop.subpop.0.species.ind = ec.gp.GPIndividual\n" +
            "pop.subpop.0.duplicate-retries = 100\n";

    private static final String koza =
            "pop.subpop.0.species.pipe = multiobjective.overrides.MutationPipeline\n" +
            "pop.subpop.0.species.pipe.generate-max = false\n" +
            "pop.subpop.0.species.pipe.source.0 = ec.breed.MultiBreedingPipeline\n" +
            "pop.subpop.0.species.pipe.source.0.num-sources = 2\n" +
            "pop.subpop.0.species.pipe.source.0.source.0 = multiobjective.overrides.CrossoverPipeline\n" +
            "pop.subpop.0.species.pipe.source.0.source.1 = multiobjective.overrides.ReproductionPipeline\n\n" +

            "breed.reproduce.source.0 = multiobjective.overrides.TournamentSelection\n" +
            "gp.koza.xover.source.0 = multiobjective.overrides.TournamentSelection\n" +
            "gp.koza.xover.source.1 = same\n" +
            "gp.koza.xover.ns.0 = multiobjective.overrides.KozaNodeSelector\n" +
            "gp.koza.xover.ns.1 = same\n" +
            "gp.koza.xover.tries = 1\n\n" +

            "gp.koza.mutate.source.0 = multiobjective.overrides.TournamentSelection\n" +
            "gp.koza.mutate.ns.0 = multiobjective.overrides.KozaNodeSelector\n" +
            "gp.koza.mutate.build.0 = ec.gp.koza.GrowBuilder\n" +
            "gp.koza.mutate.maxdepth = 17\n" +
            "gp.koza.mutate.tries = 1\n\n" +

            "init = ec.gp.GPInitializer\n" +
            "stat = ec.gp.koza.KozaStatistics\n\n" +
            "pop.subpop.0.species.fitness = ec.gp.koza.KozaFitness\n\n" +
            "eval.problem.stack = ec.gp.ADFStack\n" +
            "eval.problem.stack.context = ec.gp.ADFContext\n\n";

    public static final String      start =
            "state = gp.SimpleEvolutionState\n\n";

    public static final String      end =
            "eval.problem = gp.TextureProblem\n" +
            "eval.problem.data = dataobjects.Data\n\n" +
            "eval.problem.stack.context.data = dataobjects.Data\n\n";
    

    /**
     * Writes the information to a parameter file.
     */
    
    public static void write ( ) {

        PrintWriter out = null;
        String s = "";

        try {

            out = new PrintWriter(new FileWriter(param.Parameters.PATH + filename));

            s += ec;
            s += simple;
            s += "stat.file = " + "$out.stat\n\n";

            // ADF-related
            s += pops;
            s += "pop.subpop.0.species.ind.numtrees = " + (GPParameters.ADFS + 1) + "\n";
            for ( int i = 0; i < GPParameters.ADFS + 1; i++ ) {
                s += "pop.subpop.0.species.ind.tree." + i + " = ec.gp.GPTree\n";
                s += "pop.subpop.0.species.ind.tree." + i + ".tc = tc" + i + "\n";
            } s += "\n";

            s += koza;
            s += start;
            s += GPParameters.print();
            s += FunctionSet.print();

            // Constraints
            s += "gp.tc.size = " + (GPParameters.ADFS + 1) + "\n\n";
            s += "gp.tc.0 = ec.gp.GPTreeConstraints\n" +
                    "gp.tc.0.name = tc0\n" +
                    "gp.tc.0.fset = f0\n" +
                    "gp.tc.0.returns = render\n\n";

            for ( int i = 1; i < GPParameters.ADFS + 1; i++ ) {
                s += "gp.tc." + i + " = ec.gp.GPTreeConstraints\n";
                s += "gp.tc." + i + ".name = tc" + i + "\n";
                s += "gp.tc." + i + ".fset = f" + i + "\n";
                s += "gp.tc." + i + ".returns = rgb\n\n";
            }

            s += end;

            out.println(s);
            out.close();

        } catch ( Exception e ) {}

    };


};