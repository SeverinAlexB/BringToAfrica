package controllers;

/**
 * Created by Severin on 01.04.2015.
 */
public class AfricaException extends Exception {
    public AfricaException(String message) {
        super(message);
    }
    public AfricaException(){}
    public AfricaException(String message, Throwable cause) {
        super(message, cause);
    }
    public AfricaException(Throwable cause) {
        super(cause);
    }
}
