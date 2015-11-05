package interfaces;

import java.awt.Color;
import javax.swing.text.*;


/**
 * This display class is used to make changes to the GA status window.
 *
 * @author Steve Bergen - Brock University
 * @version 1.0.0 - 25/04/2009
 */

public class Console {

    
    private javax.swing.JTextPane console;
    private Style def;
    private StyledDocument doc;
    
    
    /**
     * Class constructor. Passes the console object to this class for use in
     * writing to the display.
     *
     * @param console       The Java TextPane used for writing
    */
    
    public Console ( javax.swing.JTextPane console ) {
        
        this.console = console;
        
        // Set text styles
        def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);
        doc = console.getStyledDocument();

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");
        
    };
    
    
    /**
     * Called to clear a textpane (console).
    */
    
    public void clear ( ) {
    
        console.setText("");
        
    };
    
    
    /**
     * Writes an error to the console.
     *
     * @param s    The error string to be printed
    */
    
    public void writeError ( String s ) {
    
        
        StyleConstants.setForeground(def, new Color(128, 0, 0));

        try {
            doc.insertString(doc.getLength(), s + "\n", def);
        } catch (Exception ex) { System.out.println("S");}
        
    };
    
    
    /**
     * Writes a success message to the console.
     *
     * @param s    The success string to be printed
    */
    
    public void writeSuccess ( String s ) {
    
        StyleConstants.setForeground(def, new Color(0, 128, 0));

        try {
            doc.insertString(doc.getLength(), s + "\n", def);
        } catch (Exception ex) { System.out.println("S");}
        
    };
    
    
    /**
     * Writes an message to the console.
     *
     * @param s    The string to be printed
    */
    
    public void writeMsg ( String s ) {
    
        StyleConstants.setForeground(def, new Color(0, 0, 0));

        try {
            doc.insertString(doc.getLength(), s + "\n", def);
        } catch (Exception ex) { System.out.println("S");}
        
    };
    
    
};