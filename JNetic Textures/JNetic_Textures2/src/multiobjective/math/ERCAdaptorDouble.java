package multiobjective.math;


import ec.gp.*; 
import ec.EvolutionState; 
import ec.Problem; 
import ec.util.Code; 
import ec.util.DecodeReturn; 
import dataobjects.Data;

 
/** 
 * <p/> 
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation; either version 2 
 * of the License, or (at your option) any later version, 
 * provided that any use properly credits the author. 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU General Public License for more details at http://www.gnu.org 
 * </p> 
 * 
 * @author Olly Oechsle, University of Essex, Date: 17-Aug-2006 
 * @version 1.0 
 */ 
public abstract class ERCAdaptorDouble extends ERC { 
 
    double value; 
    String name; 
 
    public abstract double setNumber(final EvolutionState state,
            final int thread);
 
    public void resetNode(final EvolutionState state, final int thread) { 
        //value = state.random[thread].nextInt(4); 
        value = setNumber(state, thread); 
        //System.out.println("Created ERC: " + value); 
    } 
 
    public int nodeHashCode() { 
        // a reasonable hash code 
        return this.getClass().hashCode() + Float.floatToIntBits((float) value); 
    } 
 
    public boolean nodeEquals(final GPNode node) { 
        // check first to see if we're the same kind of ERC -- 
        // won't work for subclasses; in that case you'll need 
        // to change this to isAssignableTo(...) 
        if (this.getClass() != node.getClass()) return false; 
        // now check to see if the ERCs hold the same value 
        return (((ERCAdaptorInt) node).value == value); 
    } 
 
    public String encode() { 
        return Code.encode(value); 
    } 
 
    public boolean decode(DecodeReturn dret) { 
        // store the position and the string in case they 
        // get modified by Code.java 
        int pos = dret.pos; 
        String data = dret.data; 
 
        // decode 
        Code.decode(dret); 
 
        if (dret.type != DecodeReturn.T_DOUBLE) // uh oh! 
        { 
            // restore the position and the string; it was an error 
            dret.data = data; 
            dret.pos = pos; 
            return false; 
        } 
 
        // store the data 
        value = dret.d; 
        return true; 
    } 
 
    public void eval(final EvolutionState state, final int thread, 
            final GPData input, final ADFStack stack,
            final GPIndividual individual, final Problem problem) {
        Data rd = ((Data) (input)); 
        rd.resultd = value; 
    } 
 
} 
