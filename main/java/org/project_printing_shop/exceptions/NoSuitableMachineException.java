package org.project_printing_shop.exceptions;

/**
 * Exception thrown when no suitable machine is found for a specific task in the printing shop.
 */
public class NoSuitableMachineException extends Exception {

    /**
     * Constructs a new NoSuitableMachineException with the specified detail message.
     *
     * @param message the detail message, providing more information about the exception.
     */
    public NoSuitableMachineException(String message) {
        super(message);
    }
}