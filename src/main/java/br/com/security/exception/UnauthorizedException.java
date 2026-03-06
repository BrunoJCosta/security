package br.com.security.exception;

public class UnauthorizedException extends Exception {

    public UnauthorizedException() {
        super("credentials invalid");
    }
}
