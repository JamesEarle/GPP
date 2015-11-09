package ec.app.filereader;
import ec.*;
import ec.gp.*;
import ec.util.*;
/**
 * Top level abstract class as an extension of ERC from ECJ. 
 * Implement this class with some concrete Double.
 * @author JamesEarle
 */
public abstract class ERCAdaptorDouble extends ERC {
    
    double value;
    String name;
    
    @Override
    public String name() { return ""; }
    
    @Override
    public String toString() {
        // the call to encode is unreadable in the out.stat file, so override.
        return name() + "[" + value + "]";
    }
    
    @Override
    public void eval(final EvolutionState state, final int thread, 
            final GPData input, final ADFStack stack,
            final GPIndividual individual, final Problem problem) {
        DoubleData rd = ((DoubleData) (input)); 
        rd.x = value; 
    } 
    
    // Mandatory overridden methods below.
    @Override
    public int nodeHashCode() { return this.getClass().hashCode() + Float.floatToIntBits((float) value); }
    
    public abstract double setNumber(final EvolutionState state, final int thread);
    
    @Override
    public void resetNode(final EvolutionState state, final int thread) { 
        value = setNumber(state, thread); 
    }
    
    @Override
    public boolean nodeEquals(final GPNode node) { 
        if (this.getClass() != node.getClass()) return false; 
        return (((ERCAdaptorDouble) node).value == value); 
    } 
    
    @Override
    public String encode() {
        return Code.encode(value);
    }
    
    
}
