package interfaces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.image.*;
import java.awt.geom.Rectangle2D;
import java.awt.Color;


/**
 * A JPanel extension class which holds and displays a chromosome image as 
 * an icon.
 *
 * @author Steve Bergen - Brock University
 * @version 1.0.0 - 25/04/2009
 */

public class ImageDisplayer extends JPanel {
    
    
    private     BufferedImage           image;          // Imported Image
    private     BufferedImage           temp;

    
    /**
     * Class constructor. Passes an image object for display
     *
     * @param image       The image to display as an icon
    */
    
    public ImageDisplayer ( BufferedImage image ) {
        
        super();
        
        // Creates and stores the image
        temp = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = temp.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle2D.Double(0, 0, 1000, 1000));
        g2d.dispose();

        this.image = image;
      
    };
    
    
    /**
     * Sets a new image to the icon of this panel.
     *
     * @param image       The image to display as an icon
    */
    
    public void setImage ( BufferedImage image ) {
        
        this.image = image;
        this.repaint();
        
    };
    
    
    @Override
    public void paint(Graphics g) {
        
        Graphics2D g2D = (Graphics2D) g;
        if (image != null) g2D.drawImage(image, null, 0, 0);
        else g2D.drawImage(temp, null, 0, 0);

    };
    

};