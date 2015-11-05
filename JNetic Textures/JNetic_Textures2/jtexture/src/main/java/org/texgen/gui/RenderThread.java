package org.texgen.gui;

import org.texgen.textures.Texture;
import org.texgen.utils.RGBAColor;

public class RenderThread implements Runnable {

	private TextureImage image;
	private final Texture texture;
	private final int startPosition;
	private final int renderSize;

	public RenderThread(TextureImage image, Texture texture, int startPosition,
			int renderSize) {
		super();
		this.image = image;
		this.texture = texture;
		this.startPosition = startPosition;
		this.renderSize = renderSize;
	}

	public void run() {
		render(texture);
	}

	private void render(Texture texture) {

		RGBAColor col = new RGBAColor();
		RGBAColor temp = new RGBAColor();
		int startLevel = image.isGradualDisplay() ? 8 : 1;

		for (int size = startLevel; size >= 1; size = size - 1) {
			double du = 0.5 * size / image.getWidth();
			double dv = 0.5 * size / image.getHeight();

			for (int x = startPosition; x < startPosition + renderSize; x += size) {

				double u = (double) x / image.getWidth();

				if (x % 40 == 0) {
					synchronized (image) {
						if (startLevel == 1) {
							image.updateListener("Completed : " + x * 100
									/ renderSize + " %", this);
						} else {
							image.updateListener("Fast rendering", this);
						}
					}

				}

				for (int y = 0; y < image.getHeight(); y += size) {
					double v = (double) y / image.getHeight();

					// give the bias to the first point
					texture.getColor(u, v, col);

					if (size == 1 && image.isAntiAliased()) {
						col.scale(2);
						temp = texture.getColor(u + du, v);
						col.add(temp);
						temp = texture.getColor(u, v + dv);
						col.add(temp);
						temp = texture.getColor(u + du, v + dv);
						col.add(temp);
						col.scale(0.2);
					}
					
					if (size == 1) {
						//setting the pixel is already thread safe
						image.getImage().setRGB(x, y, col.getRGB());						
					} else {
						image.drawCell(x, y, size, col.getRGB());
					}
				}
			}
		}
		image.threadComplete();
	}

}
