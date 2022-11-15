package com.marzag.cameraclient.enums;

/**
 * DRC mode options.
 * <ul>
 * <li>{@link #OFF}</li>
 * <li>{@link #LOW}</li>
 * <li>{@link #MEDIUM}</li>
 * <li>{@link #HIGH}</li>
 * </ul>
 *
 */
public enum DRC {

    OFF,

    LOW,

    MEDIUM,

    HIGH;

    /**
     * Returns enum as lowercase.
     */
    public String toString() {
        String id = name();
        return id.toLowerCase();
    }
}
