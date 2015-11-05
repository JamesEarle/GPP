package listeners;


import java.awt.event.*;


/**
 * Listener object that can be attached to any window. This particular object
 * does not disable the main window until the current one is closed.
 *
 * @author Steve Bergen
 */

public class FrameListenerConstant implements WindowListener {
    
    
    public javax.swing.JFrame   callingwindow;
    public javax.swing.JFrame   openwindow;
    
    
    public FrameListenerConstant ( javax.swing.JFrame callingwindow, 
            javax.swing.JFrame openwindow, Boolean open  ) {
        
        super();
        
        this.callingwindow  = callingwindow;
        this.openwindow     = openwindow;
        
        openwindow.setLocationRelativeTo(callingwindow);
        openwindow.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        
        openwindow.setVisible(open);
        
    };
    
    
    @Override
    public void windowClosed(WindowEvent arg0) {}
    
    @Override
    public void windowActivated(WindowEvent arg0) {}
    
    @Override
    public void windowClosing(WindowEvent arg0) {

        this.callingwindow.setEnabled(true);
        this.openwindow.setVisible(false);

    };
           
    @Override
    public void windowDeactivated(WindowEvent arg0) {} 
    
    @Override
    public void windowDeiconified(WindowEvent arg0) {}
    
    @Override
    public void windowIconified(WindowEvent arg0) {}
    
    @Override
    public void windowOpened(WindowEvent arg0) {}
    
};