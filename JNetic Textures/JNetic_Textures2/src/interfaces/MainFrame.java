package interfaces;


/**
 * This class is used to run the main window in a separate thread.
 *
 * @author Steve Bergen
 */

public class MainFrame extends Thread {
    

    public MainFrame ( ) {
        
    };
    
    
    /**
     * Overrides the Thread function 'run'.
     */
    @Override
    public void run ( ) {

        param.Parameters.init();

        new Frame().setVisible(true);
        
    };
    
    
};