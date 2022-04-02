package com.github.kleesup.CloverGdxUtils;

/**
 * @author KleeSup
 * @version 1.0
 * Class created on 02.04.2022
 *
 * A simple helper class that contains some useful methods.
 */
public class CloverHelper {

    /**
     * Checks whether a value is in bounds of a max and min value.
     * @param min The min value (included, lowest = min).
     * @param max The max value (included, highest = max).
     * @param value The value to check for.
     * @return {@code true} if the specified value is within the specified bounds, {@code false} otherwise.
     */
    public static boolean inBounds(final int min, final int max, final int value){
        return value >= min && value <= max;
    }
    public static boolean inBounds(final long min, final long max, final long value){
        return value >= min && value <= max;
    }
    public static boolean inBounds(final double min, final double max, final double value){
        return value >= min && value <= max;
    }
    public static boolean inBounds(final float min, final float max, final float value){
        return value >= min && value <= max;
    }
    public static boolean inBounds(final short min, final short max, final short value){
        return value >= min && value <= max;
    }
    public static boolean inBounds(final byte min, final byte max, final byte value){
        return value >= min && value <= max;
    }

}
