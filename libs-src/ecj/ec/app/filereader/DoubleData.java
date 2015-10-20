/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/
package ec.app.filereader;
import ec.gp.*;

public class DoubleData extends GPData {
    
    public double x;

    @Override
    public void copyTo(final GPData gpd) { 
        ((DoubleData)gpd).x = x; 
    }    
}


