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
import org.texgen.utils.GraphUtils;

/**
 * Returns a sine wave derived value based on the input value. The speed indicate how fast 
 * the sine wave moves, the octaves indicates how many octaves are to be mixed 
 * in. The size is a scalar value to apply to the end result.
 * The final value is clamped to 0..1
 * 
 * @author Andy Gibson
 */
public class SineWave extends AbstractChannelSignal {

    private final double speed;
    private final double size;
    private final int octaves;

    public SineWave() {
        this(10, 10, 1);
    }

    public SineWave(double speed) {
        this(speed, 10, 10);

    }

    public SineWave(double speed, int octaves) {
        this(speed, octaves, 10);

    }

    public SineWave(double speed, int octaves, double size) {
        this.speed = speed;
        this.size = size;
        this.octaves = octaves;
    }

    public double getValue(double u, double v) {
        u = u * speed;
        double value = GraphUtils.calculateSine(u, octaves);
        value = (value + 1) * 0.5;
        value = value * size;

        return value;
    }

    public double getSpeed() {
        return speed;
    }

    public double getSize() {
        return size;
    }
    public int getOctaves() {
        return octaves;
    }
}
