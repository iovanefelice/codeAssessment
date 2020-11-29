package com.backend.springboot.docker.exception;

/**
 * The type Person exception.
 */
public class PersonException extends Exception {
    /**
     * Instantiates a new Person exception.
     *
     * @param errorMessage the error message
     */
    public PersonException(String errorMessage) {
        super(errorMessage);
    }
}