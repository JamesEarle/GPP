/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.gui;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

/**
 * Swing JPanel that uses a TextureImage to provide the image displayed in 
 * the panel
 * 
 * @author Andy Gibson
 */
public class TexturePanel extends JPanel implements ComponentListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6250433540407027959L;
	
	private TextureImage image = new TextureImage(32,32);

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image.getImage(), 0, 0, image.getWidth(),image.getHeight(), this);
    }

    public TextureImage getImage() {
        return image;
    }

    public void setImage(TextureImage image) {
        this.image = image;            
    }

    
    public void componentResized(ComponentEvent e) {
    	System.out.println("Resized"+getHeight());
        if (getWidth() != 0 && getHeight() != 0) {
        image.resize(getWidth(),getHeight());
        }
    }

    public void componentMoved(ComponentEvent e) {        
    }

    public void componentShown(ComponentEvent e) {        
        image.resize(getWidth(),getHeight());
    }

    public void componentHidden(ComponentEvent e) {      
    }

}


