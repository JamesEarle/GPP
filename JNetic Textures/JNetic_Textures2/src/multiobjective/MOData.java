package multiobjective;

import java.awt.image.BufferedImage;

import ec.gp.koza.*;
import ec.*;
import ec.gp.*;

import eval.*;
import fitness.*;
import java.io.*;


/**
 * This object represents a single individual in the population. It stores and
 * calculates fitness values, as well as tree information and the tree itself.
 *
 * @author Steve Bergen
 */

public class MOData {


    public int              ID;
    public Individual       program     = null;         // GP tree

    public double           fitness[]   = null;         // Fitness error
    public int              ranks[]     = null;         // Ranks-per-fitness
    public double           raw[]       = null;         // Raw fitness

    public float            fit         = 0;            // Rank

    public int              depth       = 0;            // Depth of tree
    public int              nodes       = 0;            // Nodes in tree


    /**
     * Creates an object from a GP tree, as well as performs fitness
     * calculation.
     *
     * @param program               GP tree
     */

    public MOData ( Individual program, int id ) {

        this.ID = id;
        this.program    = program;

        int length = param.Parameters.FITNESS_FUNCTIONS.size();

        BufferedImage img = getImage();

        Response r      = new Response(img);
        double fitn[];

        this.fitness    = new double[length];
        this.raw        = new double[length];
        this.ranks      = new int[length];

        // Calculates fitnesses
        for ( int i = 0; i < length; i++ ) {
            FitnessFunction f =
                    ((FitnessFunction)param.Parameters.FITNESS_FUNCTIONS.get(i));
            fitn = f.calculateFitness(r, img, param.Parameters.SOURCE);
            fitness[i] = fitn[0];
            raw[i] = fitn[1];
        }

        stats();
        
    };


    /**
     * Calculates the number of nodes and depth of this tree.
     */

    private void stats ( ) {

        nodes   = 0;
        depth   = 0;

        parse(((GPIndividual)program).trees[0].child);

    };


    /**
     * Recursive object which is called on a single node, increments the number
     * of total nodes, and tests the depth. This is also called on any children
     * the node may have.
     *
     * @param node              Current node
     */

    private void parse ( GPNode node ) {

        nodes++;

        int d = node.atDepth();
        if ( d > depth ) depth = d;

        for ( int i = 0; i < node.children.length; i++ )
            parse(node.children[i]);

    };
 

    /**
     * Returns the image produced by this individual's tree.
     *
     * @return                  Tree result image
     */

    public BufferedImage getImage ( ) {

        BufferedImage img =
        param.Parameters.PROBLEM.getData(param.Parameters.STATE, program, 0, 0);

        System.gc();

        return img;
        
    };


    /**
     * Returns the scaled image produced by this individual's tree.
     *
     * @param scale             Scale value to use
     * @return                  Tree result image
     */

    public BufferedImage getScaledImage ( float scale ) {

        int oldW = param.Parameters.IMAGEW;
        int oldH = param.Parameters.IMAGEH;

        param.Parameters.SCALE = scale;
        param.Parameters.IMAGEW = (int)((float)param.Parameters.IMAGEW * param.Parameters.SCALE);
        param.Parameters.IMAGEH = (int)((float)param.Parameters.IMAGEH * param.Parameters.SCALE);

        BufferedImage img =
        param.Parameters.PROBLEM.getData(param.Parameters.STATE, program, 0, 0);

        param.Parameters.SCALE = 1.0f;
        param.Parameters.IMAGEW = oldW;
        param.Parameters.IMAGEH = oldH;

        System.gc();

        return img;

    };


    /**
     * Returns a string representation of this individual's tree.
     *
     * @return                  Tree as a string (single line)
     */

    public String getTree ( ) {

        String tree;
        StringWriter s = new StringWriter();
        PrintWriter out = null;

        try {
            out = new PrintWriter(s);
        } catch ( Exception ex ) {};

        program.printIndividual(param.Parameters.STATE, out);

        tree = s.toString();
        String list2[] = tree.split("\n");

        out.close();
        try { s.close(); } catch ( Exception e ) {};

        return list2[list2.length - 1];

    };


    /**
     * Updates the KozaFitness object of this individual with its rank.
     */
    
    public void updateFitness ( ) {

        KozaFitness f = ((KozaFitness)program.fitness);

        f.setStandardizedFitness(param.Parameters.STATE, this.fit);

    };

    
};