/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.signals;

import org.texgen.textures.AbstractChannelSignal;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Returns a signal based on the returned value from a texture
 * The returned signal can consist of any of the components (red, green, blue or alpha)
 * 
 * @author Andy Gibson
 */
public class TextureSignal extends AbstractChannelSignal {


    private final Texture source;
    private final Channel channel;

    public TextureSignal(Texture source) {
        this(source,Channel.ALPHA);
    }
    public TextureSignal(Texture source, Channel channel) {
        this.source = source;
        this.channel = channel;
    }

    

    public double getValue(double u, double v) {
        if (source != null) {
            RGBAColor col = source.getColor(u, v);
            
            switch (channel) {
                case ALPHA : return (double)col.getAlpha();
                case BLUE : return (double)col.getBlue()/255;
                case GREEN : return (double)col.getGreen()/255;
                case RED : return (double)col.getRed()/255;                
            }
        }
        return 0;
        
    }

    public Texture getSource() {
        return source;
    }

    public Channel getChannel() {
        return channel;
    }
    
    
    
}
