package util;

import java.awt.Color;


/**
 * This class represents a single color node in a Color Octree. The node 
 * contains functions needed to add a child and retrieve and convert color
 * values between binary and decimal, which is how the octree is referenced.
 *
 * @author Steve Bergen - Brock University
 * @version 1.0.0 - 25/04/2009
 */

public class ONode {

    /** Octree class using referencing this node */
    public  Octree      main;            
    /** The number of times this node has been referenced */
    public  int         references = 0;         
    /** The storage index for this node */
    public  int         index = -1;
    /** The list of children (8), each representing a diffent binary value */
    public  ONode       child[];               
    /** The decimal RGB value of this node  */
    public  int         r, g, b;               
    /** The depth of this node (0..8) */
    public  int         depth = 0;                
    /** Boolean value representing if this node has any children */
    public  boolean     hasChildren = false;
    
    
    /**
     * Class constructor. Creates an empty node with no children.
     * 
     * @param depth     The current depth this node is created at
     * @param main      The tree referencing this node
    */
    
    public ONode ( int depth, Octree main ) {
        
        this.main = main;                           
        
        this.depth = depth;
        if ( depth == 8 ) main.numNodes++;
        
        child = new ONode[8];
        for ( int i = 0; i < 8; i++ ) child[i] = null;
        
    };
    
    
    /**
     * Adds the RGB color to the octree from this node.
     *
     * @param  R        Red binary values
     * @param  G        Green binary values
     * @param  B        Blue binary values
     * @param  r        Red integer values
     * @param  g        Green integer values
     * @param  b        Blue integer values
    */
    
    public void addColor ( boolean R[], boolean G[], boolean B[], 
            int r, int g, int b ) {
        
        // At depth 8, initialize this node to a color
        if ( depth == 8 ) {

            this.r = r;
            this.g = g;
            this.b = b;
            this.references++;

        // Otherwise move down the tree another level
        } else {
            
            // Retrieve highest order bit
            boolean num[] = new boolean[3];
            num[0] = R[depth];
            num[1] = G[depth];
            num[2] = B[depth];
            
            // Convert bits to 3-bit number
            int ind = Binary.getValue(0, 3, num);
            int d = this.depth + 1;
            
            hasChildren = true;
            
            // Create child node and move down the tree
            if ( child[ind] == null ) child[ind] = new ONode(d, main);
            child[ind].addColor(R, G, B, r, g, b);
            
        }
   
    };
    
    
    /**
     * Parses this branch of the tree, and sums up all of the child references
     * where the total of all child references is less than the minimum. This 
     * function is best called after setMin is called on the root node.
     * 
     * @return          The total of all child references
    */
    
    public int addChildren ( ) {
        
        if ( !hasChildren ) return references;
        
        // Sum up all child references
        int total = 0;
        for ( int i = 0; i < 8; i++ ) 
            if (child[i] != null) total += child[i].addChildren();
        
        // If total is less than the minimum, set it
        if ( total <= main.min ) {
            main.min = total;
            main.minNode = this;
            sumChildren();
        }

        return total;
        
    };
    
    
    /**
     * Reduces this node by summing up all the children references of this node 
     * and removing all other references to these children.
    */
    
    public void sumChildren ( ) {
        
        int total       = 0;
        int children    = 0;
        int R           = 0;
        int G           = 0;
        int B           = 0;
        
        // Sums up all children and removes references
        for ( int i = 0; i < 8; i++ ) {
            
            if ( child[i] != null ) {
                
                total   += child[i].references;
                R       += child[i].r;
                G       += child[i].g;
                B       += child[i].b;
                children++;
                child[i] = null;
                
            }
            
        }
        
        // Reduces the total number of nodes
        main.numNodes -= children - 1;
        
        // Get averages for the new color of this node
        this.references += total;
        this.r = R / children;
        this.g = G / children;
        this.b = B / children;
        
        hasChildren = false;
        
    };
    
    
    /**
     * Parses all children in order to set indexes. This should be called after
     * the octree is created and reduced.
    */
    
    public void parse ( ) {
        
        // If there are no children (leaf node) set its index
        if ( !hasChildren ) {
            
            index = main.index;
            main.index++;
            
        // Otherwise parse all children
        } else {
            
            for ( int i = 0; i < 8; i++ ) 
                if ( child[i] != null ) child[i].parse();
            
        }
      
    };
    
    
    /**
     * Looks through the octree and locates the minimum total of all children.
     * This need not be called with a return.
     * 
     * @return          The current minimum of the tree.
    */
    
    public int setMin ( ) {
        
        if ( !hasChildren ) {
            return references;
        }
        
        // Totals all children
        int total = 0;
        for ( int i = 0; i < 8; i++ ) 
            if ( child[i] != null )
                total += child[i].setMin();
        
        // Set the new min based on this total
        if ( total < main.min ) {
            main.min = total;
            main.minNode = this;
        }
        
        return total;
        
    };
    
    
    /**
     * Returns the color object represented by this octree.
     *
     * @param  R        Red binary values
     * @param  G        Green binary values
     * @param  B        Blue binary values
     * @param  r        Red integer values
     * @param  g        Green integer values
     * @param  b        Blue integer values
     * @return          The color object represented by the given RGB values
    */
    
    public Color getColor (boolean R[], boolean G[], boolean B[], 
            int r, int g, int b) {
        
        // If at a leaf node, return this color
        if ( !hasChildren ) return new Color(this.r, this.g, this.b);
        
        boolean num[] = new boolean[3];
        num[0] = R[depth];
        num[1] = G[depth];
        num[2] = B[depth];
            
        // Parse necessary child node
        int ind = Binary.getValue(0, 3, num);
        return child[ind].getColor(R, G, B, r, g, b);
        
    };
    
    
    /**
     * Increments the number of indexes in the tree by one, and calls this
     * function recursively on all children.
    */
    
    public void getIndex ( ) {
        
        if ( !hasChildren ) {
            
            main.colorIndex[main.cindex] = new Color(r, g, b);
            main.cindex++;
            
        } else 
           
            for ( int i = 0; i < 8; i++ ) 
                if ( child[i] != null ) child[i].getIndex();   
            
        
    };
    
    
};