package org.texgen.utils;

import org.texgen.textures.ChannelSignal;
import org.texgen.textures.Texture;
import org.texgen.textures.color.SolidTexture;
import org.texgen.textures.color.TextureGradient;
import org.texgen.textures.complex.Dirty;
import org.texgen.utils.RGBAColor;

/**
 * Helper class that contains some useful terrain textures and also combines
 * them into a terrain texture to produce a textured terrain based on the
 * textures defined here.
 * 
 * @author Andy Gibson
 * 
 */
public abstract class TerrainTexture {

	/**
	 * @param signal
	 *            Signal to determine terrain heights from
	 * 
	 * @return Texture that uses terrain colors to create texture that will
	 *         produce a texture map for a terrain
	 */
	public static Texture genTexture(ChannelSignal signal) {

		TextureGradient result = new TextureGradient(signal);
		result.addLevel(0, 0.2, 0, 0.01, getBeach());
		result.addLevel(0.2, 0.6, 0, 0.1, getGrass());
		result.addLevel(0.6, 0.65, 0.1, 0.1, getBlackRock());
		result.addLevel(0.7, 0.9, 0.1, 0, getBrownRock());
		result.addLevel(0.9, 1.2, 0, getSnow());

		return result;
	}

	public static Texture getSnow() {
		return new Dirty(new SolidTexture(RGBAColor.white()),
				RGBAColor.black(), 210, 10, 0.25);
	}

	public static Texture getBrownRock() {
		return new Dirty(new SolidTexture(new RGBAColor(100, 86, 40)),
				new RGBAColor(0, 0, 0), 280, 10, 0.55);
	}

	public static Texture getBlackRock() {
		return new Dirty(new SolidTexture(new RGBAColor(38, 27, 13)),
				new RGBAColor(0, 0, 0), 280, 10, 0.55);
	}

	public static Texture getGreyRock() {
		return new Dirty(new SolidTexture(new RGBAColor(100, 100, 100)),
				new RGBAColor(0, 0, 0), 305, 13, 0.45);
	}

	public static Texture getGrass() {
		Texture grassCol = new Dirty(new RGBAColor(74, 140, 29), 472, 14, 0.6);
		Texture dirtCol = new Dirty(new RGBAColor(86, 70, 31), 345, 13, 0.9);
		Texture grass = new Dirty(grassCol, dirtCol, 352, 15, 0.5);
		grass = new Dirty(grass, new RGBAColor(40, 80, 20), 359, 15, 0.8);
		// grass=dirtCol;
		return grass;
	}

	public static Texture getBeach() {
		return new Dirty(new SolidTexture(new RGBAColor(216, 170, 50)),
				new RGBAColor(100, 64, 30), 400, 12, 0.5);
	}

}
