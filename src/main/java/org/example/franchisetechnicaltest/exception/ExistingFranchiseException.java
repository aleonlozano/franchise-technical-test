package org.example.franchisetechnicaltest.exception;

public class ExistingFranchiseException extends RuntimeException {
    public ExistingFranchiseException(String name) {
        super("A franchise with the name '" + name + "' already exists.");
    }
}
