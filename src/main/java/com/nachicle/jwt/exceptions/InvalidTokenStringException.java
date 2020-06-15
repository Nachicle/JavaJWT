package com.nachicle.jwt.exceptions;

/**
 * Exception thrown when an invalid token is found.
 *
 * @author Ignacio Blasco Muñoz
 * @author https://www.nachicle.com
 * @version 0.0.1
 * @since 0.0.1
 */

public class InvalidTokenStringException extends Exception {

    /**
     * Creates a new InvalidTokenStringException and returns it.
     *
     * @param message Message shown when exception is thrown.
     */
    public InvalidTokenStringException(String message) {
        super(message);
    }
}
