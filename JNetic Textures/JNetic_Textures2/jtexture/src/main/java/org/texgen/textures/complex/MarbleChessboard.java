/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.complex;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.Texture;
import org.texgen.textures.color.NegateTexture;
import org.texgen.textures.pattern.Checker;
import org.texgen.utils.RGBAColor;

/**
 * A Marble chessboard texture based on the checker and two marble type textures.
 *
 * @author Andy Gibson
 */
public class MarbleChessboard extends AbstractTexture {
        
    private final Texture blackMarble;
    private final Texture whiteMarble;    
    private final Texture finalTexture;

    /**
     * Constructs an instance of Marble Chessboard
     */
    public MarbleChessboard() {
        blackMarble = new ComplexMarble(1,120,120);
        whiteMarble = new ComplexMarble();

        Texture blackSquare = blackMarble;
        Texture whiteSquare = new NegateTexture(whiteMarble);
                
           
        finalTexture = new Dirty(new Checker(blackSquare,whiteSquare));        
                
        
    }

    public void getColor(double u, double v,RGBAColor value) {
        //return checker.getSourceA().getColor(u, v);
        //calculateColorFromTexture(u, v, finalTexture,value);        
        calculateColorFromTexture(u, v, finalTexture,value);        
    }

}
