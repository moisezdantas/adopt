package br.com.adopt.service.exception;

public class UserDuplicateException extends RuntimeException {

    public UserDuplicateException(String msg) {
        super(msg);
    }
}
