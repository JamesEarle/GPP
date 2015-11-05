package interfaces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.image.*;


/**
 * This display class is used to show the base image, and stores the image
 * to use as a background.
 *
 * @author Steve Bergen - Brock University
 * @version 1.0.0 - 25/04/2009
 */

public class BaseDisplayer extends JPanel {
    

    public      BufferedImage           image;          // Imported Image
    
    
    /**
     * Class constructor. Generates a new image panel from the image.
     *
     * @param image         Image to be used as background
     * @param globals       Global variable object
    */
    
    public BaseDisplayer ( BufferedImage image ) {
        
      super();

      this.image = image;
      
    };
    
    
    @Override
    public void paint(Graphics g) {
        
        Graphics2D g2D = (Graphics2D) g;
        
        g2D.drawImage(image, null, 0, 0);
        
    };
    
};