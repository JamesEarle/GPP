/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.gui;

import org.texgen.textures.Texture;

/**
 *
 * Helper class that quickly instantiates a TextureWindow and sets the texture 
 * to the texture parameter to let the user easily see results.
 * 
 * @author Andy Gibson
 */
public class TextureViewer implements Runnable {

    TextureWindow form = new TextureWindow();

    /**
     * Static method to display a window that will display the passed in texture
     * 
     * @param texture
     */
    public static void show(Texture texture, int width, int height,String caption) {
        TextureViewer viewer = new TextureViewer();
        viewer.form.setTexture(texture);
        viewer.form.setSize(width, height);
        viewer.form.setTitle(caption);
        java.awt.EventQueue.invokeLater(viewer);
    }

    public static void show(Texture texture, int width, int height) {
    	show(texture,width,height,"Caption");
    }
    public static void show(Texture texture) {
        show(texture, 300, 300,"Texture");
    }

    public void run() {
        form.setVisible(true);
    }
}
