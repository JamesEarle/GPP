/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */

package org.texgen.utils;

/**
 * Class representing colors using Red,Green,Blue and Alpha values. Includes
 * methods to modify and merge colors.
 * 
 * @author Andy Gibson
 */
public class RGBAColor {

	private int red, green, blue;
	private double alpha;

	public static RGBAColor black() {
		return new RGBAColor(0, 0, 0, 1);
	}

	public static RGBAColor white() {
		return new RGBAColor(255, 255, 255, 1);
	}

	public static RGBAColor red() {
		return new RGBAColor(255, 0, 0, 1);
	}

	public static RGBAColor green() {
		return new RGBAColor(0, 255, 0, 1);
	}

	public static RGBAColor blue() {
		return new RGBAColor(0, 0, 255, 1);
	}

	public static RGBAColor yellow() {
		return new RGBAColor(255, 255, 0, 1);
	}

	public static RGBAColor orange() {
		return new RGBAColor(255, 128, 0, 1);
	}

	/** Creates a new instance of RGBColor */
	public RGBAColor() {
		this(0, 0, 0, 1d);
	}

	public RGBAColor(int color) {
		setColor(color);
	}

	public RGBAColor(int red, int green, int blue) {
		this(red, green, blue, 1);
	}

	public RGBAColor(int red, int green, int blue, double alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public RGBAColor(double red, double green, double blue) {
		this(red, green, blue, 1);
	}

	public RGBAColor(double red, double green, double blue, double alpha) {
		setColor(red, green, blue, alpha);
	}

	public RGBAColor(RGBAColor color) {
		this.red = color.red;
		this.green = color.green;
		this.blue = color.blue;
		this.alpha = color.alpha;
	}

	public void interpolate(RGBAColor color, double level) {
		red = ((int) Gradient.cosInterpolate(red, color.red, level));
		green = ((int) Gradient.cosInterpolate(green, color.green, level));
		blue = ((int) Gradient.cosInterpolate(blue, color.blue, level));
		alpha = Gradient.cosInterpolate(alpha, color.alpha, level);
	}

	public void merge(RGBAColor color) {
		merge(color, color.alpha);
	}

	public void merge(RGBAColor color, double level) {
		red = ((int) Gradient.cosInterpolate(red, color.red, level));
		green = ((int) Gradient.cosInterpolate(green, color.green, level));
		blue = ((int) Gradient.cosInterpolate(blue, color.blue, level));
		alpha = Gradient.cosInterpolate(alpha, color.alpha, level);
	}

	public void scale(double value) {
		red = (int) (red * value);
		green = (int) (green * value);
		blue = (int) (blue * value);
	}

	public int getRGB() {
		int res = red;
		res = res << 8;
		res = res + green;
		res = res << 8;
		res = res + blue;
		return res;
	}

	public void scaleAlpha(double coef) {
		alpha = alpha * coef;
	}

	protected int negateValue(int value) {
		value = 255 - value;
		if (value < 0) {
			value = 0;
		}
		if (value > 255) {
			value = 255;
		}
		return value;
	}

	public void negate() {
		red = negateValue(red);
		blue = negateValue(blue);
		green = negateValue(green);
	}

	protected int clampValue(int value) {
		if (value > 255) {
			value = 255;
		}
		if (value < 0) {
			value = 0;
		}
		return value;
	}

	public void clamp() {
		red = clampValue(red);
		green = clampValue(green);
		blue = clampValue(blue);
		alpha = GraphUtils.clamp(alpha);
	}

	public String toString() {
		return new StringBuilder().append("RGB Color : ").append("Red = ")
				.append(red).append(", Green = ").append(green).append(
						", Blue = ").append(blue).append(", Alpha= ").append(
						alpha).toString();
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public void setColor(RGBAColor color) {
		this.red = color.red;
		this.blue = color.blue;
		this.green = color.green;
		this.alpha = color.alpha;
	}

	public void setColor(int red, int green, int blue) {
		setColor(red, green, blue, 1d);
	}

	public void setColor(int red, int green, int blue, double alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public void setColor(double red, double green, double blue, double alpha) {
		this.red = (int) (red * 255);
		this.green = (int) (green * 255);
		this.blue = (int) (blue * 255);
		this.alpha = alpha;
	}

	public void add(RGBAColor color) {
		if (color != null) {
			this.red += color.red;
			this.green += color.green;
			this.blue += color.blue;
			this.alpha += color.alpha;
		}
	}

	public void mulitply(RGBAColor color) {
		if (color != null) {
			this.red = (this.red * color.red) / 255;
			this.green = (this.green * color.green) / 255;
			this.blue = (this.blue * color.blue) / 255;
			this.alpha = this.alpha * color.alpha;

		}
	}

	private void setColor(int color) {
		this.blue = color & 255;
		color = color >> 8;
		this.green = color & 255;
		color = color >> 8;
		this.red = color & 255;
	}
	
	public void setGreyScale(double level) {
		setRed((int)(level * 255));
		setGreen((int)(level * 255));
		setBlue((int)(level * 255));
		setAlpha(1);
	}

}
