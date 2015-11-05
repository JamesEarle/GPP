/*
 * Texture Generator Library by Andy Gibson
 *
 * This software is distributed under the terms of the FSF Lesser
 * Gnu Public License. It is provided as-is, without any express
 * or implied warranty, or guarantee of any kind.
 *
 */
package org.texgen.textures;

/**
 * Helper Channel Signal class that takes another signal as a constructor
 * parameter and then as part of its implementation, uses the value from that
 * signal to construct a final result value.
 * 
 * @author Andy Gibson
 */
public abstract class ChainedChannelSignal extends AbstractChannelSignal {

	private final ChannelSignal source;

	public ChainedChannelSignal() {
		this(null);
	}

	public ChainedChannelSignal(ChannelSignal source) {
		super();
		if (source == null) {
			throw new IllegalArgumentException(
					"Source signal in a chained channel signal cannot be null");
		}
		this.source = source;
	}

	public ChannelSignal getSource() {
		return source;
	}

}
