/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.gui;

/**
 * Object simplementing this interface can be notified of any updates in the 
 * rendering process.
 * 
 * @author Andy Gibson
 */
public interface RenderListener {

    void notifyComplete(Object source);
    void notifyState(Object source,String message);
    
}
