package com.marzag.cameraclient.enums;

/**
 * Encoding type options.
 * <ul>
 * <li>{@link #JPG}</li>
 * <li>{@link #BMP}</li>
 * <li>{@link #GIF}</li>
 * <li>{@link #PNG}</li>
 * </ul>
 *
 */
public enum Encoding {

    JPG,

    BMP,

    GIF,

    PNG;

    /**
     * Returns the enum in lowercase.
     */
    public String toString() {
        String id = name();
        return id.toLowerCase();
    }
}
