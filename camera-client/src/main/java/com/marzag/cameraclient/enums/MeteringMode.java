package com.marzag.cameraclient.enums;

/**
 * Metering mode options.
 * <ul>
 * <li>{@link #AVERAGE}</li>
 * <li>{@link #SPOT}</li>
 * <li>{@link #BACKLIT}</li>
 * <li>{@link #MATRIX}</li>
 * </ul>
 *
 */
public enum MeteringMode {

    /**
     * Average the whole frame for metering.
     */
    AVERAGE,

    /**
     * Spot metering.
     */
    SPOT,

    /**
     * Assume a backlit image.
     */
    BACKLIT,

    /**
     * Matrix metering.
     */
    MATRIX;

    /**
     * Returns enum in lowercase.
     */
    public String toString() {
        String id = name();
        return id.toLowerCase();
    }
}
