package listeners;


import java.awt.event.*;


/**
 * Listener object that can be attached to any window. This particular object
 * disables the main window until the current one is closed.
 *
 * @author Steve Bergen
 */

public class FrameListener implements WindowListener {


    public javax.swing.JFrame       callingwindow;
    public javax.swing.JFrame       openwindow;


    public FrameListener ( javax.swing.JFrame callingwindow,
            javax.swing.JFrame openwindow ) {

        super();

        this.callingwindow  = callingwindow;
        this.openwindow     = openwindow;

        if ( callingwindow != null ) callingwindow.setEnabled(false);

        openwindow.setLocationRelativeTo(callingwindow);
        openwindow.setDefaultCloseOperation(
                javax.swing.JFrame.DO_NOTHING_ON_CLOSE);

    };


    @Override
    public void windowClosing( WindowEvent arg0 ) {

        if ( callingwindow != null ) this.callingwindow.setEnabled(true);
        this.openwindow.setVisible(false);

    };
    
    @Override
    public void windowClosed ( WindowEvent arg0 ) {};

    @Override
    public void windowActivated ( WindowEvent arg0 ) {};

    @Override
    public void windowDeactivated ( WindowEvent arg0 ) {};

    @Override
    public void windowDeiconified ( WindowEvent arg0 ) {};

    @Override
    public void windowIconified ( WindowEvent arg0 ) {};

    @Override
    public void windowOpened ( WindowEvent arg0 ) {};


};