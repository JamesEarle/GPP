package util;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

import multiobjective.*;

import org.apache.batik.svggen.*;
import org.apache.batik.dom.svg.SVGDOMImplementation;

import org.w3c.dom.svg.*;
import org.w3c.dom.DOMImplementation;


/**
 * This class can be used for the purposes of loading and saving BufferedImages
 * from and to files.
 *
 * @author Steve Bergen - Brock University
 * @version 1.0.0 - 25/04/2009
 */

public class ImageUtil {
    
    
    /**
     * Return the color of the pixel at (x, y) of supplied image.
     * 
     * @param name      The file name to be loaded (no extension)  
     * @return          BufferedImage from the specified file
    */
    
    public static BufferedImage loadImage ( String name ) {
      
        BufferedImage base = null;
        
        try {
            base = ImageIO.read(new File(name));
        } catch ( IOException e ) {
            System.out.println("Error loading image");
        }
        
        return base;

    };
    
    
    /**
     * Saves an image file from a BufferedImage with file and path broken up.
     * 
     * @param base      The image to be saved
     * @param name      The file name to be saved as (no extension) 
     * @param path      The absolute path of the new file
     * @param format    The image file extension 
    */
    
    public static void saveImage ( BufferedImage base, String name, String path, String format ) {
        
        try {
            File outputfile = new File(path + name + format);
            ImageIO.write(base, "png", outputfile);
        } catch ( IOException e ) { 
            System.out.println("Error saving image");
        }
        
    };


    /**
     * Saves an image file from a BufferedImage with file and path together
     *
     * @param base      The image to be saved
     * @param path      The absolute path of the new file with name and ext
    */

    public static void saveImage ( BufferedImage base, String path, String format ) {

        try {
            File outputfile = new File(path + "." + format);
            ImageIO.write(base, format, outputfile);
        } catch ( IOException e ) {
            System.out.println("Error saving image");
        }

    };
    
    
    /**
     * Saves an image file from a BufferedImage with file and path together
     * 
     * @param base      The image to be saved
     * @param path      The absolute path of the new file with name and ext
    */
    
    public static void saveImage ( BufferedImage base, String path ) {
        
        try {
            File outputfile = new File(path);
            ImageIO.write(base, "png", outputfile);
        } catch ( IOException e ) {
            System.out.println("Error saving image");
        }
        
    };


    /**
     * Saves an SVG file from a Graphics2D object with file and path together
     *
     * @param globals   Contains data to render image
     * @param index     Population index
     * @param path      The absolute path of the new file with name and ext
    */

    public static void saveSVG ( MOData ind, float scale, String path ) {

        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(doc);

        param.Parameters.SVG = svgGenerator;
        
        int oldW = param.Parameters.IMAGEW;
        int oldH = param.Parameters.IMAGEH;

        param.Parameters.SCALE = scale;
        param.Parameters.IMAGEW = (int)((float)param.Parameters.IMAGEW * param.Parameters.SCALE);
        param.Parameters.IMAGEH = (int)((float)param.Parameters.IMAGEH * param.Parameters.SCALE);

        ind.getImage();

        param.Parameters.SCALE = 1.0f;
        param.Parameters.IMAGEW = oldW;
        param.Parameters.IMAGEH = oldH;

        svgGenerator.setSVGCanvasSize(new Dimension((int)((float)param.Parameters.IMAGEW * scale), (int)((float)param.Parameters.IMAGEH * scale)));

        try {

            Writer output = null;
            File file = new File(path + ".svg");
            output = new BufferedWriter(new FileWriter(file));
            boolean useCSS = true;
            svgGenerator.stream(output, useCSS);

        } catch (IOException e) { }

        param.Parameters.SVG.dispose();
        param.Parameters.SVG = null;

    };
    
    
};