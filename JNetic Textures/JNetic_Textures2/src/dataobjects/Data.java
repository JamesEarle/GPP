package dataobjects;


import ec.gp.*;
import java.awt.Color;
import org.texgen.textures.AbstractTexture;


/**
 * This class is an extension of an ECJ data object, uesed to pass information
 * back and forth in the GP tree.
 *
 * @author Steve Bergen
 */

public class Data extends GPData {
    
    
    public Color                color;          // Color
    public double               resultd;        // Double value
    public int                  resulti;        // Integer value
    public AbstractTexture      texture;        // Texture

    
    @Override
    public GPData copyTo(final GPData gpd) { 
        
        ((Data)gpd).resultd = resultd; 
        ((Data)gpd).resulti = resulti; 
        ((Data)gpd).color   = color; 
        ((Data)gpd).texture = texture;

        return gpd; 
        
    };
    
   
};