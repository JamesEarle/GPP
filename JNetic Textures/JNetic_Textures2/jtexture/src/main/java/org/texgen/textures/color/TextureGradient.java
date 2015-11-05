/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures.color;

import java.util.ArrayList;
import java.util.List;

import org.texgen.textures.AbstractTexture;
import org.texgen.textures.ChannelSignal;
import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

/**
 * Calculates the texture color based on the input signal and a set of textures
 * that is interpolates between as the signal goes from 0..1
 * 
 * @author Andy Gibson
 */
public class TextureGradient extends AbstractTexture {

	private final ChannelSignal source;
	private final List<TextureLevel> levels = new ArrayList<TextureLevel>();

	public TextureGradient(ChannelSignal source) {
		super();
		this.source = source;
	}
	
	private class TextureLevel {
		private final double min;
		private final double max;
		private final double upperBorder;
		private final double lowerBorder;
		private final Texture source;

		public TextureLevel(double min, double max, double border, Texture source) {
			this(min,max,border,border,source);
			
		}
		public TextureLevel(double min, double max, double lowerBorder, double upperBorder, Texture source) {
			super();
			if (min >= max) {
				throw new IllegalArgumentException("Min must be less than max");
			}
			this.min = min;
			this.max = max;
			this.upperBorder= upperBorder;
			this.lowerBorder = lowerBorder;
			this.source = source;
		}

		public double getLevelAt(double value) {
			if (value <= min-lowerBorder) {
				return 0;
			}
			if (value >= max+upperBorder) {
				return 0;
			}

			if (value < max && value > min) {
				return 1;
			}
			
			if (value < min && lowerBorder ==0) {
				return 0;
			}
			
			if (value > max && upperBorder ==0) {
				return 0;
			}
			
			//phase out over border
			
			if (value >= max) {
				return 1-((value - max)/upperBorder);
			}
			
			if (value <= min) {
				return  1-((min - value)/lowerBorder);
			}
			return 0;
		}
	}

	public void getColor(double u, double v, RGBAColor value) {

		double val = source.getValue(u, v);

		double sum = 0;
		for (TextureLevel level : levels) {
			sum = sum + level.getLevelAt(val);
		}

		if (sum != 0) {
			sum = 1 / sum;
		}
		value.setColor(0, 0, 0, 0);
		RGBAColor temp = new RGBAColor();

		
		for (TextureLevel level : levels) {
			double c = level.getLevelAt(val);			
			if (c != 0) {
				calculateColorFromTexture(u, v, level.source, temp);
				temp.scale(c * sum);
				value.add(temp);
			}
		}
	}

	public void addLevel(double min, double max, double lowerBorder,double upperBorder,Texture texture) {
		TextureLevel level = new TextureLevel(min, max, lowerBorder,upperBorder, texture);
		levels.add(level);

	}
	public void addLevel(double min, double max, Texture texture) {
		TextureLevel level = new TextureLevel(min, max, 0.1,0.1,texture);
		levels.add(level);
	}
	
	public void addLevel(double min, double max, double border,Texture texture) {
		TextureLevel level = new TextureLevel(min, max, border,border,texture);
		levels.add(level);
	}
	
}
