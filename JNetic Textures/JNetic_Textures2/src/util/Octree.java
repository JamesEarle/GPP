package util;

import java.awt.image.BufferedImage;
import java.awt.Color;


/**
 * This class is used to perform color quantization upon an image. It takes an
 * image in as input, and counts the number of color references of every color
 * used in the image, stored in an octree. The octree is reduced after all 
 * colors are added, and the remaining N colors are returned as a list for
 * later use. This can be used to reduce an image to an N-color one.
 *
 * @author Steve Bergen - Brock University
 * @version 1.0.0 - 25/04/2009
 */

public class Octree {
    
    /** The total number of nodes in the tree */
    public  int     numNodes    = 0;               
    /** The current node with the lowest number of references */
    public  ONode   minNode     = null;             
    /** The weight of the node with the lowest number of references */
    public  int     min         = Integer.MAX_VALUE;          
    /** The number of indexes (references) in this tree */
    public  int     index       = 0;
    /** The current color index */
    public  int     cindex      = 0;
    /** The color index array */
    public  Color   colorIndex[];
    
    /** The color representation of this image (default 256) */
    private int     K           = 256;                                   
    /** The root node of this tree */
    private ONode   oct;       
    /** The base image to be reduced to K-colors */
    private BufferedImage   base;     


    /**
     * Class constructor. Creates a color octree from the image with K colors.
     * 
     * @param base      The base image to be parsed for colors
     * @param K         The number of most common colors to be found
    */
    
    public Octree ( BufferedImage base, int K) {
        
        this.base = base;
        this.K = K;
        
        create();
        makeTable();
        
    };
    
    
    /**
     * Creates the octree from the image, and reduces the number of colors
     * in that tree to K. Often, the number of colors will be less than K.
    */
    
    private void create ( ) {
        
        oct = new ONode(0, this);
        
        // Add all colors to the octree from the base image
        for ( int i = 0; i < base.getWidth(); i++ ) {
            for ( int j = 0; j < base.getHeight(); j++ ) {
                Color c = ColorUtil.getPixel(i, j, base);
                addColor(c.getRed(), c.getGreen(), c.getBlue(), oct);
            }
        }
        
        // Reduce the number of colors in the tree
        while ( numNodes > K ) {
            minNode = null;
            min = Integer.MAX_VALUE;
            oct.setMin();
            oct.addChildren();
        }

        // Parses the tree and sets up indexing
        oct.parse();
        
    };
    
    
    /**
     * Adds a color to the root node. The color will be dropped down the tree
     * according to its RGB values, until a depth of 8.
     * 
     * @param r         Red value
     * @param g         Green value
     * @param b         Blue value
     * @param oct       The node the color will be added to
    */
    
    private void addColor ( int r, int g, int b, ONode oct ) {
        
        boolean R[] = Binary.toBinary(r, 8);            // Color conversions
        boolean G[] = Binary.toBinary(g, 8);
        boolean B[] = Binary.toBinary(b, 8);
        
        oct.addColor(R, G, B, r, g, b);                 
        
    };

    
    /**
     * Returns the original image which was passed in.
     * 
     * @return          The image passed in (original)
    */
    
    public BufferedImage getImage ( ) {
        
        return base;
        
    };
    
    
    /**
     * Creates the color table of the tree's remaining colors, parsing the tree
     * from the root node down.
    */
    
    private void makeTable ( ) {
        
        colorIndex = new Color[numNodes];
        oct.getIndex();
        
    };

    
};