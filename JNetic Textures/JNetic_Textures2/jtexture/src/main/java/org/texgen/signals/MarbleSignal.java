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

/**
 * Signal to use for creating marble-type textures using the noisy sine 
 * functions. Includes a builder to help manage parameters to the constructor.
 * 
 * @author Andy Gibson
 */
public class MarbleSignal extends AbstractChannelSignal {
    
    private final double sineScale;
    private final double size;
    private final int octaves;
    private final double speed;
    private final double uOffset;
    private final double vOffset;

    public MarbleSignal() {
        this(0,0,5);
    }
    
    public MarbleSignal(double uOffset,double vOffset,double sineScale) {
        this(uOffset,vOffset,sineScale,1,10,4);
    }
    
    /**
     * 
     * @param uOffset
     *   Value to add to u value (prevents repeating patterns)
     * @param vOffset
     *   Value to add to v value (prevents repeating patterns)
     * @param sineScale
     *   Rate of change of the basic sine element.
     * @param speed
     *   Rate of change of noise of the surface
     * @param octaves
     *   Number of octaves to use to generate the noise
     * @param size
     *   Scalar value to determine the size of the returned noise.   
     */
    public MarbleSignal(double uOffset,double vOffset,double sineScale, double speed,int octaves,double size ) {
        this.sineScale = sineScale;
        this.size = size;
        this.octaves = octaves;
        this.speed = speed;
        this.uOffset = uOffset;
        this.vOffset = vOffset;
    }
    
    
    public double getValue(double u, double v) {
        return getNoise().noisySine(u+uOffset, v+vOffset, sineScale,speed,octaves) * size;
    }

	public double getScale() {
		return sineScale;
	}

	public double getSize() {
		return size;
	}


	public int getOctaves() {
		return octaves;
	}

	public double getSpeed() {
		return speed;
	}

	public double getUOffset() {
		return uOffset;
	}

	public double getVOffset() {
		return vOffset;
	}
    
	public static class Builder {

	    private double sineScale = 5;
	    private double size =1;
	    private int octaves = 10;
	    private double speed = 4;
	    private double uOffset = 0;
	    private double vOffset = 0;
	    
		public Builder setSineScale(double sineScale) {
			this.sineScale = sineScale;
			return this;
		}
		public Builder setSize(double size) {
			this.size = size;
			return this;
		}
		public Builder setOctaves(int octaves) {
			this.octaves = octaves;
			return this;
		}
		public Builder setSpeed(double speed) {
			this.speed = speed;
			return this;
		}
		public Builder setUOffset(double offset) {
			uOffset = offset;
			return this;
		}
		public Builder setVOffset(double offset) {
			vOffset = offset;
			return this;
		}
		
		public MarbleSignal build() {
			return new MarbleSignal(uOffset,vOffset,sineScale,speed,octaves,size);
		}

	    
	}

}
