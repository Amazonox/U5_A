package edu.kit.informatik.ui.Exceptions;

public class InvalidArgumentException extends Exception{
    private final String errorMessage;
    public InvalidArgumentException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
