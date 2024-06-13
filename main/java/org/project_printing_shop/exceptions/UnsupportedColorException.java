package org.project_printing_shop.exceptions;

/**
 * Exception thrown when attempting to use color printing on a machine that does not support it.
 */
public class UnsupportedColorException extends Exception {

    /**
     * Constructs a new UnsupportedColorException with the specified detail message.
     *
     * @param message the detail message, providing more information about the exception.
     */
    public UnsupportedColorException(String message) {
        super(message);
    }
}