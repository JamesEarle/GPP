/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.texgen.textures.Texture;

/**
 * Base class for rendering textures, managing the image and allowing the image
 * to be saved. This class is used to spawn the threads off that perform the
 * rendering.
 * 
 * @author Andy Gibson
 */
public class TextureImage implements Runnable {

	private BufferedImage image;
	private int width;
	private int height;
	private Texture texture;
	private RenderListener listener;
	private boolean antiAliased;
	private boolean gradualDisplay;
	private int threadCount = 0;
	private long startTime;
	private long endTime;
	private RenderThread keyThread;

	/**
	 * Create a new Texture Image instance
	 * 
	 * @param width
	 *            width of image
	 * @param height
	 *            height of image
	 */
	public TextureImage(int width, int height) {
		resize(width, height);
	}

	/**
	 * Adjusts the size of the image used. Paints the old image onto the new one
	 * 
	 * @param width
	 *            new image width
	 * @param height
	 *            new image height
	 */
	public void resize(int width, int height) {
		System.out.println("Resising with new dim " + width);

		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = newImage.getGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		image = newImage;
		this.width = width;
		this.height = height;
	}

	/**
	 * 
	 * Handles call to a render listener pass the message to the listener
	 * 
	 * @param message
	 *            Message to pass to the listener
	 * 
	 */
	protected void updateListener(String message, Object source) {
		if (listener != null) {
			if (source == this || source == keyThread) {
				listener.notifyState(this, message);
			}
		}
	}

	/**
	 * Thread safe call to draw the cell on the image canvas.
	 * 
	 * @param x
	 *            x position of pixels to set
	 * @param y
	 *            y position of the pixels to set
	 * @param size
	 *            size of the cell to draw
	 * @param color
	 *            Color to set the cell to
	 */
	protected void drawCell(int x, int y, int size, int color) {
		for (int ix = x; ix < x + size; ix++) {
			for (int iy = y; iy < y + size; iy++) {
				if (ix < image.getWidth() && iy < image.getHeight()) {
					image.setRGB(ix, iy, color);
				}
			}
		}
	}

	/**
	 * Starts the rendering process for a given texture. This method draws the
	 * texture on the backing canvas.
	 * 
	 * @param texture
	 *            Texture to render
	 */
	private void render(Texture texture) {

		// will never return less than 1
		threadCount = Runtime.getRuntime().availableProcessors();

		int size = getWidth() / threadCount;
		startTime = System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			RenderThread rthread = new RenderThread(this, texture, size * i,
					size);
			if (i == 0) {
				keyThread = rthread;
			}
			new Thread(rthread).start();
		}
	}

	/**
	 * Called when each thread completes, and keeps track of how many threads
	 * are still running. Also notifies the listener that it is complete and
	 * passes it some timing information.
	 */
	void threadComplete() {
		threadCount--;
		if (threadCount == 0) {
			endTime = System.currentTimeMillis();
			updateListener("Time taken = " + (endTime - startTime), this);
			if (listener != null) {
				listener.notifyComplete(this);
			}

		}
	}

	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Renders the texture held in the texture member
	 */
	public void run() {
		run(getTexture());
	}

	public void run(Texture texture) {
		if (texture != null) {
			render(texture);
		}
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public RenderListener getListener() {
		return listener;
	}

	public void setListener(RenderListener listener) {
		this.listener = listener;
	}

	public boolean isAntiAliased() {
		return antiAliased;
	}

	public void setAntiAliased(boolean antiAliased) {
		this.antiAliased = antiAliased;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isGradualDisplay() {
		return gradualDisplay;
	}

	public void setGradualDisplay(boolean gradualDisplay) {
		this.gradualDisplay = gradualDisplay;
	}

	/**
	 * Helper method which saves the image as a PNG
	 * 
	 * @param fileName
	 *            Name of the file to save the PNG as
	 */
	public void saveAsPNG(String fileName) {
		try {
			ImageIO.write(image, "PNG", new File(fileName));
		} catch (IOException ex) {
			Logger.getLogger(TextureImage.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
	
	public void renderAndWait() {
		render(texture);
		while (threadCount != 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
