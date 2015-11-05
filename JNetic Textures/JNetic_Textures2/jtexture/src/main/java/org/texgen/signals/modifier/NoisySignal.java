/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.signals.modifier;

import org.texgen.textures.AbstractChannelSignal;
import org.texgen.textures.ChannelSignal;

/**
 * Adds a level of noise to the signal from the source.
 * 
 * <pre>
 * result = (source + noise(u,v))
 * result = result scaled to lie between 0..1
 * </pre>
 * 
 * @author Andy Gibson
 */
public class NoisySignal extends AbstractChannelSignal {

	private final double speed;
	private final int octaves;
	private final double size;
	private final double offset;
	private final ChannelSignal source;

	public NoisySignal() {
		this(null);
	}

	public NoisySignal(ChannelSignal source) {
		this(source, 0.25);
	}

	public NoisySignal(ChannelSignal source, double speed) {
		this(source, speed, 10, 0.25);
	}

	public NoisySignal(ChannelSignal source, double speed, int octaves,
			double size) {
		this(source, speed, octaves, size, 0);
	}

	public NoisySignal(ChannelSignal source, double speed, int octaves,
			double size, double offset) {
		if (source == null) {
			throw new IllegalArgumentException("Source cannot be null");
		}
		this.source = source;
		this.speed = speed;
		this.octaves = octaves;
		this.size = size;
		this.offset = offset;
	}

	public double getValue(double u, double v) {
		double value = calculateValue(u, v, source);
		value = value
				+ getNoise().fbmNoise2(offset + (u * speed),
						offset + (v * speed), octaves) * size;
		// value = value / (1.0+size);
		return value;
	}

	public double getSpeed() {
		return speed;
	}

	public int getOctaves() {
		return octaves;
	}

	public double getSize() {
		return size;
	}

	public double getOffset() {
		return offset;
	}

	public ChannelSignal getSource() {
		return source;
	}
}
