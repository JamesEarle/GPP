/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.app.filereader;

import ec.EvolutionState;

/**
 *
 * @author JamesEarle
 */
public class ERCDouble extends ERCAdaptorDouble {
    
    //override double and string here?
    
    @Override
    public String name() {
        return "C";
    }
    
    @Override
    public double setNumber(final EvolutionState state, final int thread) {
        return state.random[thread].nextDouble() * 10.0;
    }

    
    
    
    
    
}
