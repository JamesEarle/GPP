/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.composite;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.ChannelSignal;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Takes two input textures (A and B) and merges them based on the input from a 
 * channel source.<br/>
 * <pre>
 * colA = sourceA.getColor(u,v);
 * colB = sourceB.getColor(u,v);
 * filter = filter.getValue(u,v);
 * 
 * result = (colA * (1-filter)) + (colB * filter);
 * </pre>
 * 
 * @author Andy Gibson
 */
public class SignalMixer extends AbstractTexture {

    private final Texture sourceA;
    private final Texture sourceB;
    private final ChannelSignal filter;

    public SignalMixer(Texture sourceA, Texture sourceB, ChannelSignal filter) {
        this.sourceA = sourceA;
        this.sourceB = sourceB;
        this.filter = filter;
    }

    public Texture getSourceA() {
        return sourceA;
    }

    public Texture getSourceB() {
        return sourceB;
    }
    public ChannelSignal getFilter() {
        return filter;
    }

    public void getColor(double u, double v,RGBAColor value) {
        calculateColorFromTexture(u, v, sourceA,value);
        RGBAColor colB = calculateColorFromTexture(u, v, sourceB);
        double filterValue = calculateSignalFromSignal(u, v, filter);

        //merge the two colors A + B based on the value of the filter
        value.merge(colB,filterValue);        

    }
}
