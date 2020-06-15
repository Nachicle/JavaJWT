package com.nachicle.jwt.utils;

/**
 * Enum that contains available hashing algorithms for JWT signing.
 *
 * @author Ignacio Blasco Muñoz
 * @author https://www.nachicle.com
 * @version 0.0.1
 * @since 0.0.1
 */

public enum HashAlgorithm {

    HS256("HmacSHA256");
    private final String hashName;

    /**
     * Assigns an enum value to a string value.
     *
     * @param hashName Hash string name.
     */
    HashAlgorithm(final String hashName) {
        this.hashName = hashName;
    }

    /**
     * Converts enum value to assigned string value.
     *
     * @return hashName String value for hash algorithm.
     */
    @Override
    public String toString() {
        return hashName;
    }

}
