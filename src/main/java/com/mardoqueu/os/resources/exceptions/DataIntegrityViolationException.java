package com.mardoqueu.os.resources.exceptions;

import java.io.Serial;

public class DataIntegrityViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
